package simplifiedDotsIA;

import simplifiedDots.Game;
import simplifiedDots.Movement;
import simplifiedDots.Player;

public class IAPlayer implements Player {

	private String _name;
	private Game _game;
	
	public IAPlayer(String name, Game game) {
		_name = name;
		_game = game;
	}
	
	public char getInitial() {
		return _name.charAt(0);
	}

	public String getName() {
		return _name;
	}

	public Movement getNextMovement() {
		MinimaxNode minimaxTree = new MinimaxNode(_game);
//		minimaxTree.
//		game.play(best_position, best_stroke);
		return null;
	}
	
}
