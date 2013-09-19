package simplifiedDots;

import java.util.HashMap;
import java.util.Map;

/**
 * The simplified dots game board.
 * _size is the board size (square board).
 * _cells are the board cells.
 */
public class Board {
	protected HashMap<Position, Cell> _cells;
	protected int _size;
	
	public Board(int size) {
		_size = size;
		clear();
	}
	
	/**
	 * Copy constructor allows an easy board copy.
	 */
	public Board(Board b) {
		_size = b._size;
		_cells = new HashMap<Position,Cell>();
		for (Map.Entry<Position,Cell> entry : b._cells.entrySet()) {
			Position position = entry.getKey();
			Cell cell = entry.getValue();
			_cells.put(position, new Cell(cell));
		}
	}
	
	/**
	 * Clear the board (create new cells and replace them)
	 */
	public void clear() {
		_cells = new HashMap<Position, Cell>();
		for(int x = 0; x < _size; x++) {
			for(int y = 0; y < _size; y++) {
				Position p = new Position(x, y);
				Cell c = new Cell();
				_cells.put(p, c);
			}
		}
	}
	
	/**
	 * Mark a stripe in position p.
	 * This method returns true if the stripe is marked.
	 * It returns false if the stripe already exists or the position is invalid (invalid move).
	 */
	public boolean mark(Position p, Stripe s, char name) {
		if(!_isValidMove(p, s)) return false;
		_cells.get(p).mark(s, name);
		_markNeighbors(p, s, name);
		return true;
	}
	
	/**
	 * Auxiliary method to verify if a given move (Position, Stripe) is valid.
	 */
	private boolean _isValidMove(Position p, Stripe s) {
		if(!_cells.containsKey(p)) return false;
		Cell c = _cells.get(p);
		if(c.isFilled(s)) return false;
		return true;
	}
	
	/**
	 * Auxiliary method to mark the neighbor cell stripe.
	 */
	private void _markNeighbors(Position p, Stripe s, char name) {
		Position pp;
		switch(s) {
		case UP:
			pp = p.relativePosition(0, -1);
			if(!_isValidMove(pp, Stripe.DOWN)) return;
			_cells.get(pp).mark(Stripe.DOWN, name);
			break;
		case RIGHT:
			pp = p.relativePosition(+1, 0);
			if(!_isValidMove(pp, Stripe.LEFT)) return;
			_cells.get(pp).mark(Stripe.LEFT, name);
			break;
		case DOWN:
			pp = p.relativePosition(0, +1);
			if(!_isValidMove(pp, Stripe.UP)) return;
			_cells.get(pp).mark(Stripe.UP, name);
			break;
		case LEFT:
			pp = p.relativePosition(-1, 0);
			if(!_isValidMove(pp, Stripe.RIGHT)) return;
			_cells.get(pp).mark(Stripe.RIGHT, name);
			break;
		}
	}
	
	/**
	 * Return a copy of the cell in position p.
	 */
	public Cell getCell(Position p) {
		if(!_cells.containsKey(p)) return null;
		return new Cell(_cells.get(p));
	}

	/**
	 * Return a copy of the cell next to the one in position p, in the side s.
	 */
	public Cell getCellNextTo(Position p, Stripe s) {
		if(!_cells.containsKey(p)) return null;
		Position pp = null;
		switch(s) {
		case UP: pp = p.relativePosition(0, -1); break;
		case RIGHT: pp = p.relativePosition(+1, 0); break;
		case DOWN: pp = p.relativePosition(0, +1); break;
		case LEFT: pp = p.relativePosition(-1, 0); break;
		}
		if(_cells.containsKey(pp)) return new Cell(_cells.get(pp));
		else return new Cell();
	}
	
	/**
	 * Verify if all cells in board are filled.
	 */
	public boolean allCellsFilled() {
		for(Cell c : _cells.values()) {
			if(!c.isFilled()) return false;
		}
		return true;
	}
	
	public int size() {
		return this._size;
	}
	
	@Override
	public String toString() {
		String string = "";
		for (Map.Entry<Position,Cell> entry : this._cells.entrySet()) {
			string += "x: "+entry.getKey().x() + " - y: "+entry.getKey().y() +" "+
					"UP: "+entry.getValue().isFilled(Stripe.UP)+" "+
					"RIGHT: "+entry.getValue().isFilled(Stripe.RIGHT)+" "+
					"LEFT: "+entry.getValue().isFilled(Stripe.LEFT)+" "+
					"DOWN: "+entry.getValue().isFilled(Stripe.DOWN)+"\n";
		}
		return string;
	}
}
