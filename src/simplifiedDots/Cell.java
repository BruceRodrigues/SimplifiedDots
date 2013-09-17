package simplifiedDots;

import java.util.HashMap;

/**
 * A Simplified Dots game cell.
 * The game cell have information about its up, right, down and left stripes.
 * A cell is a representation of four dots.
 */
public class Cell {
	protected HashMap<Stripe, Boolean> _stripes;
	protected char _name;
	
	public Cell() {
		_stripes = new HashMap<Stripe, Boolean>();
		_stripes.put(Stripe.UP, false);
		_stripes.put(Stripe.RIGHT, false);
		_stripes.put(Stripe.DOWN, false);
		_stripes.put(Stripe.LEFT, false);
		_name = ' ';
	}

	public Cell(Cell cell) {
		_stripes = new HashMap<Stripe, Boolean>();
		_stripes.put(Stripe.UP, cell._stripes.get(Stripe.UP));
		_stripes.put(Stripe.RIGHT, cell._stripes.get(Stripe.RIGHT));
		_stripes.put(Stripe.DOWN, cell._stripes.get(Stripe.DOWN));
		_stripes.put(Stripe.LEFT, cell._stripes.get(Stripe.LEFT));
		_name = cell._name;
	}

	/**
	 * Verify if one side of the cell is filled.
	 */
	public boolean isFilled(Stripe s) {
		return _stripes.containsKey(s) ? _stripes.get(s) : false;
	}

	/**
	 * Verify if all sides of the cell are filled.
	 */
	public boolean isFilled() {
		return isFilled(Stripe.UP) && isFilled(Stripe.RIGHT) &&
			isFilled(Stripe.DOWN) && isFilled(Stripe.LEFT);
	}

	/**
	 * Mark one of the sides of the cell.
	 * If after a mark the cell is filled, the char 'name' is set.
	 */
	public void mark(Stripe s, char name) {
		 if(isFilled()) return;
		 if(_stripes.containsKey(s)) _stripes.put(s, true);
		 if(isFilled()) _name = name;
	}

	/**
	 * Returns the name marked in the cell (if none, returns ' ').
	 */
	public char getName() {
		return _name;
	}
}
