package simplifiedDots;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class SimplifiedDotsCreateTreeTest {
	
	public class State {
		
		public HashMap<Position,Cell> p = new HashMap<Position,Cell>();
		
		State(HashMap<Position,Cell> p ) { this.p = p;}
		
		public Cell getFirst() { 
			Collection<Cell> l = p.values();
			Object[] c = l.toArray();
			return (Cell)c[0];
		}
		
		public Cell getLast() { 
			Collection<Cell> l = p.values();
			Object[] c = l.toArray();
			return (Cell)c[99];
		}
		
	}

	@Test
	public void test() {
		
		//start
		HashMap<Position,Cell> p = new HashMap<Position,Cell>();
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Cell c = new Cell();
				Position pos = new Position(i,j);
				p.put(pos, c);
			}
		}
		State state = new State(p);
		Node root = new Node(state);
		Tree t = new Tree(root);
		Frame frame = new Frame();
		List<HashMap<Position,Cell>> list = frame.createAllPossibilities(p);
		for (HashMap<Position,Cell> h : list) {
			State s = new State(h);
			Node n = new Node(s);
			root.addChild(n);
		}
		
		//At this point, the tree should have all the possibilities from the initial position
		assertTrue(root.getTotalChildren() == 100);
		Cell a = ((State)root.getChild(0).getElement()).getFirst();
		Cell b = ((State)root.getChild(99).getElement()).getLast();
		assertTrue(a.isFilled(Stripe.UP));
		assertTrue(b.isFilled());
		
		
	}

}
