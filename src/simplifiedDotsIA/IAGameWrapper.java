package simplifiedDotsIA;

import java.util.LinkedList;

import simplifiedDots.Game;
import simplifiedDots.Movement;

public class IAGameWrapper {
	
	private Game _game;
	private LinkedList<Movement> _movements;

	public IAGameWrapper(Game game) {
		_game = game;
		_movements = new LinkedList<Movement>();
	}

	public LinkedList<Movement> movements() {
		return _movements;
	}

	public Game game() {
		return _game;
	}

	public void setMoves(LinkedList<Movement> moves) {
		_movements = moves;
	}
}
