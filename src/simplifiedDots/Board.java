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
	 * Get a specific cell based in its position.
	 * 
	 * @TODO: Maybe this method isn't really needed.
	 */
	public Cell getCell(Position p) {
		return _cells.get(p);
	}
}
