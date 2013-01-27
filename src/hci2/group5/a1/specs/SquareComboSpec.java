package hci2.group5.a1.specs;

/**
 * Includes distance and x,y for both squares.
 */
public enum SquareComboSpec {
	
	// The enum name indicates the square combo's distance
	SHORT(440, 100, 700, 120),
	MIDIUM(300, 250, 640, 180),
	LONG(290, 200, 830, 400);
	
	public final int x1, y1, x2, y2;

	SquareComboSpec(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
	}
}
