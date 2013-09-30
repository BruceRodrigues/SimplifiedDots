package simplifiedDotsIA;

public class MinMaxNode<T> extends Node<T, Integer> {
	
	protected enum MinMax { MIN, MAX };
	protected int _visitedNodeCount;
	
	public MinMaxNode(T element, Integer cost) {
		super(element);
		_cost = cost;
		_visitedNodeCount = 0;
	}

	public MinMaxNode(T element) {
		super(element);
		_cost = null;
		_visitedNodeCount = 0;
	}

	public int lastVisitedNodeCount() {
		return _visitedNodeCount;
	}
	
	public Integer getCost() {
		if(_cost != null) return _cost;
		return _getCostAux(this, MinMax.MAX);
	}
	
	protected Integer _getCostAux(MinMaxNode<T> root, MinMax type) {
		root._visitedNodeCount++;

		if(getChildren().isEmpty())
			return _cost;
		
		int cost =
			((MinMaxNode<T>) getChild(0))._getCostAux(root, _toggleState(type));
		for(int i = 1; i < getChildren().size(); i++) {
			MinMaxNode<T> tmp = (MinMaxNode<T>) getChild(i);
			int new_cost = tmp._getCostAux(root, _toggleState(type));
			cost = _applyStateRule(type, cost, new_cost);
		}
		setCost(cost);
		return cost;
	}

	protected int _applyStateRule(MinMax type, int cost, int new_cost) {
		if(type == MinMax.MAX) return cost > new_cost ? cost : new_cost;
		return cost < new_cost ? cost : new_cost;
	}

	protected MinMax _toggleState(MinMax type) {
		return (type == MinMax.MAX) ? MinMax.MIN : MinMax.MAX;
	}
	

}
