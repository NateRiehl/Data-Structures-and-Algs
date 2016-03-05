import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class BSTreeTest {
	public BSTree<Integer> tree;
	public IntComparatorInc cmp;
	@Before
	public void setUp() throws Exception {
		cmp = new IntComparatorInc();
		tree = new BSTree<Integer>(cmp);
	}
	@Test
	public void testAdd(){
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(8);
		tree.add(10);
		tree.add(12);
		tree.add(12);
		tree.add(40);
		tree.add(6);
		tree.add(9);
		tree.add(20);
		assertEquals(11, tree.size());
		assertEquals(11, tree.sizeTree());
		assertEquals("[ 10 9 11 8 10 12 6 9 12 40 20 ]", tree.toString());
		tree.printPaths();
		tree.clear();
		tree.add(40);
		tree.add(2);
		tree.add(17);

		assertEquals("[ 40 2 17 ]", tree.toString());
	}
	@Test
	public void testContains(){
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(8);
		tree.add(10);
		tree.add(12);
		tree.add(12);
		tree.add(40);

		assertTrue(tree.contains(12));
		assertFalse(tree.contains(50));

		tree.remove(40);
		assertFalse(tree.contains(40));	

		tree.add(40);

		assertTrue(tree.contains(40));	
		assertTrue(tree.contains(10));
	}

	@Test
	public void testRemove(){
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(8);
		tree.add(10);
		tree.add(12);
		tree.add(12);
		tree.add(40);
		tree.add(6);
		tree.add(9);
		tree.add(20);
		assertEquals(11, tree.size());
		assertEquals("[ 10 9 11 8 10 12 6 9 12 40 20 ]", tree.toString());

		assertEquals(9, (int) tree.remove(9));
		assertEquals(10, tree.size());
		assertEquals("[ 10 9 11 8 10 12 6 12 40 20 ]", tree.toString());

		assertEquals(11, (int) tree.remove(11));
		assertEquals(9, tree.size());
		assertEquals("[ 10 9 12 8 10 12 40 6 20 ]", tree.toString());

		tree.remove(10);
		assertEquals(8, tree.size());
		assertEquals("[ 10 9 12 8 12 40 6 20 ]", tree.toString());

		tree.remove(9);
		assertEquals(7, tree.size());
		assertEquals("[ 10 8 12 6 12 40 20 ]", tree.toString());

		tree.remove(20);
		assertEquals(6, tree.size());
		assertEquals("[ 10 8 12 6 12 40 ]", tree.toString());

		assertEquals(6, (int) tree.remove(6));
		assertEquals(5, tree.size());
		assertEquals("[ 10 8 12 12 40 ]", tree.toString());

		assertEquals(12, (int) tree.remove(12));
		assertEquals("[ 10 8 12 40 ]", tree.toString());

		assertEquals(40, (int) tree.remove(40));
		assertEquals("[ 10 8 12 ]", tree.toString());

		assertEquals(8, (int) tree.remove(8));
		assertEquals("[ 10 12 ]", tree.toString());

		assertEquals(10, (int) tree.remove(10));
		assertEquals(1, tree.size());
		assertEquals("[ 12 ]", tree.toString());

		tree.add(6); 

		assertEquals(12, (int) tree.remove(12));
		assertEquals(1, tree.size());
		assertEquals("[ 6 ]", tree.toString());

		assertEquals(6, (int) tree.remove(6));
		assertEquals(0, tree.size());
		assertNull(tree.getRoot());
		assertEquals("[ ]", tree.toString());

		tree.clear(); //Clear!
		tree.add(20); 
		tree.add(40); 
		tree.add(60); 
		assertEquals("[ 20 40 60 ]", tree.toString());
		assertEquals(20, (int)tree.getRoot().getData());
		tree.remove(20);
		assertEquals(40, (int)tree.getRoot().getData());
		tree.remove(40);
		assertEquals("[ 60 ]", tree.toString());
		assertEquals(60, (int)tree.getRoot().getData());
		tree.remove(60);
		assertEquals("[ ]", tree.toString());
		assertNull(tree.getRoot());
		
		tree.add(4); 
		tree.add(3); 
		tree.add(2); 
		tree.add(3); 
		tree.add(3); 
		assertEquals("[ 4 3 2 3 3 ]", tree.toString());
		assertEquals(4, (int)tree.getRoot().getData());
		
		tree.remove(4);
		assertEquals("[ 3 2 3 3 ]", tree.toString());
		assertEquals(3, (int)tree.getRoot().getData());
		
		tree.clear();
		tree.add(40);
		tree.add(2);
		tree.add(17);
		tree.add(48);
		tree.remove(2);
		assertEquals("[ 40 17 48 ]", tree.toString());
		
		tree.clear();
		tree.add(30);
		tree.add(10);
		tree.add(15);
		tree.add(5);
		tree.add(13);
		tree.remove(30);
		assertEquals("[ 15 10 5 13 ]", tree.toString());
		
		tree.clear();
		tree.add(15);
		tree.add(40);
		tree.add(35);
		tree.add(30);
		tree.remove(40);
		assertEquals("[ 15 35 30 ]", tree.toString());
	}
	@Test
	public void testSize(){
		assertEquals(0, tree.size());
		tree.add(10);
		assertEquals(1, tree.size());
		tree.add(9);
		assertEquals(2, tree.size());
		tree.remove(9);
		assertEquals(1, tree.size());
		tree.remove(10);
		assertEquals(0, tree.size());
	}
	@Test
	public void testEmpty(){
		assertTrue(tree.empty());
		tree.add(10);
		assertFalse(tree.empty());
		tree.remove(10);
		assertTrue(tree.empty());
	}
	@Test
	public void testClear(){
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(8);
		tree.add(10);

		tree.clear();
		assertEquals("[ ]", tree.toString());
		assertEquals(0, tree.size());
		assertNull(tree.getRoot());
		assertTrue(tree.empty());
	}

	private class IntComparatorInc implements  Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	}
}
