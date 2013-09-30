package simplifiedDots;

import java.util.HashMap;

/**
 * Position is a simple tuple (x, y). Represent the position of something.
 */
public class Position {
	private int _x, _y;
	private static HashMap<Integer, Position> _positions;
	
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
		return (_x + 1) * (_y + 1) + _y + 1;
	}

	public static Position get(int x, int y) {
		int hashCode = (x + 1) * (y + 1) + y + 1;
		if(_positions == null)
			_positions = new HashMap<Integer, Position>();
		if(!_positions.containsKey(hashCode))
			_positions.put(hashCode, new Position(x, y));
		return _positions.get(hashCode);
	}
}
