
public class QueueArray<E> {
	private E[] queue;
	private int first; //Front
	private int last; //Add to last
	private int count;

	public QueueArray(){
		queue = (E[]) new Object[4];
		first = -1;
		last = 0;
	}

	public void enqueue(E data){
		//Resize if necessary
		if(count == queue.length){
			//create a large array
			E[] temp = (E[]) new Object[queue.length * 2];
			//copy data over
			System.arraycopy(queue, first, temp, 0, queue.length - first);
			System.arraycopy(queue, 0, temp, queue.length - 1, last);
			queue = temp;
			//update first and last
			first = 0;
			last  = count;
		}
		//Add to queue
		queue[last] = data;

		//Make sure last is always inbounds
		last = (last + 1) % queue.length;
		count++;
	}

	public E dequeue(){
		if(count == 0){
			return null;
		}
		E front = queue[first];
		first = (first  + 1) % queue.length;
		count--;
		return front;
	}

	public E getFirst(){
		if(count == 0){
			return null;
		}
		return queue[first];
	}
}
