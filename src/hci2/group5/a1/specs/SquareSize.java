package hci2.group5.a1.specs;

public enum SquareSize {

	SMALL(100),
	MEDIUM(150),
	LARGE(200);
	
	/**
	 * In pixels
	 */
	public final int length;

	/**
	 * 
	 * @param length In pixels
	 */
	SquareSize(int length) {
		this.length = length;
	}
	
	public char toAbbr() {
		return name().charAt(0);
	}
}
