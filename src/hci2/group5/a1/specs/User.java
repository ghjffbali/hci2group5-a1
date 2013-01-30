package hci2.group5.a1.specs;

public class User {

	public static String name;
	public static Finger currFinger = Finger.THUMB; // default
	
	public static void reset() {
		name = null;
		currFinger = Finger.THUMB;
	}
}
