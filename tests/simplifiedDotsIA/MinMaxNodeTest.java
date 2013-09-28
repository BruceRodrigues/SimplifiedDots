package simplifiedDotsIA;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxNodeTest {

	@Test
	public void MinMaxTestCase1() {
		/**
		 *        ______(   )________          root
		 *       /        |          \         
		 *     (   )    (   )     _(   )_      nodes 1 2 3
		 *    /     \     |      /   |   \     
		 *  (  3) ( 10) (  4) (  5)(  6)(  7)  nodes 4 5 6 7 8 9
		 */
		MinMaxNode<Integer> root = new MinMaxNode<Integer>();
		
		MinMaxNode<Integer> node1 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node2 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node3 = new MinMaxNode<Integer>();
		
		MinMaxNode<Integer> node4 = new MinMaxNode<Integer>(3);
		MinMaxNode<Integer> node5 = new MinMaxNode<Integer>(10);
		MinMaxNode<Integer> node6 = new MinMaxNode<Integer>(4);
		MinMaxNode<Integer> node7 = new MinMaxNode<Integer>(5);
		MinMaxNode<Integer> node8 = new MinMaxNode<Integer>(6);
		MinMaxNode<Integer> node9 = new MinMaxNode<Integer>(7);
		
		root.addChild(node1);
		root.addChild(node2);
		root.addChild(node3);
		
		node1.addChild(node4);
		node1.addChild(node5);
		
		node2.addChild(node6);
		
		node3.addChild(node7);
		node3.addChild(node8);
		node3.addChild(node9);

		/**
		 *        ______(  5)________          MAX
		 *       /        |          \         
		 *     (  3)    (  4)     _(  5)_      MIN
		 *    /     \     |      /   |   \     
		 *  (  3) ( 10) (  4) (  5)(  6)(  7)  MAX
		 */
		assertEquals((Integer)5, root.getCost());
	}

	@Test
	public void MinMaxTestCase2() {
		/**
		 *        ______(   )___________________          
		 *       /               \              \        
		 *     (   )___        (   )     _____(   )_______   
		 *    /        \         |      /       |         \     
		 *  (   )    (   )     (  5)  (  2)   (   )     (   )
		 *    |      /   \                   /     \      |
		 *  ( 11)  (  7)(  3)              (  3) (  3)  (  1)
		 */
		MinMaxNode<Integer> root = new MinMaxNode<Integer>();
		
		MinMaxNode<Integer> node1 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node2 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node3 = new MinMaxNode<Integer>();

		MinMaxNode<Integer> node4 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node5 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node6 = new MinMaxNode<Integer>(5);
		MinMaxNode<Integer> node7 = new MinMaxNode<Integer>(2);
		MinMaxNode<Integer> node8 = new MinMaxNode<Integer>();
		MinMaxNode<Integer> node9 = new MinMaxNode<Integer>();

		MinMaxNode<Integer> node10 = new MinMaxNode<Integer>(11);
		MinMaxNode<Integer> node11 = new MinMaxNode<Integer>(7);
		MinMaxNode<Integer> node12 = new MinMaxNode<Integer>(3);
		MinMaxNode<Integer> node13 = new MinMaxNode<Integer>(3);
		MinMaxNode<Integer> node14 = new MinMaxNode<Integer>(3);
		MinMaxNode<Integer> node15 = new MinMaxNode<Integer>(1);

		root.addChild(node1);
		root.addChild(node2);
		root.addChild(node3);
		
		node1.addChild(node4);
		node1.addChild(node5);
		
		node2.addChild(node6);
		
		node3.addChild(node7);
		node3.addChild(node8);
		node3.addChild(node9);
		
		node4.addChild(node10);
		
		node5.addChild(node11);
		node5.addChild(node12);

		node8.addChild(node13);
		node8.addChild(node14);
		
		node9.addChild(node15);
		
		/**
		 *        ______(  7)___________________                MAX
		 *       /               \              \               
		 *     (  7)___        (  5)     _____(  1)_______      MIN
		 *    /        \         |      /       |         \     
		 *  ( 11)    (  7)     (  5)  (  2)   (  3)     (  1)   MAX
		 *    |      /   \                   /     \      |     
		 *  ( 11)  (  7)(  3)              (  3) (  3)  (  1)   MIN
		 */
		assertEquals((Integer)7, root.getCost());
	}

}
