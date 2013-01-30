package hci2.group5.a1.square;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square {

	private static int SQUARE_COLOR = Color.DKGRAY;
	private static int ATTENTION_COLOR = Color.WHITE;
	
	// painter
	public static Paint painter;
	static {
		painter = new Paint();
		painter.setColor(SQUARE_COLOR);
		painter.setAntiAlias(true);
	}
	
	// the shape
	private Rect rect;
	private int centerX, centerY;

	// hit
	private boolean isHit;
	private long hitTime;
	
	public Square(int centerX, int centerY, int length) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		initRect(centerX, centerY, length);
		
		isHit = false;
	}

	private void initRect(int centerX, int centerY, int length) {
		int halfLength = length / 2;
		
		int leftX = centerX - halfLength;
		int topY = centerY - halfLength;
		int rightX = centerX + halfLength;
		int bottomY = centerY + halfLength;
		
		rect = new Rect(leftX, topY, rightX, bottomY);
	}
	
	public int getCenterX() { return centerX; }
	public int getCenterY() { return centerY; }

	public void setHit(boolean isHit) {
		this.isHit = isHit;
		hitTime = System.currentTimeMillis();
	}
	public boolean isHit() { return isHit; }
	
	public long hitTimeDiff(Square s2) {
		return this.hitTime - s2.hitTime;
	}

	public void draw(Canvas canvas) {
		canvas.drawRect(rect, painter);
	}

	public void drawAttention(Canvas canvas) {
		painter.setColor(ATTENTION_COLOR);
		canvas.drawRect(rect, painter);
		painter.setColor(SQUARE_COLOR);
	}

	public boolean contains(float x, float y) {
		return rect.contains((int)x, (int)y);
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Square("); sb.append(centerX); sb.append(", "); sb.append(centerY); sb.append(")");
        return sb.toString();
	}
}
