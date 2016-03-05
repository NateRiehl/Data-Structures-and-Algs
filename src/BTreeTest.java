import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
/**
 *Test class for BTree and BTreeNode. Further test-cases in BTree/BTreeNode classes
 *@author Nate Riehl
 *CS216, Fall 2015
 */
public class BTreeTest {
	BTree<Integer> tree23;
	BTree<Integer> tree3;
	BTree<Integer> tree4;
	IntComparatorInc cmp;
	BTreeNode<Integer> n3_1;  
	BTreeNode<Integer> n3_2;
	BTreeNode<Integer> n3_3;
	BTreeNode<Integer> n4_1;  
	BTreeNode<Integer> n4_2;
	BTreeNode<Integer> n4_3;
	ArrayList<Integer> l1;
	ArrayList<Integer> l2;
	@Before
	public void setUp() throws Exception {
		cmp = new IntComparatorInc();
		tree23 = new BTree<Integer>(cmp, 3);
		tree3 = new BTree<Integer>(cmp, 3);
		tree4 = new BTree<Integer>(cmp, 4);

		n4_1 = new BTreeNode<Integer>(null, new IntComparatorInc(), 4);  // degree 4 btreenode
		n4_2 = new BTreeNode<Integer>(n4_1, new IntComparatorInc(), 4); // degree 4 btreenode
		n4_3 = new BTreeNode<Integer>(n4_1, new IntComparatorInc(), 4); // degree 4 btreenode

		l1 = new ArrayList<Integer>(); // 3, 17, 40
		l2 = new ArrayList<Integer>(); // 2, 3, 17, 40
		l1.add(3); l1.add(17); l1.add(40);
		l2.add(2); l2.addAll(l1);

		n3_1 = new BTreeNode<Integer>(null, new IntComparatorInc(), 3);  // degree 3 btreenode
		n3_2 = new BTreeNode<Integer>(null, new IntComparatorInc(), 3); // degree 3 btreenode
		n3_3 = new BTreeNode<Integer>(null, new IntComparatorInc(), 3); // degree 3 btreenode
	}

	@Test
	public void testAddBTree(){		
		/**
		 * Internally this will test stepDown(), findLeaf(), BTreeNode add(), BTreeNode isOverflow(),
		 * BTreeNode split(), BTreeNode addChild(), BTreeNode getters/setters
		 */

		/** To add: 2 17 40 9 20 38 93 8 */

		tree23.add(2);
		assertEquals("[ 2 ] " ,tree23.toString());
		tree23.add(17);
		assertEquals("[ 2 17 ] " ,tree23.toString());
		tree23.add(40);
		assertEquals("[ 17 ] [ 2 ] [ 40 ] " ,tree23.toString()); //isOverflow(), split()
		tree23.add(9);
		assertEquals("[ 17 ] [ 2 9 ] [ 40 ] " ,tree23.toString());
		tree23.add(20);
		assertEquals("[ 17 ] [ 2 9 ] [ 20 40 ] " ,tree23.toString());
		tree23.add(38);
		assertEquals("[ 17 38 ] [ 2 9 ] [ 20 ] [ 40 ] " ,tree23.toString());//isOverflow(), split()
		tree23.add(93);
		assertEquals("[ 17 38 ] [ 2 9 ] [ 20 ] [ 40 93 ] " ,tree23.toString());
		tree23.add(8);
		assertEquals("[ 17 ] [ 8 ] [ 38 ] [ 2 ] [ 9 ] [ 20 ] [ 40 93 ] " ,tree23.toString());//isOverflow(), split()

		/** To add: 40, 3, 17, 2, 9, 5, 25, 10, 8, 50, 93, 100, 95, 6 */

		tree4.add(40);
		tree4.add(3);
		tree4.add(17);
		assertEquals("[ 3 17 40 ] " ,tree4.toString());
		tree4.add(2);
		assertEquals("[ 17 ] [ 2 3 ] [ 40 ] " ,tree4.toString());
		tree4.add(9);
		assertEquals("[ 17 ] [ 2 3 9 ] [ 40 ] " ,tree4.toString());
		tree4.add(5);
		assertEquals("[ 5 17 ] [ 2 3 ] [ 9 ] [ 40 ] " ,tree4.toString());
		tree4.add(25);
		assertEquals("[ 5 17 ] [ 2 3 ] [ 9 ] [ 25 40 ] " ,tree4.toString());
		tree4.add(10);
		tree4.add(8);
		tree4.add(50);
		tree4.add(93);
		tree4.add(100);
		tree4.add(95);
		tree4.add(6);
		assertEquals("[ 17 ] [ 5 9 ] [ 50 ] [ 2 3 ] [ 6 8 ] [ 10 ] [ 25 40 ] [ 93 95 100 ] " ,tree4.toString());
	}

	@Test
	public void testNodeBasic(){
		/**
		 * Tests provided by Professor Kim (10/26/16)
		 */
		assertEquals("[ ]", n4_1.toString());
		assertNull(n4_1.getParent());
		assertFalse(n4_1.isOverflow());
//		assertTrue(n4_1.isUnderflow());
		assertEquals(n4_1, n4_2.getParent());

		// add values to the lists
		n4_1.setData(l1);
		assertEquals("[ 3 17 40 ]", n4_1.toString());
		assertFalse(n4_1.isOverflow());
		assertTrue(n4_1.contains(new Integer(3)));
		assertTrue(n4_1.contains(new Integer(17)));
		assertFalse(n4_1.contains(new Integer(2)));
		assertFalse(n4_1.contains(new Integer(20)));
	}
	@Test
	public void testRemove(){
		/**
		 * Calling remove(target) on BTree will test remove()/join()/rotate()/handleUnderflow()
		 */
		/** To add: 2 17 40 9 20 38 93 8 */
		tree23.add(2);
		assertEquals("[ 2 ] " ,tree23.toString());
		tree23.add(17);
		assertEquals("[ 2 17 ] " ,tree23.toString());
		tree23.add(40);
		assertEquals("[ 17 ] [ 2 ] [ 40 ] " ,tree23.toString()); //isOverflow(), split()
		tree23.add(9);
		assertEquals("[ 17 ] [ 2 9 ] [ 40 ] " ,tree23.toString());
		tree23.add(20);
		assertEquals("[ 17 ] [ 2 9 ] [ 20 40 ] " ,tree23.toString());
		tree23.add(38);
		assertEquals("[ 17 38 ] [ 2 9 ] [ 20 ] [ 40 ] " ,tree23.toString());//isOverflow(), split()
		tree23.add(93);
		assertEquals("[ 17 38 ] [ 2 9 ] [ 20 ] [ 40 93 ] " ,tree23.toString());
		tree23.add(8);
		assertEquals("[ 17 ] [ 8 ] [ 38 ] [ 2 ] [ 9 ] [ 20 ] [ 40 93 ] " ,tree23.toString());//isOverflow(), split()		
		tree23.remove(40);
		assertEquals("[ 17 ] [ 8 ] [ 38 ] [ 2 ] [ 9 ] [ 20 ] [ 93 ] " ,tree23.toString());
		tree23.add(45);
		assertEquals("[ 17 ] [ 8 ] [ 38 ] [ 2 ] [ 9 ] [ 20 ] [ 45 93 ] " ,tree23.toString());		
		tree23.remove(20);
		assertEquals("[ 17 ] [ 8 ] [ 45 ] [ 2 ] [ 9 ] [ 38 ] [ 93 ] " ,tree23.toString());
		tree23.add(4);
		tree23.remove(9);
		assertEquals("[ 17 ] [ 4 ] [ 45 ] [ 2 ] [ 8 ] [ 38 ] [ 93 ] " ,tree23.toString());
		tree23.remove(8);
		assertEquals("[ 17 45 ] [ 2 4 ] [ 38 ] [ 93 ] " ,tree23.toString());
		tree23.remove(93);
		assertEquals("[ 17 ] [ 2 4 ] [ 38 45 ] " ,tree23.toString());
		tree23.remove(17);
		assertEquals("[ 4 ] [ 2 ] [ 38 45 ] " ,tree23.toString());
		tree23.remove(4);
		assertEquals("[ 38 ] [ 2 ] [ 45 ] " ,tree23.toString());
		tree23.add(4);
		tree23.add(6);
		assertEquals("[ 4 38 ] [ 2 ] [ 6 ] [ 45 ] " ,tree23.toString());
		tree23.add(1);
		assertEquals("[ 4 38 ] [ 1 2 ] [ 6 ] [ 45 ] " ,tree23.toString());
		tree23.remove(4);
		tree23.remove(38);
		tree23.remove(1);
		assertEquals("[ 6 ] [ 2 ] [ 45 ] " ,tree23.toString());
		tree23.remove(2);
		assertEquals("[ 6 45 ] " ,tree23.toString());
		tree23.remove(6);
		assertEquals("[ 45 ] " ,tree23.toString());
		tree23.remove(45);
		assertEquals("" ,tree23.toString());
		
		tree4.add(39);
		tree4.add(9);
		tree4.add(17);
		tree4.add(25);
		tree4.add(2);
		tree4.add(8);
		tree4.add(12);
		tree4.add(20);
		tree4.add(38);
		tree4.add(95);
		tree4.add(50);
		tree4.add(60);
		tree4.add(93);
		tree4.add(99);
		assertEquals("[ 50 ] [ 9 25 ] [ 95 ] [ 2 8 ] [ 12 17 20 ] [ 38 39 ] [ 60 93 ] [ 99 ] ", tree4.toString());
		tree4.remove(25);
		assertEquals("[ 50 ] [ 9 20 ] [ 95 ] [ 2 8 ] [ 12 17 ] [ 38 39 ] [ 60 93 ] [ 99 ] ", tree4.toString());
		tree4.remove(8);
		assertEquals("[ 50 ] [ 9 20 ] [ 95 ] [ 2 ] [ 12 17 ] [ 38 39 ] [ 60 93 ] [ 99 ] ", tree4.toString());
		tree4.remove(17);
		assertEquals("[ 50 ] [ 9 20 ] [ 95 ] [ 2 ] [ 12 ] [ 38 39 ] [ 60 93 ] [ 99 ] ", tree4.toString());
		tree4.remove(12);
		assertEquals("[ 50 ] [ 9 38 ] [ 95 ] [ 2 ] [ 20 ] [ 39 ] [ 60 93 ] [ 99 ] ", tree4.toString());
		tree4.remove(93);
		assertEquals("[ 50 ] [ 9 38 ] [ 95 ] [ 2 ] [ 20 ] [ 39 ] [ 60 ] [ 99 ] ", tree4.toString());
		tree4.remove(60);
		assertEquals("[ 38 ] [ 9 ] [ 50 ] [ 2 ] [ 20 ] [ 39 ] [ 95 99 ] ", tree4.toString());
		tree4.remove(2);
		assertEquals("[ 38 50 ] [ 9 20 ] [ 39 ] [ 95 99 ] ", tree4.toString());
		tree4.remove(39);
		assertEquals("[ 20 50 ] [ 9 ] [ 38 ] [ 95 99 ] ", tree4.toString());
		tree4.remove(38);
		assertEquals("[ 20 95 ] [ 9 ] [ 50 ] [ 99 ] ", tree4.toString());
		tree4.remove(20);
		tree4.remove(95);
		assertEquals("[ 50 ] [ 9 ] [ 99 ] ", tree4.toString());
		tree4.remove(99);
		assertEquals("[ 9 50 ] ", tree4.toString());
		tree4.remove(9);
		assertEquals("[ 50 ] ", tree4.toString());
		tree4.remove(50);
		assertEquals("", tree4.toString());
	}
	
	public void testContains(){
		tree4.add(39);
		tree4.add(9);
		tree4.add(17);
		tree4.add(25);
		tree4.add(2);
		tree4.add(8);
		tree4.add(12);
		tree4.add(20);
		tree4.add(38);
		tree4.add(95);
		tree4.add(50);
		tree4.add(60);
		tree4.add(93);
		tree4.add(99);
		
		assertTrue(tree4.contains(39));
		assertTrue(tree4.contains(9));
		assertTrue(tree4.contains(12));
		assertTrue(tree4.contains(93));
		
		assertFalse(tree4.contains(100));
		assertFalse(tree4.contains(1));
		assertFalse(tree4.contains(3));
		
		tree4.remove(39);
		tree4.remove(9);
		
		assertFalse(tree4.contains(39));
		assertFalse(tree4.contains(9));
	}

	private class IntComparatorInc implements  Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	}
}
