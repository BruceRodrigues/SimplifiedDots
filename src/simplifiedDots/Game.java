package simplifiedDots;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	protected ArrayList<Player> _players;
	protected ArrayList<GameObserver> _observers;
	protected int _currentPlayerIndex;
	protected Board _board;
	protected HashMap<Player, Integer> _score;

	public Game(int gameSize) {
		_players = new ArrayList<Player>();
		_observers = new ArrayList<GameObserver>();
		_currentPlayerIndex = 0;
		_board = new Board(gameSize);
		_score = new HashMap<Player, Integer>();
	}

	public Game(Game game) {
		_observers = new ArrayList<GameObserver>();
		_players = new ArrayList<Player>();
		for(Player pl : game._players)
			_players.add(pl);
		_currentPlayerIndex = game._currentPlayerIndex;
		_board = new Board(game._board);
		_score = new HashMap<Player, Integer>();
		for(Player pl : _players)
			_score.put(pl, game._score.get(pl));
	}
	
	public int getPoints(Player pl) {
		return _score.get(pl);
	}

	public int boardSize() {
		return _board.size();
	}
	
	public void addObserver(GameObserver o) {
		_observers.add(o);
	}
	
	public void addPlayer(Player pl) {
		_players.add(pl);
		for(GameObserver o : _observers)
			o.playerAdded(pl);
		_score.put(pl, 0);
	}
	
	public void play(Movement m) throws InvalidMovementException {
		Player currentPlayer = _players.get(_currentPlayerIndex);
		if(currentPlayer == null) return;
		
		if(!_board.mark(m.position(), m.stripe(), currentPlayer.getInitial())) {
			throw new InvalidMovementException();
		}
		
		// TODO: This is ugly as hell :(
		// We shouldn't have to test the cell next to the one played!
		boolean playerScored = false;
		if(_board.getCell(m.position()).isFilled()) {
			playerScored = true;
			for(GameObserver o : _observers)
				o.playerPoint(currentPlayer);
			_score.put(currentPlayer, _score.get(currentPlayer) + 1);
		}
		if(_board.getCellNextTo(m.position(), m.stripe()).isFilled()) {
			playerScored = true;
			for(GameObserver o : _observers)
				o.playerPoint(currentPlayer);
			_score.put(currentPlayer, _score.get(currentPlayer) + 1);
		}
		
		if(!playerScored) {
			_currentPlayerIndex = (_currentPlayerIndex + 1) % _players.size();
		}
				
		if(_board.allCellsFilled()) {
			for(GameObserver o : _observers)
				o.gameOver();
		}
	}
	
	public boolean isOver() {
		return _board.allCellsFilled();
	}
	
	public Player currentPlayer() {
		return _players.get(_currentPlayerIndex);
	}
	
	public Cell getCell(Position p) {
		return this._board.getCell(p);
	}

	public  ArrayList<Player> getPlayers() {
		return _players;
	}
}
