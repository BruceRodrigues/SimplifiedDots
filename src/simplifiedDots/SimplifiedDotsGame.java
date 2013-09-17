package simplifiedDots;

import java.util.ArrayList;

public class SimplifiedDotsGame {
	ArrayList<Player> _players;
	int _currentPlayerIndex;
	Board _board;
	
	public SimplifiedDotsGame(int gameSize) {
		_players = new ArrayList<Player>();
		_currentPlayerIndex = 0;
		_board = new Board(gameSize);
	}
	
	public void addPlayer(Player pl) {
		_players.add(pl);
	}
	
	public void play(Position p, Stripe s) {
		Player currentPlayer = _players.get(_currentPlayerIndex);
		if(currentPlayer == null) return;
		
		if(!_board.mark(p, s, currentPlayer.getInitial())) {
			// Invalid movement
		}
		
		if(!_board.getCell(p).isFilled()) {
			// Cell not filled means player didn't scored points.
			// WARNING: maybe the cell next to it became filled!!
			_currentPlayerIndex = (_currentPlayerIndex + 1) % _players.size();
		}
		
		if(_board.allCellsFilled()) {
			// Game over
		}
	}
	
	public Player currentPlayer() {
		return _players.get(_currentPlayerIndex);
	}
}
