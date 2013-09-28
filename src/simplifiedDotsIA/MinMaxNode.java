package simplifiedDotsIA;

public class MinMaxNode<T> extends Node<T, Integer> {
	
	private enum MinMax { MIN, MAX };
	
	public MinMaxNode(Integer cost) {
		_cost = cost;
	}

	public MinMaxNode() {
		_cost = null;
	}
	
	public Integer getCost() {
		if(_cost != null) return _cost;
		return _getCostAux(MinMax.MAX);
	}
	
	private Integer _getCostAux(MinMax type) {
		if(getChildren().isEmpty())
			return _cost;
		
		int cost =
			((MinMaxNode<T>) getChild(0))._getCostAux(_toggleState(type));
		for(int i = 1; i < getChildren().size(); i++) {
			MinMaxNode<T> tmp = (MinMaxNode<T>) getChild(i);
			int new_cost = tmp._getCostAux(_toggleState(type));
			cost = _applyStateRule(type, cost, new_cost);
		}
		setCost(cost);
		return cost;
	}

	private int _applyStateRule(MinMax type, int cost, int new_cost) {
		if(type == MinMax.MAX) return cost > new_cost ? cost : new_cost;
		return cost < new_cost ? cost : new_cost;
	}

	private MinMax _toggleState(MinMax type) {
		return (type == MinMax.MAX) ? MinMax.MIN : MinMax.MAX;
	}
	

}
