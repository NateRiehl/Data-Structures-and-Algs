import java.util.ArrayList;
/**
 * Queue using ArrayList
 * @author nateriehl
 *
 * @param <E>
 */
public class QueueAL<E> {
	private ArrayList<E> queue;
	private int count;
	public QueueAL(){
		queue = new ArrayList<E>();
	}
	/**
	 * Add to back of queue
	 * @param data that is being added
	 */
	public void enqueue(E data){
		queue.add(data);
		count++;
	}
	
	/**
	 * Remove element at front
	 * @return element that is removed
	 */
	public E dequeue(){
		if(queue.isEmpty()){
			return null;
		}
		count--;
		return queue.remove(0);
	}
	/**
	 * Essentially a peek method
	 * @return data at front of Queue
	 */
	public E getFront(){
		if(queue.isEmpty()){
			return null;
		}
		return queue.get(0);
	}
	
	public boolean isEmpty(){
		return (count == 0);
	}
}
