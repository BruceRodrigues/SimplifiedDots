package simplifiedDotsIA;

public class Tree {
	
	
	private Node _root;
	
	public Tree(Node root) {
		this._root = root;
	}
	
	public void setRoot(Node root) {
		this._root = root;
	}
	
	public Node getRoot() {
		return this._root;
	}
	
	public int getTotalNodes() {
		return this._root.getTotalChildren();
	}
	
	
}
