package simplifiedDots;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimplifiedDotsTest {

	@Test
	public void testBoard() {
		// @TODO: Test the board!
	}
	
	@Test
	public void testCell() {
		Cell c = new Cell();
		assertFalse(c.isFilled(Stripe.UP));
		assertFalse(c.isFilled(Stripe.RIGHT));
		assertFalse(c.isFilled(Stripe.DOWN));
		assertFalse(c.isFilled(Stripe.LEFT));
		
		c.mark(Stripe.UP);
		assertTrue(c.isFilled(Stripe.UP));
		assertFalse(c.isFilled(Stripe.RIGHT));
		assertFalse(c.isFilled(Stripe.DOWN));
		assertFalse(c.isFilled(Stripe.LEFT));

		assertFalse(c.isFilled());
		c.mark(Stripe.RIGHT);
		c.mark(Stripe.DOWN);
		c.mark(Stripe.LEFT);
		assertTrue(c.isFilled());
	}

}
