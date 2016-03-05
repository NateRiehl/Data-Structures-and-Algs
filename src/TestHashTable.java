import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * JUnit test cases for Hasht1able implementation
 * @author Nate Riehl
 * CS216, Fall 2015
 * HW 4
 */
public class TestHashTable {
	private HashTable<String, String> ht1;
	private HashTable<String, String> ht2;
	private HashTable<String, String> ht3;
	private HashTable<String, String> ht4;
	@Before
	public void setUp() throws Exception {
		ht1 = new HashTable<String, String>();
		ht2 = new HashTable<String, String>(.75);
		ht3 = new HashTable<String, String>(5);
		ht4 = new HashTable<String, String>(13, .75);
	}

	@Test
	public void testPut() {
		assertNull(ht1.put("A", "A")); //length % 7 = (1)
		assertNull(ht1.put("B", "B")); //length % 7 = 1 (Collision) --> +1 --> (2)
		assertNull(ht1.put("C", "C")); //length % 7 = 1 (Collision) --> +1 ---> + 4 = (5)
		assertNull(ht1.put("D", "D")); //length % 7 = 1 (Collision) --> +1 ---> + 4 --> Resize() triggered --> (10)
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString()); //length 17
		assertEquals(4, ht1.size());

		assertEquals("A", ht1.put("A", "Z")); //Change value associated with key "A"
		assertEquals("[ E A:Z B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(4, ht1.size());

		assertEquals("Z", ht1.get("A")); 
		ht1.put("F", "F"); //length % 17  = 1 (Collision) -->+1 -->+4 -->+9 -->+16 --> (0)
		assertEquals("[ F:F A:Z B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(5, ht1.size());

		assertEquals("F", ht1.put("F", "Y"));
		assertEquals("[ F:Y A:Z B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		assertNull(ht4.put("A", "A")); //length % 13 = (1)
		assertNull(ht4.put("B", "B")); //length % 13 = 1 (Collision) --> +1 --> (2)
		assertNull(ht4.put("C", "C")); //length % 13 = 1 (Collision) --> +1 ---> + 4 = (5)
		assertNull(ht4.put("D", "D")); //length % 13 = 1 (Collision) --> +1 ---> + 4 --> +9 --> (10)
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E ]",ht4.toString()); //length 13
		assertEquals(4, ht4.size());

		assertEquals("A", ht4.put("A", "Z")); //Change value associated with key "A"
		assertEquals("[ E A:Z B:B E E C:C E E E E D:D E E ]",ht4.toString()); //length 13
		assertEquals(4, ht4.size());

		/*
		 * Tests for HashTable(int cap)
		 */
		assertNull(ht3.put("Nate", "A")); //length % 5 = (4)
		assertNull(ht3.put("Sam", "B"));   //length % 5 = (3)
		assertNull(ht3.put("Dean", "C"));//length % 5 = 4 (Collision) --> +1 --> (0)
		assertEquals("[ Dean:C E E Sam:B Nate:A ]",ht3.toString()); //length 5
	}
	@Test
	public void testContainsKey(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D");
		assertTrue(ht1.containsKey("A"));
		assertTrue(ht1.containsKey("D"));
		assertFalse(ht1.containsKey("Y"));
		assertFalse(ht1.containsKey("Z"));
		assertFalse(ht1.containsKey(null)); //Null key

		ht1.remove("A");
		assertFalse(ht1.containsKey("A"));
		assertTrue(ht1.containsKey("B"));

		ht1.put("A", "A");
		assertTrue(ht1.containsKey("A"));

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D");
		assertTrue(ht4.containsKey("A"));
		assertTrue(ht4.containsKey("D"));
		assertFalse(ht4.containsKey("Y"));
		assertFalse(ht4.containsKey("Z"));
		assertFalse(ht4.containsKey(null)); //Null key

		ht4.remove("A");
		assertFalse(ht4.containsKey("A"));
		assertTrue(ht4.containsKey("B"));

		ht4.put("A", "A");
		assertTrue(ht4.containsKey("A"));
	}
	@Test
	public void testContainsValue(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D");
		assertTrue(ht1.containsValue("A"));
		assertTrue(ht1.containsValue("D"));
		assertFalse(ht1.containsValue("Y")); //Not present
		assertFalse(ht1.containsValue("Z")); //Not present

		ht1.put("A", "Z"); //Change value at key

		assertTrue(ht1.containsValue("Z")); //Make sure value "Z" now returns true
		assertFalse(ht1.containsValue(null)); //Null value

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D");
		assertTrue(ht4.containsValue("A"));
		assertTrue(ht4.containsValue("D"));
		assertFalse(ht4.containsValue("Y")); //Not present
		assertFalse(ht4.containsValue("Z")); //Not present

		ht4.put("A", "Z"); //Change value at key

		assertTrue(ht4.containsValue("Z")); //Make sure value "Z" now returns true
		assertFalse(ht4.containsValue(null)); //Null value
	}
	@Test
	public void testGet(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D");
		assertEquals("A",ht1.get("A"));
		ht1.put("A", "Z"); //Change value at key
		assertEquals("Z",ht1.get("A"));
		assertNull(ht1.get("L")); //Not present
		assertNull(ht1.get(null)); //Null key

		ht1.remove("A");
		assertNull(ht1.get("A"));

		ht1.put("A", "A");
		assertEquals("A", ht1.get("A"));

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D");
		assertEquals("A",ht4.get("A"));
		ht4.put("A", "Z"); //Change value at key
		assertEquals("Z",ht4.get("A"));
		assertNull(ht4.get("L")); //Not present
		assertNull(ht4.get(null)); //Null key

		ht4.remove("A");
		assertNull(ht4.get("A"));

		ht4.put("A", "A");
		assertEquals("A", ht4.get("A"));
	}
	@Test
	public void testRemove(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D");
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(4, ht1.size());
		assertEquals("A", ht1.remove("A"));
		assertEquals("[ E E B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(3, ht1.size());

		assertEquals("B", ht1.remove("B"));
		assertEquals("C", ht1.remove("C"));
		assertEquals("D", ht1.remove("D")); 

		assertEquals("[ ]",ht1.toString());
		assertEquals(0, ht1.size());
		assertTrue(ht1.empty());

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D");
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E ]",ht4.toString());
		assertEquals(4, ht4.size());
		assertEquals("A", ht4.remove("A"));
		assertEquals("[ E E B:B E E C:C E E E E D:D E E ]",ht4.toString());
		assertEquals(3, ht4.size());

		assertEquals("B", ht4.remove("B"));
		assertEquals("C", ht4.remove("C"));
		assertEquals("D", ht4.remove("D")); 

		assertEquals("[ ]",ht4.toString());
		assertEquals(0, ht4.size());
		assertTrue(ht4.empty());
	}
	@Test
	public void testSize(){
		ht1.put("A", "A");
		assertEquals(1, ht1.size());
		ht1.put("B", "B");
		assertEquals(2, ht1.size());
		ht1.put("C", "C");
		assertEquals(3, ht1.size());
		ht1.put("D", "D");
		assertEquals(4, ht1.size());

		ht1.clear();
		assertEquals(0, ht1.size());

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		assertEquals(1, ht4.size());
		ht4.put("B", "B");
		assertEquals(2, ht4.size());
		ht4.put("C", "C");
		assertEquals(3, ht4.size());
		ht4.put("D", "D");
		assertEquals(4, ht4.size());

		ht4.clear();
		assertEquals(0, ht4.size());
	}

	@Test
	public void testEmpty(){
		assertTrue(ht1.empty());
		ht1.put("A", "A");
		assertFalse(ht1.empty());
		ht1.remove("A");
		assertTrue(ht1.empty());

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		assertTrue(ht4.empty());
		ht4.put("A", "A");
		assertFalse(ht4.empty());
		ht4.remove("A");
		assertTrue(ht4.empty());
	}

	@Test
	public void testClear(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D");
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(4, ht1.size());

		ht1.clear();
		assertEquals("[ ]",ht1.toString());
		assertEquals(0, ht1.size());

		/*
		 * Tests for HashTable(int cap, double lf)
		 */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D");
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E ]",ht4.toString());
		assertEquals(4, ht4.size());

		ht4.clear();
		assertEquals("[ ]",ht4.toString());
		assertEquals(0, ht4.size());
	}
	@Test
	public void testResize(){
		ht1.put("A", "A");
		ht1.put("B", "B");
		ht1.put("C", "C");
		ht1.put("D", "D"); //Resize() triggered --> internal table = 17 
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E E E E E ]",ht1.toString());
		assertEquals(4, ht1.size());

		/* * Tests for HashTable(int cap, double lf) */
		ht4.put("A", "A");
		ht4.put("B", "B");
		ht4.put("C", "C");
		ht4.put("D", "D"); //Resize() triggered --> internal table = 13
		assertEquals("[ E A:A B:B E E C:C E E E E D:D E E ]",ht4.toString());
		assertEquals(4, ht4.size());
	}

}
