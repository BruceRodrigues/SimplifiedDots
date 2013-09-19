package simplifiedDotsIA;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import simplifiedDots.Cell;
import simplifiedDotsIA.Node;
import simplifiedDotsIA.Tree;

public class SimplifiedDotsTreeTest {

	@Test
	public void test() {
		Cell a = new Cell();
		Cell b = new Cell();
		Cell c = new Cell();
		Cell d = new Cell();
		
		Node<Cell, Integer> na = new Node<Cell, Integer>(a);
		Node<Cell, Integer> nb = new Node<Cell, Integer>(b);
		Node<Cell, Integer> nc = new Node<Cell, Integer>(c);
		Node<Cell, Integer> nd = new Node<Cell, Integer>(d);
		
		Tree tree = new Tree(na);
		na.addChild(nb);
		nb.addChild(nc);
		nb.addChild(nd);
		tree.setRoot(nb);
		
		for (Node<Cell, Integer> n : na.getChildren()) {
			assertEquals(nb, n);
		}
		assertEquals(na, nb.getParent());
		
	}

}
