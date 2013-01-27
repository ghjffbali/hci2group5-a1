package hci2.group5.a1;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Contains 2 squares on a 1024x600 device.
 */
class TwinSquareView extends View {

	private int BACKGROUND_COLOR = Color.BLACK;
	private int SQUARE_COLOR = Color.WHITE;

	private Paint squarePainter;
	
	public TwinSquareView(Context context) {
		super(context);
		
		setBackgroundColor(BACKGROUND_COLOR);
		
		squarePainter = new Paint();
		squarePainter.setColor(SQUARE_COLOR);
		squarePainter.setAntiAlias(true);
	}
	
	private int width = 0, height = 0;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (width  == 0) { width = getWidth();   }
		if (height == 0) { height = getHeight(); }

		
		// prepare square properties TODO
		Random random = new Random();
		// get the center point of square 1
		int square1X = random.nextInt(width) + 1;
		int square1Y = random.nextInt(height) + 1;
		// get the center point of square 2
		int square2X = random.nextInt(width) + 1;
		int square2Y = random.nextInt(height) + 1;
		// set square length
		int squareLength = 100;
		
		
		// draw
		RectF rect;
		rect = Helper.buildRectF(square1X, square1Y, squareLength);
		canvas.drawRect(rect, squarePainter);
		rect = Helper.buildRectF(square2X, square2Y, squareLength);
		canvas.drawRect(rect, squarePainter);
	}
}

class Helper {
	
	public static RectF buildRectF(float centerX, float centerY, float length) {
		float halfLength = length / 2F;
		
		float leftX = centerX - halfLength;
		float topY = centerY - halfLength;
		float rightX = centerX + halfLength;
		float bottomY = centerY + halfLength;
		
		return new RectF(leftX, topY, rightX, bottomY);
	}
}