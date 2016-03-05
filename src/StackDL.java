/**
 * Implementation of a stack using a Doubly-Linked List
 * @author nateriehl
 *
 */
public class StackDL<E> {
	private DList<E> stack;

	public StackDL(){
		stack = new DList<E>();
	}

	public void push(E elem){
		stack.add(0, elem);
	}

	public E pop(){
		if(!stack.empty()){
			return	stack.remove(0);
		}
		return null;
	}

	public E peek(){
		return stack.get(0);
	}
}
