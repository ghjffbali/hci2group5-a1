package hci2.group5.a1.square;

import hci2.group5.a1.main.DialogHandler;
import hci2.group5.a1.specs.Finger;
import hci2.group5.a1.specs.SquareAmplitude;
import hci2.group5.a1.specs.SquareSize;
import hci2.group5.a1.specs.User;
import hci2.group5.a1.util.DataRecorder;
import android.app.Activity;
import android.graphics.Canvas;

public class SquareDrawTouchEvents {

	// UI
	private Activity parentActivity;
	private MainView mainView;
	
	// squares
	private HitableSquare startSquare, targetSquare;
	
	// factors
	private SquareAmplitude currAmplitude;
	private SquareSize currSize;

	// when done, don't draw squares anymore
	private boolean done;

	// trial data
	private static int MAX_TRIAL = 10;
	private int currTrial;
	private float hitTimeDiffSum;
	private float targetErrorRateSum;
	
	public SquareDrawTouchEvents(Activity parentActivity, MainView mainView) {
		this.parentActivity = parentActivity;
		this.mainView = mainView;
		
		currAmplitude = SquareAmplitude.SHORT;
		currSize = SquareSize.SMALL;
		resetTrial();
		
		done = false;
	}

	private void resetTrial() {
		currTrial = 1;
		hitTimeDiffSum = 0;
		targetErrorRateSum = 0;
	}

	public void touched(float x, float y) {
		
		if (done) { return; }
		
		if (startSquare.notHit()) {
			
			if (startSquare.contains(x, y)) {
				startSquare.setHit(true);
			}
		}
		else {
			if (targetSquare.contains(x, y)) {
				targetSquare.setHit(true);
				targetErrorRateSum += targetSquare.getErrorRate();
				
				DataRecorder.getInstance().recordRaw((String.format("%s - (%s,%s) %d - %d", User.currFinger.toAbbr(), currAmplitude.toAbbr(), currSize.toAbbr(), currTrial, targetSquare.hitTimeDiff(startSquare) )));
				
				trialIncreaced();
				
				if ( ! done) {
					// prepare for next trial
					startSquare.setHit(false);
					buildTargetSquare();
				}
			}
		}
	}

	private void trialIncreaced() {
		
		currTrial++;
		hitTimeDiffSum += targetSquare.hitTimeDiff(startSquare);
		
		if (currTrial > MAX_TRIAL) {
			DataRecorder.getInstance().recordPerAWSummary((String.format("%s - (%s,%s) %f, %f", User.currFinger.toAbbr(), currAmplitude.toAbbr(), currSize.toAbbr(), hitTimeDiffSum / (float)MAX_TRIAL, targetErrorRateSum / (float)MAX_TRIAL )));
			
			resetTrial();
			
			// Change amplitude or size or finger
			
			// first change amplitude
			if (currAmplitude == SquareAmplitude.SHORT) {
				currAmplitude = SquareAmplitude.MIDIUM;
			}
			else if (currAmplitude == SquareAmplitude.MIDIUM) {
				currAmplitude = SquareAmplitude.LONG;
			}
			else { // reach long amplitude, change size now
				
				currAmplitude = SquareAmplitude.SHORT;
				
				if (currSize == SquareSize.SMALL) {
					currSize = SquareSize.MEDIUM;
				}
				else if (currSize == SquareSize.MEDIUM) {
					currSize = SquareSize.LARGE;
				}
				else { // reach large size, change finger now
					
					if (User.currFinger == Finger.THUMB) {
						User.currFinger = Finger.INDEX;
						fingerChanged();
					}
					else { // used index too, END
						done = true;
						parentActivity.showDialog(DialogHandler.DONE_DIALOG_ID);
					}
				}
			}
		}
	}

	private void fingerChanged() {
		currAmplitude = SquareAmplitude.SHORT;
		currSize = SquareSize.SMALL;
		buildTargetSquare();
		
		resetTrial();
		
		parentActivity.showDialog(User.currFinger.id);
	}

	public void draw(Canvas canvas) {

		if (done) {
			canvas.drawText("Thank you, " + User.name + "!", mainView.getWidth() / 2, mainView.getHeight() / 2, Square.painter);
			return;
		}
		
		if (startSquare == null) {
			// initialize startSquare here because we need mainView's width
			startSquare = SquareFactory.getStartSquare(mainView);
			buildTargetSquare();
		}
		
		// draw
		if ( ! startSquare.isHit()) {
			startSquare.drawAttention(canvas);
		}
		else {
			startSquare.draw(canvas);
			targetSquare.drawAttention(canvas);
		}
	}

	private void buildTargetSquare() {
		targetSquare = SquareFactory.getRandomTargetSquare(mainView, startSquare, currAmplitude, currSize);
	}
}
