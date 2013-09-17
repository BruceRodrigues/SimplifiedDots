package simplifiedDots;

import java.util.HashMap;

/**
 * A Simplified Dots game cell.
 * The game cell have information about its up, right, down and left stripes.
 * A cell is a representation of four dots.
 */
public class Cell {
	HashMap<Stripe, Boolean> _stripes;
	
	public Cell() {
		_stripes = new HashMap<Stripe, Boolean>();
		_stripes.put(Stripe.UP, false);
		_stripes.put(Stripe.RIGHT, false);
		_stripes.put(Stripe.DOWN, false);
		_stripes.put(Stripe.LEFT, false);
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
	 * One side can be marked more than once!
	 */
	public void mark(Stripe s) {
		 if(_stripes.containsKey(s)) _stripes.put(s, true);
	}
}
