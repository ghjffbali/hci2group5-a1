package hci2.group5.a1.util;

import java.util.Random;

public class RandomInt {

	private static Random rand = new Random();
	
	public static int generateWithin(int min , int max) {
		return rand.nextInt(max - min + 1) + min;
	}
}
