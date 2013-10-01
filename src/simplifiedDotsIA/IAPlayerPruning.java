package simplifiedDotsIA;

import java.util.LinkedList;
import java.util.List;

import simplifiedDots.Game;
import simplifiedDots.InvalidMovementException;
import simplifiedDots.Movement;
import simplifiedDots.Player;
import simplifiedDots.Position;
import simplifiedDots.Stripe;

public class IAPlayerPruning implements Player {

	private String _name;
	private Game _game;
	private LinkedList<Movement> _movementsBuffer;
	private static final int DEPTH = 4;

	public IAPlayerPruning(String name, Game game) {
		_name = name;
		_game = game;
		_movementsBuffer = new LinkedList<Movement>();
	}

	public char getInitial() {
		return _name.charAt(0);
	}

	public String getName() {
		return _name;
	}

	public Movement getNextMovement() {
		System.out.println("Computador pensando...");
		if (!_movementsBuffer.isEmpty())
			return _movementsBuffer.removeFirst();

		MinMaxWithPruningNode<IAGameWrapper> minMaxTree = new MinMaxWithPruningNode<IAGameWrapper>(
				new IAGameWrapper(_game));
		_expandMinMaxTree(minMaxTree, DEPTH);
		int maxCost = minMaxTree.getCost();
		System.out.println("Nodos visitados ="
				+ minMaxTree.lastVisitedNodeCount());
		System.out.println("Cost=" + minMaxTree.getCost());
		IAGameWrapper bestGameWrapper = null;

		for (int i = 0; i < minMaxTree.getTotalChildren(); i++) {
			if (minMaxTree.getChild(i).getCost() == maxCost) {
				bestGameWrapper = minMaxTree.getChild(i).getElement();
			}
		}

		for (Movement m : bestGameWrapper.movements())
			_movementsBuffer.addLast(m);

		return _movementsBuffer.removeFirst();
	}

	private void _expandMinMaxTree(MinMaxWithPruningNode<IAGameWrapper> minMaxNode,
			int depth) {

		if (depth == 0) {
			IAGameWrapper gameWrapper = minMaxNode.getElement();
			Game game = gameWrapper.game();

			int cost = 0;
			for (Player pl : game.getPlayers()) {
				if (pl == this) cost += game.getPoints(pl);
				else cost -= game.getPoints(pl);
			}

			minMaxNode.setCost(cost);
			return;
		}

		List<MinMaxWithPruningNode<IAGameWrapper>> possibilities = _generatePossibilities(minMaxNode
				.getElement());
		if (possibilities.size() == 0) {
			IAGameWrapper gameWrapper = minMaxNode.getElement();
			Game game = gameWrapper.game();

			int cost = 0;
			for (Player pl : game.getPlayers()) {
				if (pl == this) cost += game.getPoints(pl);
				else cost -= game.getPoints(pl);
			}

			minMaxNode.setCost(cost);
			return;
		}
		for (MinMaxWithPruningNode<IAGameWrapper> child : possibilities) {
			_expandMinMaxTree(child, depth - 1);
			minMaxNode.addChild(child);
		}
	}

	private List<MinMaxWithPruningNode<IAGameWrapper>> _generatePossibilities(
			IAGameWrapper gameWrapper) {

		LinkedList<MinMaxWithPruningNode<IAGameWrapper>> possibilities = new LinkedList<MinMaxWithPruningNode<IAGameWrapper>>();
		Game game = gameWrapper.game();
		Game gameCopy = null;
		Movement m = null;

		for (int i = 0; i < game.boardSize(); i++) {
			gameCopy = new Game(game);
			m = new Movement(Position.get(i, 0), Stripe.UP);
			try {
				Player lastPlayer = gameCopy.currentPlayer();
				gameCopy.play(m);
				IAGameWrapper gameCopyWrapper = new IAGameWrapper(gameCopy);

				if (gameCopy.currentPlayer() == lastPlayer
						&& !gameCopy.isOver()) {
					List<MinMaxWithPruningNode<IAGameWrapper>> nextNodes = _generatePossibilities(gameCopyWrapper);
					for (MinMaxWithPruningNode<IAGameWrapper> n : nextNodes) {
						LinkedList<Movement> moves = new LinkedList<Movement>();
						moves.add(m);
						moves.addAll(n.getElement().movements());
						gameCopyWrapper.setMoves(moves);
						possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
								gameCopyWrapper));
					}
				} else {
					LinkedList<Movement> moves = new LinkedList<Movement>();
					moves.add(m);
					gameCopyWrapper.setMoves(moves);
					possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
							gameCopyWrapper));
				}

			} catch (InvalidMovementException e) {
			}
		}

		for (int j = 0; j < game.boardSize(); j++) {
			gameCopy = new Game(game);
			m = new Movement(Position.get(0, j), Stripe.LEFT);
			try {
				Player lastPlayer = gameCopy.currentPlayer();
				gameCopy.play(m);
				IAGameWrapper gameCopyWrapper = new IAGameWrapper(gameCopy);

				if (gameCopy.currentPlayer() == lastPlayer
						&& !gameCopy.isOver()) {
					List<MinMaxWithPruningNode<IAGameWrapper>> nextNodes = _generatePossibilities(gameCopyWrapper);
					for (MinMaxWithPruningNode<IAGameWrapper> n : nextNodes) {
						LinkedList<Movement> moves = new LinkedList<Movement>();
						moves.add(m);
						moves.addAll(n.getElement().movements());
						gameCopyWrapper.setMoves(moves);
						possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
								gameCopyWrapper));
					}
				} else {
					LinkedList<Movement> moves = new LinkedList<Movement>();
					moves.add(m);
					gameCopyWrapper.setMoves(moves);
					possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
							gameCopyWrapper));
				}

			} catch (InvalidMovementException e) {
			}
		}

		for (int i = 0; i < game.boardSize(); i++) {
			for (int j = 0; j < game.boardSize(); j++) {
				gameCopy = new Game(game);
				m = new Movement(Position.get(i, j), Stripe.RIGHT);
				try {
					Player lastPlayer = gameCopy.currentPlayer();
					gameCopy.play(m);
					IAGameWrapper gameCopyWrapper = new IAGameWrapper(gameCopy);

					if (gameCopy.currentPlayer() == lastPlayer
							&& !gameCopy.isOver()) {
						List<MinMaxWithPruningNode<IAGameWrapper>> nextNodes = _generatePossibilities(gameCopyWrapper);
						for (MinMaxWithPruningNode<IAGameWrapper> n : nextNodes) {
							LinkedList<Movement> moves = new LinkedList<Movement>();
							moves.add(m);
							moves.addAll(n.getElement().movements());
							gameCopyWrapper.setMoves(moves);
							possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
									gameCopyWrapper));
						}
					} else {
						LinkedList<Movement> moves = new LinkedList<Movement>();
						moves.add(m);
						gameCopyWrapper.setMoves(moves);
						possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
								gameCopyWrapper));
					}
				} catch (InvalidMovementException e) {
				}

				gameCopy = new Game(game);
				m = new Movement(Position.get(i, j), Stripe.DOWN);
				try {
					Player lastPlayer = gameCopy.currentPlayer();
					gameCopy.play(m);
					IAGameWrapper gameCopyWrapper = new IAGameWrapper(gameCopy);

					if (gameCopy.currentPlayer() == lastPlayer
							&& !gameCopy.isOver()) {
						List<MinMaxWithPruningNode<IAGameWrapper>> nextNodes = _generatePossibilities(gameCopyWrapper);
						for (MinMaxWithPruningNode<IAGameWrapper> n : nextNodes) {
							LinkedList<Movement> moves = new LinkedList<Movement>();
							moves.add(m);
							moves.addAll(n.getElement().movements());
							gameCopyWrapper.setMoves(moves);
							possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
									gameCopyWrapper));
						}
					} else {
						LinkedList<Movement> moves = new LinkedList<Movement>();
						moves.add(m);
						gameCopyWrapper.setMoves(moves);
						possibilities.add(new MinMaxWithPruningNode<IAGameWrapper>(
								gameCopyWrapper));
					}
				} catch (InvalidMovementException e) {
				}
			}
		}

		return possibilities;
	}

}
