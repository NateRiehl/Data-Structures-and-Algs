/**
 * Queue implementation using a DNode<E>
 * @author nateriehl
 *
 */
public class QueueDNode<E> {
	private DNode<E> front;
	private DNode<E> back;
	public int count;

	public QueueDNode(){
	}

	/**
	 * Add to back of queue
	 * @param data that is being added
	 */
	public void enqueue(E data){
		DNode<E> newNode = new DNode<E>(data);
		if(count != 0){
			back.next = newNode;
			newNode.previous = back;
			back = newNode;
		}
		else{
			front = newNode;
			back = newNode;
		}
		count++;
	}

	/**
	 * Remove element at front
	 * @return element that is removed
	 */
	public E dequeue(){
		if(count == 0){
			return null;
		}
		E temp = front.data;
		front = front.next;
		if(front != null){
			front.previous = null;
		}
		else{
			back = null;
		}
		count--;
		return temp;
	}
	/**
	 * Essentially a peek method
	 * @return data at front of Queue
	 */
	public E getFront(){
		if(front != null){
			return front.data;
		}
		return null;
	}
}