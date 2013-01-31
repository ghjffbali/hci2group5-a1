package hci2.group5.a1.square;

public class HitableSquare extends Square {

	private boolean isHit;
	
	private long hitTime;
	
	private int missCount;
	
	public HitableSquare(int centerX, int centerY, int length) {
		super(centerX, centerY, length);
		
		isHit = false;
		missCount = 0;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
		hitTime = System.currentTimeMillis();
	}

	public boolean isHit() {
		return isHit;
	}
	
	public boolean notHit() {
		return ! isHit;
	}
	
	public long hitTimeDiff(HitableSquare s2) {
		return this.hitTime - s2.hitTime;
	}
	
	@Override
	public boolean contains(float x, float y) {
		boolean isContained = super.contains(x, y);
		
		if ( ! isContained) {
			missCount++;
		}
		
		return isContained;
	}
	
	public float getErrorRate() {
		return (float)missCount / (float)(missCount + 1);
	}
}
