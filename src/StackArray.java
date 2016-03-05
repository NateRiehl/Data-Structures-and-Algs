/**
 * Implementation of Stack using an Array
 * @author nateriehl
 */
public class StackArray<E> {
	private E[] stack;
	private int topOfStack;

	public StackArray(){
		stack = (E[]) new Object[4];
		topOfStack = -1;
	}

	public void push(E data){
		if(topOfStack == stack.length - 1){
			//resize
			E[] tmp = (E[]) new Object[size() * 2]; 
			//copy
			System.arraycopy(stack, 0, tmp, 0, stack.length);
			stack = tmp;
		}
		stack[++(topOfStack)] = data;
	}

	/**
	 * Removes and returns the item at the top
	 * @return Item at top
	 */
	public E pop(){
		if(!empty()){
			return stack[topOfStack--];
		}
		return null;
	}
	
	/**
	 * Returns the size of stack
	 * @return the size of stack
	 */
	public int size(){
		return topOfStack + 1;
	}
	
	/**
	 * Checks if empty
	 * @return true if empty, false otherwise
	 */
	public boolean empty(){
		return topOfStack == -1;
	}
}
