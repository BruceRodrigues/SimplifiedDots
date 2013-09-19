package simplifiedDots;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimplifiedDotsTest {

	private void _play(Game g, Player pl, Position p, Stripe s) {
		assertEquals(g.currentPlayer(), pl);
		try {
			Movement m = new Movement(p, s);
			g.play(m);
		} catch (InvalidMovementException e) {
			fail();
		}
	}

	@Test
	public void test2x2Game() {
		DummyGameObserver gameObserver = new DummyGameObserver();
		Game game = new Game(2);
		game.addObserver(gameObserver);
		Player player1 = new DummyPlayer();
		Player player2 = new DummyPlayer();
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		assertEquals(gameObserver.gamePoints(player1), 0);
		assertEquals(gameObserver.gamePoints(player2), 0);
		assertFalse(gameObserver.gameIsOver());
		
		_play(game, player1, new Position(0, 0), Stripe.UP);
		_play(game, player2, new Position(0, 0), Stripe.DOWN);
		_play(game, player1, new Position(1, 0), Stripe.RIGHT);
		_play(game, player2, new Position(1, 0), Stripe.UP);
		_play(game, player1, new Position(1, 1), Stripe.RIGHT);
		_play(game, player2, new Position(1, 1), Stripe.DOWN);
		_play(game, player1, new Position(0, 1), Stripe.DOWN);
		_play(game, player2, new Position(1, 0), Stripe.DOWN);
		_play(game, player1, new Position(0, 0), Stripe.LEFT);
		_play(game, player2, new Position(0, 0), Stripe.RIGHT);
		
		assertEquals(gameObserver.gamePoints(player1), 0);
		assertEquals(gameObserver.gamePoints(player2), 2);
		assertFalse(gameObserver.gameIsOver());
		
		_play(game, player2, new Position(0, 1), Stripe.LEFT);
		_play(game, player1, new Position(0, 1), Stripe.RIGHT);

		assertEquals(gameObserver.gamePoints(player1), 2);
		assertEquals(gameObserver.gamePoints(player2), 2);
		assertTrue(gameObserver.gameIsOver());
	}

	@Test
	public void testInvalidMovesInGame() {
		Game game = new Game(2);
		Player player1 = new DummyPlayer();
		game.addPlayer(player1);
		
		_play(game, player1, new Position(0, 0), Stripe.DOWN);
		try {
			Movement m = new Movement(new Position(0, 0), Stripe.DOWN);
			game.play(m);
			fail();
		} catch (InvalidMovementException e) {
		} catch (Exception e) {
			fail();
		}
		
		try {
			Movement m = new Movement(new Position(0, 1), Stripe.UP);
			game.play(m);
			fail();
		} catch (InvalidMovementException e) {
		} catch (Exception e) {
			fail();
		}
	}

	
	@Test
	public void testGame() {
		DummyGameObserver gameObserver = new DummyGameObserver();
		Game game = new Game(1);
		game.addObserver(gameObserver);
		Player player1 = new DummyPlayer();
		Player player2 = new DummyPlayer();
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		assertEquals(gameObserver.gamePoints(player1), 0);
		assertEquals(gameObserver.gamePoints(player2), 0);
		assertFalse(gameObserver.gameIsOver());
		
		/*
		 *  * *
		 *       
		 *  * *
		 */
		_play(game, player1, new Position(0, 0), Stripe.UP);
		
		/*
		 *  *-*
		 *    
		 *  * *
		 */
		assertEquals(game.currentPlayer(), player2);
		try {
			Movement m = new Movement(new Position(0, 0), Stripe.UP);
			game.play(m);
			fail();
		} catch (InvalidMovementException e) {
		} catch (Exception e) {
			fail();
		}
		_play(game, player2, new Position(0, 0), Stripe.LEFT);

		/*
		 *  *-*
		 *  |  
		 *  * *
		 */
		_play(game, player1, new Position(0, 0), Stripe.DOWN);

		/*
		 *  *-*
		 *  |  
		 *  *-*
		 */
		_play(game, player2, new Position(0, 0), Stripe.RIGHT);

		/*
		 *  *-*
		 *  |B|
		 *  *-*
		 */
		assertEquals(gameObserver.gamePoints(player1), 0);
		assertEquals(gameObserver.gamePoints(player2), 1);
		assertTrue(gameObserver.gameIsOver());
	}
	
	@Test
	public void testBoard() {
		Board b = new Board(2); // 2x2 board

		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				assertFalse(b.getCell(new Position(x, y)).isFilled());
			}
		}
		
		b.mark(new Position(0, 0), Stripe.UP, 'a');
		assertTrue(b.getCell(new Position(0, 0)).isFilled(Stripe.UP));
		assertFalse(b.getCell(new Position(0, 0)).isFilled(Stripe.DOWN));
		
		b.mark(new Position(0, 1), Stripe.UP, 'a');
		assertTrue(b.getCell(new Position(0, 1)).isFilled(Stripe.UP));
		assertTrue(b.getCell(new Position(0, 0)).isFilled(Stripe.DOWN));
		
		assertFalse(b.allCellsFilled());
		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				b.mark(new Position(x, y), Stripe.UP, 'a');
				b.mark(new Position(x, y), Stripe.RIGHT, 'a');
				b.mark(new Position(x, y), Stripe.DOWN, 'a');
				b.mark(new Position(x, y), Stripe.LEFT, 'a');
			}
		}
		assertTrue(b.allCellsFilled());
	}
	
	@Test
	public void testCell() {
		Cell c = new Cell();
		assertFalse(c.isFilled(Stripe.UP));
		assertFalse(c.isFilled(Stripe.RIGHT));
		assertFalse(c.isFilled(Stripe.DOWN));
		assertFalse(c.isFilled(Stripe.LEFT));
		
		c.mark(Stripe.UP, 'a');
		assertTrue(c.isFilled(Stripe.UP));
		assertFalse(c.isFilled(Stripe.RIGHT));
		assertFalse(c.isFilled(Stripe.DOWN));
		assertFalse(c.isFilled(Stripe.LEFT));
		assertFalse(c.isFilled());
		
		c.mark(Stripe.RIGHT, 'a');
		c.mark(Stripe.DOWN, 'a');
		c.mark(Stripe.LEFT, 'a');
		assertTrue(c.isFilled());
		assertEquals(c.getName(), 'a');
	}

}
