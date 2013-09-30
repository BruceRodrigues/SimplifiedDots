package simplifiedDotsIA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import simplifiedDots.Board;
import simplifiedDots.Game;
import simplifiedDots.InvalidMovementException;
import simplifiedDots.Movement;
import simplifiedDots.Player;

public class IAPlayerTest {
	
	private class DummyGame extends Game {

		public DummyGame(Game game) {
			super(game);
		}

		public DummyGame(int i) {
			super(i);
		}
		
		public Board board() {
			return _board;
		}
		
	}

	private void _play(Game g, Player pl, Movement m) {
		assertEquals(g.currentPlayer(), pl);
		try {
			g.play(m);
		} catch (InvalidMovementException e) {
			fail();
		}
	}
	
	@Test
	public void test() {
		DummyGame game = new DummyGame(2);
		IAPlayer player1 = new IAPlayer("1st_player", game);
		IAPlayer player2 = new IAPlayer("2nd_player", game);
		
		game.addPlayer(player1);
		game.addPlayer(player2);

		Board b = game.board();
		b.print();
		System.out.println("################################");

		while(!game.isOver()) {
			_play(game, game.currentPlayer(), game.currentPlayer().getNextMovement());
			b.print();
			System.out.println("################################");
		}
		
		System.out.println("Player1 = " + game.getPoints(player1));
		System.out.println("Player2 = " + game.getPoints(player2));
	}

}
