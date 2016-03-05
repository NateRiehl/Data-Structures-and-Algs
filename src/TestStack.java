import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStack {
	private StackAL<Character> stack;
	private StackAL<Character> stack2;
	@Before
	public void setUp() throws Exception {
		stack = new StackAL<Character>();
		stack2 = new StackAL<Character>();
		stack2.push('H');
		stack2.push('E');
		stack2.push('Y');
	}

	@Test
	public void testPush(){
		assertEquals(0, stack.size());
		assertTrue(stack.empty());
		assertNull(stack.pop());
		assertNull(stack.peek());
		
		stack.push('B');
		assertEquals('B', (char) stack.peek());
		assertEquals(1, stack.size());
		assertFalse(stack.empty());
		
		stack.push('Y');
		assertEquals('Y', (char) stack.peek());
	}
	
	public void testPop(){
		assertFalse(stack2.empty());
		assertEquals('Y', (char) stack2.peek());
	}

}
