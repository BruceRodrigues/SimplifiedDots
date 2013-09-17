package simplifiedDots;

import java.util.HashMap;

/**
 * The simplified dots game board.
 * _size is the board size (square board).
 * _cells are the board cells.
 */
public class Board {
	private HashMap<Position, Cell> _cells;
	private int _size;
	
	public Board(int size) {
		_size = size;
		clear();
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
	public boolean mark(Position p, Stripe s) {
		if(!_isValidMove(p, s)) return false;
		_cells.get(p).mark(s);
		_markNeighbors(p, s);
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
	private void _markNeighbors(Position p, Stripe s) {
		Position pp;
		switch(s) {
		case UP:
			pp = p.relativePosition(0, -1);
			if(!_isValidMove(pp, Stripe.DOWN)) return;
			_cells.get(pp).mark(Stripe.DOWN);
			break;
		case RIGHT:
			pp = p.relativePosition(+1, 0);
			if(!_isValidMove(pp, Stripe.LEFT)) return;
			_cells.get(pp).mark(Stripe.LEFT);
			break;
		case DOWN:
			pp = p.relativePosition(0, +1);
			if(!_isValidMove(pp, Stripe.UP)) return;
			_cells.get(pp).mark(Stripe.UP);
			break;
		case LEFT:
			pp = p.relativePosition(-1, 0);
			if(!_isValidMove(pp, Stripe.RIGHT)) return;
			_cells.get(pp).mark(Stripe.RIGHT);
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
}
