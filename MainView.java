package hci2.group5.a1.square;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

/**
 * Draw 2 squares on a 1024x600 device.
 */
public class MainView extends View {

	private Activity parentActivity;
	
	private SquareDrawTouchEvents drawTouchEvents;

	public MainView(Activity activity) {
		super(activity.getApplicationContext());
		
		setBackgroundColor(Color.BLACK);
		
		parentActivity = activity;
		
		initDrawTouch();
	}

	private void initDrawTouch() {
		drawTouchEvents = new SquareDrawTouchEvents(parentActivity, this);
		
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					drawTouchEvents.touched(event.getX(), event.getY());
					invalidate();
				}
				return true; // Always turn true. No need for the upper level to consume.
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		drawTouchEvents.draw(canvas);
	}
}