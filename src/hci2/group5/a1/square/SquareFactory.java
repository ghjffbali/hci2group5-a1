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
        int a2 = a*a;


        x = RandomInt.generateWithin(halfLength, maxX);
        int deltaX = x0-x;
        y = (int) (y0 - Math.sqrt(a2 - (deltaX*deltaX)));

        while(y > maxY || y < halfLength) {
            x = RandomInt.generateWithin(halfLength, maxX);
            deltaX = x0-x;
            y = (int) (y0 - Math.sqrt(a2 - (deltaX*deltaX)));
        }

        int deltaY = y0-y;


        System.out.println("amplitude is " +a +", y is " +y +", y0 is " +y0 +", x is " +x + ", and x0 is " +x0);
        System.out.println("amplitude^2 is " +a2 +", delta y is " +deltaY +", delta x is " +deltaX);
        System.out.println("maxX is " +maxX);
        return new HitableSquare(x, y, length);
    }
}
