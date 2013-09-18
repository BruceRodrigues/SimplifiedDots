package simplifiedDots;

import java.util.ArrayList;

public class SimplifiedDotsGame {
	ArrayList<Player> _players;
	ArrayList<SimplifiedDotsGameObserver> _observers;
	int _currentPlayerIndex;
	Board _board;
	
	public SimplifiedDotsGame(int gameSize) {
		_players = new ArrayList<Player>();
		_observers = new ArrayList<SimplifiedDotsGameObserver>();
		_currentPlayerIndex = 0;
		_board = new Board(gameSize);
	}
	
	public void addObserver(SimplifiedDotsGameObserver o) {
		_observers.add(o);
	}
	
	public void addPlayer(Player pl) {
		_players.add(pl);
		for(SimplifiedDotsGameObserver o : _observers) {
			o.playerAdded(pl);
		}
	}
	
	public void play(Position p, Stripe s) throws InvalidMovementException {
		Player currentPlayer = _players.get(_currentPlayerIndex);
		if(currentPlayer == null) return;
		
		if(!_board.mark(p, s, currentPlayer.getInitial())) {
			throw new InvalidMovementException();
		}
		
		// TODO: This is ugly as hell :(
		// We shouldn't have to test the cell next to the one played!
		boolean playerScored = false;
		if(_board.getCell(p).isFilled()) {
			playerScored = true;
			for(SimplifiedDotsGameObserver o : _observers) {
				o.playerPoint(currentPlayer);
			}
		}
		if(_board.getCellNextTo(p, s).isFilled()) {
			playerScored = true;
			for(SimplifiedDotsGameObserver o : _observers) {
				o.playerPoint(currentPlayer);
			}
		}
		
		if(!playerScored) {
			_currentPlayerIndex = (_currentPlayerIndex + 1) % _players.size();
		}
				
		if(_board.allCellsFilled()) {
			for(SimplifiedDotsGameObserver o : _observers) {
				o.gameOver();
			}
		}
	}
	
	public Player currentPlayer() {
		return _players.get(_currentPlayerIndex);
	}
}
