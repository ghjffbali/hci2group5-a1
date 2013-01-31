package hci2.group5.a1.square;

import hci2.group5.a1.specs.SquareAmplitude;
import hci2.group5.a1.specs.SquareSize;
import hci2.group5.a1.util.RandomInt;

public class SquareFactory {

	public static Square getStartSquare(MainView mainView) {
		
		int length = SquareSize.SMALL.length;
		int centerX = mainView.getWidth() / 2;
		int centerY = mainView.getHeight() - length;
		
		return new Square(centerX, centerY, length);
	}

	public static Square getRandomTargetSquare(MainView mainView, Square startSquare, SquareAmplitude amplitude, SquareSize size) {

		int length = size.length;
		int x, y;
		
		
		int halfLength = size.length / 2;
		
		int minX = halfLength;
		int maxX = mainView.getWidth() - halfLength;
		x = RandomInt.generateWithin(minX, maxX);
		
		int x0 = startSquare.getCenterX(), y0 = startSquare.getCenterY();
		int a = amplitude.value;
		y = (int) (y0 - Math.sqrt( a*a - (x-x0) * (x-x0) ));
		
		
		return new Square(x, y, length);
	}
}
