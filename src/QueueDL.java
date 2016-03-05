import java.util.ArrayList;

/**
 * Queue using DLinkedList
 * @author nateriehl
 *
 */
public class QueueDL<E> {
	private DList<E> queue = new DList<E>();
	
	public QueueDL(){
		queue = new DList<E>();
	}
	/**
	 * Add to back of queue
	 * @param data that is being added
	 */
	public void enqueue(E data){
		queue.add(data);
	}
	
	/**
	 * Remove element at front
	 * @return element that is removed
	 */
	public E dequeue(){
		if(queue.empty()){
			return null;
		}
		return queue.remove(0);
	}
	/**
	 * Essentially a peek method
	 * @return data at front of Queue
	 */
	public E getFront(){
		if(queue.empty()){
			return null;
		}
		return queue.get(0);
	}
	
}
