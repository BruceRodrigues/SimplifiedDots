package simplifiedDots;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class SimplifiedDotsCreateTreeTest {
	

	@Test
	public void test() {
		
		Board b = new Board(2);
		Frame f = new Frame();
		List<Board> l = f.createAllPossibilities(b);
		
		for (Board bo : l) {
			System.out.println(bo.toString());
		}
		assertTrue(l.get(0).getCell(new Position(0,0)).isFilled(Stripe.UP));
		assertFalse(l.get(0).getCell(new Position(0,0)).isFilled(Stripe.RIGHT));
		assertFalse(l.get(0).getCell(new Position(0,0)).isFilled(Stripe.LEFT));
		assertFalse(l.get(0).getCell(new Position(0,0)).isFilled(Stripe.DOWN));
		
		assertFalse(l.get(l.size()-1).getCell(new Position(1,1)).isFilled(Stripe.UP));
		assertFalse(l.get(l.size()-1).getCell(new Position(1,1)).isFilled(Stripe.RIGHT));
		assertTrue(l.get(l.size()-1).getCell(new Position(1,1)).isFilled(Stripe.LEFT));
		assertFalse(l.get(l.size()-1).getCell(new Position(1,1)).isFilled(Stripe.DOWN));
		
	}

}
