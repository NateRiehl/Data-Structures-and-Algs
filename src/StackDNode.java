
public class StackDNode<E> {
	private DNode<E> head;

	public StackDNode(){
	}

	public void push(E data){
		DNode<E> newNode = new DNode<E>(data);
		if(head !=null){
			head.previous = newNode;
		}
		newNode.next = head;
		head = newNode;
	}

	public E pop(){
//		if(head!= null){
//			
//		}
		return null;
	}

	public E peek(){
		return head.data;
	}
}
