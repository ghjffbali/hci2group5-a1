package hci2.group5.a1.square;

import hci2.group5.a1.specs.ExperimentSetting;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Draw 2 squares on a 1024x600 device.
 */
public class TwinSquareView extends View {

	private Activity parentActivity;
	
	private TwinSquareTouchEnv touchEnv;

	public TwinSquareView(Activity activity) {
		super(activity.getApplicationContext());
		
		parentActivity = activity;
		
		setBackgroundColor(ExperimentSetting.BACKGROUND_COLOR);
		
		touchEnv = new TwinSquareTouchEnv(activity);
		
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					touchEnv.touched(parentActivity, event.getX(), event.getY());
					
					invalidate();
				}
				return true; // Always turn true. No need for the upper level to consume.
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		touchEnv.getSquare1().draw(canvas);
		touchEnv.getSquare2().draw(canvas);
	}
}