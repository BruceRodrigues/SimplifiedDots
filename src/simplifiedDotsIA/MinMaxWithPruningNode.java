package simplifiedDotsIA;

public class MinMaxWithPruningNode<T> extends MinMaxNode<T> {
	
	public MinMaxWithPruningNode(T element, Integer cost) {
		super(element, cost);
	}

	public MinMaxWithPruningNode(T element) {
		super(element);
	}
	
	public Integer getCost() {
		if(_cost != null) return _cost;
		return _getCostAux(this, MinMax.MAX, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	protected Integer _getCostAux(MinMaxWithPruningNode<T> root, MinMax type, int alpha, int beta) {
		root._visitedNodeCount++;
		
		if(getChildren().isEmpty())
			return _cost;
		
		int cost =
			((MinMaxWithPruningNode<T>) getChild(0))._getCostAux(
			root, _toggleState(type), alpha, beta);

		if(_shouldUpdateAlphaBeta(type, alpha, beta, cost)) {
			if(type == MinMax.MAX) alpha = cost;
			if(type == MinMax.MIN) beta  = cost;
		}
		
		if(_shouldContinueSearching(alpha, beta)) {
			for(int i = 1; i < getChildren().size(); i++) {
				MinMaxWithPruningNode<T> tmp =
					(MinMaxWithPruningNode<T>) getChild(i);
				int new_cost = tmp._getCostAux(root, _toggleState(type), alpha, beta);
				cost = _applyStateRule(type, cost, new_cost);
				
				if(_shouldUpdateAlphaBeta(type, alpha, beta, cost)) {
					if(type == MinMax.MAX) alpha = cost;
					if(type == MinMax.MIN) beta  = cost;
				}
	
				if(!_shouldContinueSearching(alpha, beta)) break;
			}
		}
		setCost(cost);
		return cost;
	}

	private boolean _shouldContinueSearching(int alpha, int beta) {
		return (alpha < beta);
	}

	private boolean _shouldUpdateAlphaBeta(
			simplifiedDotsIA.MinMaxNode.MinMax type, int alpha, int beta,
			int cost) {
		
		if(type == MinMax.MAX) return (cost > alpha);
		if(type == MinMax.MIN) return (cost < beta);
		
		assert false;
		return false;
	}
}
