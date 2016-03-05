import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * 
 * @author nateriehl
 * CS 216, Fall 2015
 * HW 1
 * 
 *Testing for DList class
 */
public class DListTest {
	public DList<Integer> l1;
	public DList<Character> l2;
	public DList<Character> l3;
	public ExpectedException exception = ExpectedException.none();
	@Before
	public void setUp() throws Exception {
		CharComparatorInc charInc = new CharComparatorInc();
		CharComparatorDec charDec = new CharComparatorDec();
		
		l1 = new DList<Integer>();
		l2 = new DList<Character>(charInc);
		l3 = new DList<Character>(charDec);
	}
	
	@Test
	public void testToString(){
		assertEquals("[ ]", l1.toString());
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		assertEquals("[ 1 2 3 4 5 ]", l1.toString());
	}
	
	@Test
	public void testToStringBackward(){
		assertEquals("[ ]", l1.toStringBackward());
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		assertEquals("[ 5 4 3 2 1 ]", l1.toStringBackward());
	}
	
	@Test
	public void testAdd(){
		l1.add(0, 0);
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		assertEquals("[ 0 1 2 3 4 5 ]", l1.toString());
		assertEquals("[ 5 4 3 2 1 0 ]", l1.toStringBackward());
		
		//Tests for add() method using comparator
		l2.add('A');
		l2.add('C');
		l2.add('E');
		l2.add('B');
		l2.add('D');
		assertEquals("[ A B C D E ]", l2.toString());
		assertEquals("[ E D C B A ]", l2.toStringBackward());
		
		l3.add('A');
		l3.add('C');
		l3.add('E');
		l3.add('B');
		l3.add('D');
		assertEquals("[ E D C B A ]", l3.toString());
		assertEquals("[ A B C D E ]", l3.toStringBackward());
		
		//Tests for overloaded add(int index, E elem) method
		l1.add(0, 0);
		assertEquals("[ 0 0 1 2 3 4 5 ]", l1.toString());
		assertEquals("[ 5 4 3 2 1 0 0 ]", l1.toStringBackward());
		
		l1.add(7, 6);
		assertEquals("[ 0 0 1 2 3 4 5 6 ]", l1.toString());
		assertEquals("[ 6 5 4 3 2 1 0 0 ]", l1.toStringBackward());
		
		l1.add(3,3);
		assertEquals("[ 0 0 1 3 2 3 4 5 6 ]", l1.toString());
		assertEquals("[ 6 5 4 3 2 3 1 0 0 ]", l1.toStringBackward());
		
		l1.add(9,3);
		assertEquals("[ 0 0 1 3 2 3 4 5 6 3 ]", l1.toString());
		assertEquals("[ 3 6 5 4 3 2 3 1 0 0 ]", l1.toStringBackward());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testException(){ //Tests invalid index exception for add() method
		l1.add(2,0);
		l1.add(-1, 0);
	}
	@Test
	public void testClear(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		assertEquals("[ 1 2 3 ]", l1.toString());
		l1.clear();
		assertEquals("[ ]", l1.toString());
	}
	
	@Test
	public void testGet(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		Integer a = 5;
		Integer b = 1;
		Integer c = 3;
		
		assertEquals(a,  l1.get(4));
		assertEquals(b,  l1.get(0));
		assertEquals(c,  l1.get(2));
		
		assertNull(l1.get(5));
		assertNull(l1.get(-1));
	}
	
	@Test
	public void testSet(){
		l1.set(0, 1);
		assertEquals("[ ]", l1.toString());
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		Integer a = 1;
		assertEquals(a, l1.set(0, 0));
		assertEquals("[ 0 2 3 4 5 ]", l1.toString());
		
		Integer b = 5;
		assertEquals(b, l1.set(4, 0));
		assertEquals("[ 0 2 3 4 0 ]", l1.toString());
		
		assertNull(l1.set(10, 2)); //Index > length - 1;
		assertNull(l1.set(-1, 2)); //Index < 0
	}
	
	@Test
	public void testContains(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		assertTrue(l1.contains(1));
		assertTrue(l1.contains(5));
		assertTrue(l1.contains(3));
		
		assertFalse(l1.contains(6));
	}
	@Test
	public void testIndexOf(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		assertEquals(0, l1.indexOf(1));
		assertEquals(4, l1.indexOf(5));
		assertEquals(2, l1.indexOf(3));
		//Element not present
		assertEquals(-1, l1.indexOf(6));
	}
	@Test
	public void testLastIndexOf(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(5);
		l1.add(4);
		l1.add(1);
		
		assertEquals(7, l1.lastIndexOf(1));
		assertEquals(6, l1.lastIndexOf(4));
		assertEquals(5, l1.lastIndexOf(5));
		assertEquals(1, l1.lastIndexOf(2));
		assertEquals(-1,l1.lastIndexOf(10));
	}
	
	@Test
	public void testSize(){
		assertEquals(0, l1.size());
		l1.add(1);
		l1.add(2);
		l1.add(3);
		assertEquals(3, l1.size());
		l1.add(4);
		l1.add(5);
		assertEquals(5, l1.size());
	}
	
	@Test
	public void testEmpty(){
		assertTrue(l1.empty());
		l1.add(5);
		assertFalse(l1.empty());
	}
	@Test
	public void testToArray(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(l1.toArray()));
	}
	
	@Test
	public void testRemove(){
		l1.add(1);
		assertEquals(1, (int) l1.remove(0)); //Only element
		assertEquals("[ ]", l1.toString());
		assertEquals("[ ]", l1.toStringBackward());
		
		assertNull(l1.remove(3)); //Index too big
		assertNull(l1.remove(-1)); //Index too small

		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(6);

		assertEquals(1, (int) l1.remove(0)); //First element
		assertEquals("[ 2 3 4 5 6 ]", l1.toString());
		assertEquals("[ 6 5 4 3 2 ]", l1.toStringBackward());
		
		assertEquals(6, (int) l1.remove(4)); //Last element
		assertEquals("[ 2 3 4 5 ]", l1.toString());
		assertEquals("[ 5 4 3 2 ]", l1.toStringBackward());
		
		assertEquals(3, (int) l1.remove(1)); //Arbitrary middle element
		assertEquals("[ 2 4 5 ]", l1.toString());
		assertEquals("[ 5 4 2 ]", l1.toStringBackward());	
		
		l1.clear();
		
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		l1.add(10);
		//Tests for remove(E elem)
		assertFalse(l1.remove(new Integer(-1))); //Element not present
		assertFalse(l1.remove(new Integer(6))); //Element not present
		
		assertTrue(l1.remove(new Integer(1))); //First element
		assertEquals("[ 2 3 4 5 10 ]", l1.toString());
		assertEquals("[ 10 5 4 3 2 ]", l1.toStringBackward());
		
		assertTrue(l1.remove(new Integer(10))); //Last element
		assertEquals("[ 2 3 4 5 ]", l1.toString());
		assertEquals("[ 5 4 3 2 ]", l1.toStringBackward());
		
		assertTrue(l1.remove(new Integer(3))); //Arbitrary middle element
		assertEquals("[ 2 4 5 ]", l1.toString());
		assertEquals("[ 5 4 2 ]", l1.toStringBackward());
		
		try{ //Check if null target is used as parameter
			l1.remove(null);
			fail("IllegalArgumentException expected but not thrown");
		}
		catch(IllegalArgumentException e){			
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
	}
	
	@Test
	public void testRemoveRange(){
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);

		l1.removeRange(0, 4); //Entire list
		assertEquals("[ ]", l1.toString());
		assertEquals("[ ]", l1.toStringBackward());
		assertEquals(0, (int) l1.size());
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(5);
		
		l1.removeRange(0, 2); //beginning to some middle point
		assertEquals(2, (int) l1.size());
		assertEquals("[ 4 5 ]", l1.toString());
		assertEquals("[ 5 4 ]", l1.toStringBackward());
		
		
		l1.add(0, 1);	
		l1.add(1, 2);
		l1.add(2, 3);
		assertEquals("[ 1 2 3 4 5 ]", l1.toString());
		
		l1.removeRange(2, 4); //Some middle point to end
		assertEquals("[ 1 2 ]", l1.toString());
		assertEquals("[ 2 1 ]", l1.toStringBackward());
		
		l1.add(2, 3);	
		l1.add(3, 4);
		l1.add(4, 5);
		assertEquals("[ 1 2 3 4 5 ]", l1.toString());
		l1.removeRange(2, 3); //Some middle point to another middle point
		assertEquals("[ 1 2 5 ]", l1.toString());
		assertEquals("[ 5 2 1 ]", l1.toStringBackward());
		
		l1.removeRange(1, 1); //Same to/fromIndex
		assertEquals("[ 1 5 ]", l1.toString());
		assertEquals("[ 5 1 ]", l1.toStringBackward());
		
		l1.removeRange(-1, 2); //Case where fromIndex is too small and toIndex is too big
		assertEquals("[ ]", l1.toString());
		assertEquals("[ ]", l1.toStringBackward());
		
		try{ // Should throw IllegalArgumentException
			l1.removeRange(2, 0); //Ranges are switched
			fail("IllegalArgumentException expected but not thrown");
		}
		catch(IllegalArgumentException e){	
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
		
		try{ // Should throw IllegalArgumentException
			l1.removeRange(-1, -1); //Both indexes too small
			fail("IllegalArgumentException expected but not thrown");
		}
		catch(IllegalArgumentException e){	
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
		
		try{ // Should throw IllegalArgumentException
			l1.removeRange(10, 10); //Both indexes too big
			fail("IllegalArgumentException expected but not thrown");
		}
		catch(IllegalArgumentException e){	
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
	}
	@Test
	public void testAddAll(){
		ArrayList<Integer> addList = new ArrayList<Integer>();
		addList.add(1);
		addList.add(2);
		addList.add(3);
		addList.add(4);
		addList.add(5);
		assertEquals("[1, 2, 3, 4, 5]", addList.toString());
		l1.add(6);
		l1.add(7);
		l1.add(8);
		l1.add(9);
		l1.add(10);
		l1.addAll(5, addList);
		assertEquals("[ 6 7 8 9 10 1 2 3 4 5 ]", l1.toString()); //Add to end
		assertEquals("[ 5 4 3 2 1 10 9 8 7 6 ]", l1.toStringBackward());
		assertEquals(10, l1.size());
		l1.clear(); //Clear list
		
		l1.add(6);
		l1.add(7);
		l1.add(8);
		l1.add(9);
		l1.add(10);
		l1.addAll(0, addList);
		assertEquals("[ 1 2 3 4 5 6 7 8 9 10 ]", l1.toString()); //Add to beginning
		assertEquals("[ 10 9 8 7 6 5 4 3 2 1 ]", l1.toStringBackward());
		assertEquals(10, l1.size());
		l1.clear(); //Clear list
		
		l1.add(6);
		l1.add(7);
		l1.add(8);
		l1.add(9);
		l1.add(10);
		l1.addAll(3, addList);
		assertEquals("[ 6 7 8 1 2 3 4 5 9 10 ]", l1.toString()); //Add to middle
		assertEquals("[ 10 9 5 4 3 2 1 8 7 6 ]", l1.toStringBackward());
		assertEquals(10, l1.size());
		
		addList.clear();
		l1.clear();
		addList.add(1);
		l1.addAll(0, addList);
		assertEquals("[ 1 ]", l1.toString()); //Only element
		assertEquals("[ 1 ]", l1.toStringBackward());
		assertEquals(1, l1.size());
		
		try{ // Should throw NullPointerException
			l1.addAll(0, null);
			fail("NullPointerException expected but not thrown");
		}
		catch(NullPointerException e){	
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
		
		try{ // Should throw IndexOutOfBoundsException
			l1.addAll(-1, addList);
			fail("IndexOutOfBoundsException expected but not thrown");
		}
		catch(IndexOutOfBoundsException e){	
		}
		catch(Exception e){
			fail("Unexpected exception thrown");
		}
	}
	private class CharComparatorInc implements  Comparator<Character> {
	     /**
	* compares two arguments for order
	* @param c1   first Character object to be compared
	* @param c2   second Character object to be compared
	     * @return a negative integer if c1 < c2, 0 if c1 == c2, a positive integer if c1 > c2
	*/
	     public int compare(Character c1, Character c2) {
	           // return c1.charValue() – c2.charValue();    // also works
	           return c1.compareTo(c2);
	     }
	}
	 
	/**
	  * Character Comparator to create a list sorted in decreasing order
	 */
	private class CharComparatorDec implements Comparator<Character> {
	/**
	* compares two arguments for order
	* @param c1   first Character object to be compared
	* @param c2   second Character object to be compared
	* @return a negative integer if c1 > c2, 0 if c1 == c2, a positive integer if c1 < c2
	*/
	     public int compare(Character c1, Character c2) {
	           return c2.compareTo(c1);
	     }
	}
}
