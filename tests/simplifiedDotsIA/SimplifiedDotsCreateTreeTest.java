package simplifiedDotsIA;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import simplifiedDots.DummyPlayer;
import simplifiedDots.Game;
import simplifiedDots.Movement;
import simplifiedDots.Position;
import simplifiedDots.Stripe;
import simplifiedDotsIA.MinimaxNode;

public class SimplifiedDotsCreateTreeTest {
	
	public class SpecificGame extends Game {

		public SpecificGame(int gameSize) {
			super(gameSize);
			
			assert(gameSize == 2);
			
			/*
			 *   * * *
			 *        
			 *   * *-*
			 *     |   -> Should play here
			 *   * *-*
			 */
			_board.mark(new Position(1, 1), Stripe.UP, 'x');
//			_board.mark(new Position(1, 1), Stripe.LEFT, 'x');
			_board.mark(new Position(1, 1), Stripe.DOWN, 'x');
		}
		
	}
	
	@Test
	public void testBestMovementInSpecificGame() {
		Game game = new SpecificGame(2);
		game.addPlayer(new DummyPlayer());
		MinimaxNode t = new MinimaxNode(game, 0, null);
		Movement m = t.getBestMovement(3);
	
		System.out.println(m.position().x());
		System.out.println(m.position().y());
		System.out.println(m.stripe());
		
		assertEquals(m.position(), new Position(1, 1));
		assertEquals(m.stripe(), Stripe.RIGHT);
	}

}
