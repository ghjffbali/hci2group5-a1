package hci2.group5.a1.square;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square {

	private static Paint painter;
	static {
		painter = new Paint();
		painter.setColor(Color.WHITE);
		painter.setAntiAlias(true);
	}
	
	private Rect rect;
	
	private int centerX, centerY;
	
	private boolean visible;
	
	public Square(int centerX, int centerY, int length) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		initRect(centerX, centerY, length);
		
		setVisible(true);
	}

	private void initRect(int centerX, int centerY, int length) {
		int halfLength = length / 2;
		
		int leftX = centerX - halfLength;
		int topY = centerY - halfLength;
		int rightX = centerX + halfLength;
		int bottomY = centerY + halfLength;
		
		rect = new Rect(leftX, topY, rightX, bottomY);
	}
	
	public Rect getRect() {
		return rect;
	}
	
	public void setVisible(boolean b) { visible = b; }

	public boolean isVisible() { return visible; }

	public void draw(Canvas canvas) {
		if (isVisible()) {
			canvas.drawRect(rect, painter);
		}
	}

	public boolean contains(float x, float y) {
		return rect.contains((int)x, (int)y);
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder(18);
        
        // coordinate
        sb.append("Square("); sb.append(centerX); sb.append(", "); sb.append(centerY); sb.append(")");
        // visibility
        sb.append(" "); sb.append(isVisible()); sb.append(" visible.");
        
        return sb.toString();
	}
}
