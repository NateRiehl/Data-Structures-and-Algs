import java.util.ArrayList;

public class StackAL<E> {
	private ArrayList<E> stack;

	public StackAL(){
		stack = new ArrayList<E>();
	}
	//O(1)
	public E peek(){
		if(!stack.isEmpty()){
			return stack.get(stack.size() - 1);
		}
		return null;
	}
	//O(1)
	public void push(E data){
		if(stack.size() > 0){
		stack.add((stack.size() -1) , data);
		}
		else{
			stack.add(0, data);
		}
	}
	//O(1) Generally, Dynamic resizing can affect
	public E pop(){
		if(!stack.isEmpty()){
			return stack.remove(stack.size() - 1);
		}
		return null;
	}
	
	public int size(){
		return stack.size();
	}
	
	public boolean empty(){
		return stack.size() == 0;
	}
}

