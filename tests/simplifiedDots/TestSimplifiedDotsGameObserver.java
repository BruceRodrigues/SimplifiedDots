package simplifiedDots;

import java.util.HashMap;

public class TestSimplifiedDotsGameObserver implements SimplifiedDotsGameObserver {

	private HashMap<Player, Integer> _gamePoints;
	private boolean _gameIsOver;
	
	public TestSimplifiedDotsGameObserver() {
		_gameIsOver = false;
		_gamePoints = new HashMap<Player, Integer>();
	}
	
	/**
	 * Return the points of the player pl.
	 */
	public int gamePoints(Player pl) {
		return _gamePoints.get(pl);
	}
	
	/**
	 * Returns true if the game is over. I know that this method name suck,
	 * since we also have "gameOver" method... New name suggestions are
	 * welcome.
	 */
	public boolean gameIsOver() {
		return _gameIsOver;
	}

	
	/**
	 * When a new player is inserted, his points is initialized with 0.
	 */
	public void playerAdded(Player pl) {
		_gamePoints.put(pl, 0);
	}

	/**
	 * Called when the game is over. For test purposes, this just sets the
	 * _gameIsOver flag to true.
	 */
	public void gameOver() {
		_gameIsOver = true;
	}

	/**
	 * When a player scores a point, this method is called, so we can update
	 * his points.
	 */
	public void playerPoint(Player pl) {
		_gamePoints.put(pl, _gamePoints.get(pl) + 1);
	}

}
