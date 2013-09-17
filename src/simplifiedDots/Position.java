package simplifiedDots;

/**
 * Position is a simple tuple (x, y). Represent the position of something.
 */
public class Position {
	private int _x, _y;
	
	public Position(int x, int y) {
		_x = x;
		_y = y;
	}
	
	public int x() { return _x; }
	public int y() { return _y; }
	
	public Position relativePosition(int xOffset, int yOffset) {
		return new Position(_x + xOffset, _y + yOffset);
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Position))
			return false;
		return ((Position)obj).x() == _x && ((Position)obj).y() == _y;
	}
	
	@Override
	public int hashCode() {
		return _x * _y + _y;
	}
}
