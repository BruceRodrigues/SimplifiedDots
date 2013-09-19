package simplifiedDots;

public interface GameObserver {
	/**
	 * When a new player is inserted, his points is initialized with 0.
	 */
	public void playerAdded(Player pl);

	/**
	 * Called when the game is over. For test purposes, this just sets the
	 * _gameIsOver flag to true.
	 */
	public void gameOver();
	
	/**
	 * When a player scores a point, this method is called, so we can update
	 * his points.
	 */
	public void playerPoint(Player pl);
}
