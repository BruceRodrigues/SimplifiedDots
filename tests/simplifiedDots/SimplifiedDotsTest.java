package simplifiedDots;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimplifiedDotsTest {

	@Test
	public void testBoard() {
		Board b = new Board(2); // 2x2 board

		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				assertFalse(b.getCell(new Position(x, y)).isFilled());
			}
		}
		
		b.mark(new Position(0, 0), Stripe.UP);
		assertTrue(b.getCell(new Position(0, 0)).isFilled(Stripe.UP));
		assertFalse(b.getCell(new Position(0, 0)).isFilled(Stripe.DOWN));
		
		b.mark(new Position(0, 1), Stripe.UP);
		assertTrue(b.getCell(new Position(0, 1)).isFilled(Stripe.UP));
		assertTrue(b.getCell(new Position(0, 0)).isFilled(Stripe.DOWN));
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
