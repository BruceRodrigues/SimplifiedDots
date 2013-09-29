package simplifiedDotsGUI;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simplifiedDots.Cell;
import simplifiedDots.Position;
import simplifiedDots.Stripe;
import simplifiedDotsControl.Control;

public class DotToCellTest {

	@Test
	public void test() {
		
		//Game size 3
		Control c = new Control();
		
		Position dot00 = new Position(0,0);
		Position dot01 = new Position(0,1);
		c.onClick(dot00, dot01);
		Cell cell = c.getCell(new Position(0,0));
		
		assertTrue(cell.isFilled(Stripe.LEFT));
		
		Position dot11 = new Position(1,1);
		Position dot10 = new Position(1,0);
		c.onClick(dot11, dot10);
		cell = c.getCell(new Position(0,0));
		
		assertTrue(cell.isFilled(Stripe.RIGHT));
		
		Position dot22 = new Position(3,3);
		Position dot12 = new Position(2,3);
		c.onClick(dot22, dot12);
		cell = c.getCell(new Position(2,2));
		assertTrue(cell.isFilled(Stripe.DOWN));
	}

}
