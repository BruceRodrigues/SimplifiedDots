package simplifiedDotsIA;

import java.util.ArrayList;
import java.util.List;

import simplifiedDots.Game;
import simplifiedDots.GameObserver;
import simplifiedDots.Movement;
import simplifiedDots.Player;
import simplifiedDots.Position;
import simplifiedDots.Stripe;

public class MinimaxNode extends Node<Game, Integer> {
	
	private Movement _lastMovement;
	private class MinimaxGameObserver implements GameObserver {

		private int _points;
		public MinimaxGameObserver() { _points = 0; }
		public void playerAdded(Player pl) {}
		public void gameOver() {}
		public void playerPoint(Player pl) { _points++; }
		public int points() { return _points; }
	}

	public MinimaxNode(Game game) {
		super(game, 0);
		_lastMovement = null;
	}

	public MinimaxNode(Game game, int cost, Movement lastMovement) {
		super(game, cost);
		_lastMovement = lastMovement;
	}
	
	public Movement getBestMovement(int maxDepth) {
		return getMaxMovement(maxDepth)._lastMovement;
	}
	
	public MinimaxNode getMaxMovement(int maxDepth) {
		assert(maxDepth > 0);
		if(maxDepth == 0) return this;
		
		int max = Integer.MIN_VALUE;
		MinimaxNode chosen = null;
		for(MinimaxNode m : createAllPossibilities(+1)) {
			Game chosenGame = m.getElement();
			Game thisGame = getElement();
			MinimaxNode child = null;
			if(chosenGame.currentPlayer() == thisGame.currentPlayer()) {
				child = m.getMaxMovement(maxDepth - 1);
			} else {
				child = m.getMinMovement(maxDepth - 1);
			}
			if(max < child.getCost()) {
				max = child.getCost();
				chosen = child;
			}
		}
		return chosen;
	}

	public MinimaxNode getMinMovement(int maxDepth) {
		assert(maxDepth > 0);
		if(maxDepth == 0) return this;
		
		int min = Integer.MAX_VALUE;
		MinimaxNode chosen = null;
		for(MinimaxNode m : createAllPossibilities(-1)) {
			Game chosenGame = m.getElement();
			Game thisGame = getElement();
			MinimaxNode child = null;
			if(chosenGame.currentPlayer() == thisGame.currentPlayer()) {
				child = m.getMinMovement(maxDepth - 1);
			} else {
				child = m.getMaxMovement(maxDepth - 1);
			}
			if(min > child.getCost()) {
				min = child.getCost();
				chosen = child;
			}
		}
		return chosen;
	}

	private List<MinimaxNode> createAllPossibilities(int multiplier) {
		List<MinimaxNode> possibilities = new ArrayList<MinimaxNode>();
		Game gameClone;
		
		for (int i = 0; i < _element.boardSize(); i++) {
			gameClone = new Game(_element);
			try {
				Movement m = new Movement(new Position(i, 0), Stripe.UP);
				MinimaxGameObserver o = new MinimaxGameObserver();
				gameClone.addObserver(o);
				gameClone.play(m);
				possibilities.add(new MinimaxNode(gameClone, _cost + o.points() * multiplier, m));
			} catch(Exception e) {}
		}

		for (int j = 0; j < _element.boardSize(); j++) {
			gameClone = new Game(_element);
			try {
				Movement m = new Movement(new Position(0, j), Stripe.LEFT);
				MinimaxGameObserver o = new MinimaxGameObserver();
				gameClone.addObserver(o);
				gameClone.play(m);
				possibilities.add(new MinimaxNode(gameClone, _cost + o.points() * multiplier, m));
			} catch(Exception e) {}
		}
		
		for (int i = 0; i < _element.boardSize(); i++) {
			for (int j = 0; j < _element.boardSize(); j++) {
				gameClone = new Game(_element);
				try {
					Movement m = new Movement(new Position(i, j), Stripe.RIGHT);
					MinimaxGameObserver o = new MinimaxGameObserver();
					gameClone.addObserver(o);
					gameClone.play(m);
					possibilities.add(new MinimaxNode(gameClone, _cost + o.points() * multiplier, m));
				} catch(Exception e) {}
				
				gameClone = new Game(_element);
				try {
					Movement m = new Movement(new Position(i, j), Stripe.DOWN);
					MinimaxGameObserver o = new MinimaxGameObserver();
					gameClone.addObserver(o);
					gameClone.play(m);
					possibilities.add(new MinimaxNode(gameClone, _cost + o.points() * multiplier, m));
				} catch(Exception e) {}
			}
		}
		
		return possibilities;
	}

}
