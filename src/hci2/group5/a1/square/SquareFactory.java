package hci2.group5.a1.square;

import hci2.group5.a1.specs.SquareAmplitude;
import hci2.group5.a1.specs.SquareSize;
import hci2.group5.a1.util.RandomInt;

public class SquareFactory {

	public static HitableSquare getStartSquare(MainView mainView) {
		
		int length = SquareSize.SMALL.length;
		int centerX = mainView.getWidth() / 2;
		int centerY = mainView.getHeight() - length;
		
		return new HitableSquare(centerX, centerY, length);
	}

	public static HitableSquare getRandomTargetSquare(MainView mainView, Square startSquare, SquareAmplitude amplitude, SquareSize size) {

		int length = size.length;
		int x, y;
		
		int halfLength = size.length / 2;

		int maxX = mainView.getWidth() - halfLength;
		int maxY = mainView.getHeight() - halfLength;
		int x0 = startSquare.getCenterX();
		int y0 = startSquare.getCenterY();
		int a = amplitude.value;
		
		x = RandomInt.generateWithin(halfLength, maxX);

		y = (int) (y0 + Math.abs(x0 - x) - a);
		while(y > maxY || y < halfLength) {
			x = RandomInt.generateWithin(halfLength, maxX);
			y = (int) (y0 + Math.abs(x0 - x) - a);
		}
		
		return new HitableSquare(x, y, length);
	}
}
