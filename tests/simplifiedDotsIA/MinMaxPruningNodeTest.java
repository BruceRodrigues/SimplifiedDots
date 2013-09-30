package simplifiedDotsIA;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class MinMaxPruningNodeTest {

	@Test
	public void MinMaxPrunningTestCase1() {
		/**
		 *        ______(   )________              root
		 *       /                   \         
		 *     (  6)              _(   )__         nodes 1 2
		 *                       /        \     
		 *                    (  5)    _(   )_     nodes 3 4
		 *                            /       \
		 *                          (  8)   (  9)  nodes 5 6
		 */
		MinMaxWithPruningNode<Integer> root =
				new MinMaxWithPruningNode<Integer>(null);
		MinMaxNode<Integer> rootMinMax = new MinMaxNode<Integer>(null);
		
		MinMaxWithPruningNode<Integer> node1 =
				new MinMaxWithPruningNode<Integer>(null, 6);
		MinMaxWithPruningNode<Integer> node2 =
				new MinMaxWithPruningNode<Integer>(null);
		
		MinMaxWithPruningNode<Integer> node3 =
				new MinMaxWithPruningNode<Integer>(null, 5);
		MinMaxWithPruningNode<Integer> node4 =
				new MinMaxWithPruningNode<Integer>(null);

		MinMaxWithPruningNode<Integer> node5 =
				new MinMaxWithPruningNode<Integer>(null, 8);
		MinMaxWithPruningNode<Integer> node6 =
				new MinMaxWithPruningNode<Integer>(null, 9);

		root.addChild(node1);
		root.addChild(node2);
		rootMinMax.addChild(node1);
		rootMinMax.addChild(node2);
		
		node2.addChild(node3);
		node2.addChild(node4);
		
		node4.addChild(node5);
		node4.addChild(node6);
		
		/**
		 *        ______(  6)________              root
		 *       /                   \         
		 *     (  6)              _(  5)__         nodes 1 2
		 *                       /        \--> not visited!    
		 *                    (  5)    _(---)_     nodes 3 4
		 *                            /       \
		 *                          (  8)   (  9)  nodes 5 6
		 */
		assertEquals((Integer)6, root.getCost());
		assertEquals(4, root.lastVisitedNodeCount());
		
		// MinMax Algorithm visits more nodes
		assertEquals((Integer)6, rootMinMax.getCost());
		assertEquals(7, rootMinMax.lastVisitedNodeCount());
	}
	
	@Test
	public void MinMaxPrunningTestCase2() {
		/**
		 *                                     root
		 *             ____________________(   )____________________              root
		 *            / 1                    | 2                    \  3          
		 *       ___(   )___            ___(   )___             ___(   )___       nodes 1 2 3
		 *      / 4         \ 5        / 6         \ 7         / 8         \ 9    
		 *    (   )       (   )      (   )       (   )       (   )       (   )    nodes 4 5 6 7 8 9
		 *   /10   \11   /12   \13  /14   \15   /16   \17   /18   \19   /20   \21 
		 * (  4) (  6) (  7) (  9)(  1) (  2) (  0) (  1) (  8) (  1) (  9) (  2) nodes 10 to 21
		 */
		MinMaxWithPruningNode<Integer> root =
				new MinMaxWithPruningNode<Integer>(null);
		
		ArrayList<MinMaxWithPruningNode<Integer>> nodes = new ArrayList<MinMaxWithPruningNode<Integer>>();
		nodes.add(root);
		for(int i = 1; i <= 21; i++)
			nodes.add(new MinMaxWithPruningNode<Integer>(null));

		int[] values = {4, 6, 7, 9, 1, 2, 0, 1, 8, 1, 9, 2};
		for(int i = 0; i < 11; i++)
			nodes.get(10 + i).setCost(values[i]);
		
		root.addChild(nodes.get(1));
		root.addChild(nodes.get(2));
		root.addChild(nodes.get(3));

		int child = 4;
		for(int parent = 1; parent <= 9; parent++) {
			nodes.get(parent).addChild(nodes.get(child++));
			nodes.get(parent).addChild(nodes.get(child++));
		}

		/**
		 *                                     root
		 *             ____________________(  8)____________________              root
		 *            /                      |                      \             
		 *       ___(  6)___            ___(  2)___             ___(  8)___       nodes 1 2 3
		 *      /           \          /           X           /           \      
		 *    (  6)       (  7)      (  2)       (---)       (  8)       (  9)    nodes 4 5 6 7 8 9
		 *   /     \     /     X    /     \     /     \     /     \     /     X   
		 * (  4) (  6) (  7) (---)(  1) (  2) (---) (---) (  8) (  1) (  9) (---) nodes 10 to 21
		 */
		assertEquals((Integer)8, root.getCost());
		assertEquals(17, root.lastVisitedNodeCount());
	}

	@Test
	public void MinMaxPrunningTestCase3() {
		/**
		 *                                                    root
		 *                           _____________________(   )_____________________
		 *                          / 1                                             \ 2
		 *               _________(   )________                           ________(   )__________
		 *              / 3                    \ 4                       / 5                     \ 6
		 *         ___(   )___            ___(   )___               ___(   )___             ___(   )___
		 *        / 7         \ 8        / 9          \10          /11         \12         /13         \14
		 *      (   )       (   )      (   )        (   )        (   )       (   )       (   )       (   )
		 *     /15   \16   /17   \18  /19   \20    /21   \22    /23   \24   /25   \26   /27   \28   /29   \30
		 *   ( 20) ( 33) (-45) ( 31)( 24) ( 25)  (-10) ( 20)  ( 46) (-25) ( 18) (-42) ( 24) (-19) ( 35) (-41)
		 */
		MinMaxWithPruningNode<Integer> root =
				new MinMaxWithPruningNode<Integer>(null);
		
		ArrayList<MinMaxWithPruningNode<Integer>> nodes = new ArrayList<MinMaxWithPruningNode<Integer>>();
		nodes.add(root);
		for(int i = 1; i <= 30; i++)
			nodes.add(new MinMaxWithPruningNode<Integer>(null));

		int[] values = {20, 33, -45, 31, 24, 25, -10, 20, 46, -25, 18, -42, 24, -19, 35, -41};
		for(int i = 0; i < 16; i++)
			nodes.get(15 + i).setCost(values[i]);
		
		root.addChild(nodes.get(1));
		root.addChild(nodes.get(2));

		int child = 3;
		for(int parent = 1; parent <= 14; parent++) {
			nodes.get(parent).addChild(nodes.get(child++));
			nodes.get(parent).addChild(nodes.get(child++));
		}

		/**
		 *                           _____________________( 20)_____________________
		 *                          /                                               \ 
		 *               _________( 20)________                           ________( 18)__________
		 *              /                      \                         /                       X 
		 *         ___( 20)___            ___( 24)____              ___( 18)___             ___(---)___
		 *        /           \          /            X            /           \           /           \  
		 *      ( 20)       (-45)      ( 24)        (---)        (-25)       ( 18)       (---)       (---)
		 *     /     \     /     X    /     \      /     \      /     \     /     \     /     \     /     \  
		 *   ( 20) ( 33) (-45) (---)( 24) ( 25)  (---) (---)  ( 46) (-25) ( 18) (-42) (---) (---) (---) (---)
		 */
		assertEquals((Integer)20, root.getCost());
		assertEquals(19, root.lastVisitedNodeCount());
	}

}