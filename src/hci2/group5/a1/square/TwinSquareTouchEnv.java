package hci2.group5.a1.square;

import hci2.group5.a1.main.DataRecorder;
import hci2.group5.a1.main.MainActivity;
import hci2.group5.a1.specs.ExperimentSetting;
import hci2.group5.a1.specs.Finger;
import hci2.group5.a1.specs.SquareComboSpec;
import hci2.group5.a1.specs.SquareSize;
import hci2.group5.a1.util.TimeUtil;
import android.app.Activity;

public class TwinSquareTouchEnv {

	private SquareComboSpec squareCombo;
	private Square square1, square2;
	private SquareSize squareSize;
	
	private int trialCount;
	
	private Finger finger;
	
	private Activity parentActivity;
	
	public TwinSquareTouchEnv(Activity activity) {
		parentActivity = activity;
		
		initEnvironment();
		
		setFinger(Finger.Thumb);
	}

	private void initEnvironment() {
		squareCombo = SquareComboSpec.SHORT;
		squareSize = SquareSize.S;
		buildSquares();
		
		trialCount = 1;
	}

	public void setFinger(Finger finger) {
		this.finger = finger;
		parentActivity.showDialog(finger.id);
	}
	
	public Finger getFinger() {
		return finger;
	}
	
	private void buildSquares() {
		square1 = new Square(squareCombo.x1, squareCombo.y1, squareSize.length);
		square2 = new Square(squareCombo.x2, squareCombo.y2, squareSize.length);
	}

	private int squareLastHit;
	
	public void touched(Activity parentActivity, float x, float y) {
		if (square1.isVisible() && square1.contains(x, y)) {
			square1.setVisible(false);
			squareLastHit = 1;
			
			DataRecorder.getInstance().record(toRecordData());
		}
		else if ( (! square1.isVisible()) && square2.contains(x, y)) {
			square1.setVisible(true);
			squareLastHit = 2;
			
			DataRecorder.getInstance().record(toRecordData());
			
			trialCount++;
			
			if (trialCount > ExperimentSetting.TRIAL_MAX) {
				trialCount = 1;
				
				if (squareSize == SquareSize.S) {
					squareSize = SquareSize.M;
				}
				else if (squareSize == SquareSize.M) {
					squareSize = SquareSize.L;
				}
				else {
					squareSize = SquareSize.S;
					
					if (squareCombo == SquareComboSpec.SHORT) {
						squareCombo = SquareComboSpec.MIDIUM;
					}
					else if (squareCombo == SquareComboSpec.MIDIUM) {
						squareCombo = SquareComboSpec.LONG;
					}
					else {
						if (finger == Finger.Thumb) {
							initEnvironment();
							setFinger(Finger.Index);
						}
						else if (finger == Finger.Index) {
							parentActivity.showDialog(MainActivity.DONE_DIALOG_ID);
						}
					}
				}
				
				buildSquares();
			}
		}
	}

	public Square getSquare1() { return square1; }
	public Square getSquare2() { return square2; }
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(trialCount);sb.append(" Twin Square: (");
        	sb.append("length: "); sb.append(squareSize.name());
        	sb.append(", distance: "); sb.append(squareCombo.name());
        	sb.append(")\n");
    	
        sb.append("  Square1: "); sb.append(square1.toString()); sb.append("\n");
        sb.append("  Square2: "); sb.append(square2.toString()); sb.append("\n");
        
        return sb.toString();
	}

	public String toRecordData() {
		String currTime = TimeUtil.currTime();
		
        String trialInfo = String.format("(%s, %s) Trial %d: ", squareCombo.name(), squareSize.name(), trialCount);
        String hitInfo = String.format(" hit square %d at %s.", squareLastHit, currTime);
        
        return trialInfo + hitInfo;
	}
}