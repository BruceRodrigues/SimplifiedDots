package simplifiedDotsIA;

import java.util.ArrayList;
import java.util.List;

public class Node<E,D> {
	
	protected Node<E, D> _parent;
	protected List<Node<E,D>> _children;
	protected D _heuristic;
	protected Integer _cost;
	protected E _element;
	protected int _totalChildren;
	
	public Node() {
		this._children = new ArrayList<Node<E,D>>();
		this._totalChildren = 0;
	}
	
	public Node(E element) {
		this();
		this._element = element;
	}
	
	public Node(E element, Integer cost) {
		this(element);
		this._cost = cost;
	}
	
	public Node(E element, Integer cost, D heuristic) {
		this(element, cost);
		this._heuristic = heuristic;
	}
	
	public void setParent(Node<E,D> node) {
		_parent = node;
	}
	
	public Node getParent() {
		return this._parent;
	}
	
	public void addChild(Node node) {
		node.setParent(this);
		this._children.add(node);
			node.getParent().setTotalChildren(node.getParent().getTotalChildren()+1);
	}
	
	public void removeChild(Node node) {
		int index = this._children.indexOf(node);
		this._children.remove(index);
	}
	
	public List<Node<E,D>> getChildren() {
		return this._children;
	}
	
	public Node getChild(Node node) {
		int index = this._children.indexOf(node);
		return this._children.get(index);
	}
	
	public Node<E, D> getChild(int index) {
		return this._children.get(index);
	}
	
	public E getElement() {
		return this._element;
	}
	
	public void setElement(E element) {
		this._element = element;
	}
	
	public D getHeuristic() {
		return this._heuristic;
	}
	
	public void setHeuristic(D heuristic) {
		this._heuristic = heuristic;
	}
	
	public Integer getCost() {
		return this._cost;
	}
	
	public void setCost(Integer cost) {
		this._cost = cost;
	}
	
	public Node getLessCostChild() {
		if(this._children.isEmpty()) {
			return null;
		}
		Node less = _children.get(0);
		for ( Node node : this._children) {
			if(node.getCost() < less.getCost()) {
				less = node;
			}
		}
		return less;
	}
	
	private void setTotalChildren(int i) {
		this._totalChildren = i;
	}
	
	public int getTotalChildren() {
		return this._totalChildren;
	}

}
