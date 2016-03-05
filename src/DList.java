import java.util.Comparator;
import java.util.Iterator;
/**
 * 
 * @author nateriehl
 * CS 216, Fall 2015
 * HW 1
 * 
 *Implementation of a doubly-linked list
 */
public class DList<E> implements Iterable<E>{
	public DNode<E> head;
	public DNode<E> tail;
	private int length = 0;
	private Comparator cmp;
	/**
	 * Standard constructor which initializes member variables and creates a dummy Node
	 */
	public DList(){
		DNode<E> dummyNode = new DNode<E>(null);
		head = dummyNode;
		tail = head;
	}
	/**
	 * Overloaded constructor that takes a Comparator cmp as a parameter 
	 * @param cmp Comparator used to sort DList when adding elements
	 */
	public DList(Comparator cmp){
		DNode<E> dummyNode = new DNode<E>(null);
		head = dummyNode;
		tail = head; 
		this.cmp = cmp;
	}

	public Iterator<E> iterator() {
		return (new DListIterator());
	}
	/**
	 * Overwritten toString() method
	 * @return str A string compromised of the data from each element in DList, index 0 - (listSize-1)
	 */
	public String toString(){
		DNode<E> current = head.next;
		String str = "[ ";	
		while(current != null){
			str += current.data + " ";
			current = current.next;
		}		
		str += "]";
		return str;
	}
	/**
	 * Variation of standard toString() method
	 * @return str A string compromised of the data from each element in DList,  (listSize-1) - Index 0
	 */
	public String toStringBackward(){
		DNode<E> current = tail;
		String str = "[ ";
		while(current.previous != null){
			str += current.data + " ";
			current = current.previous;
		}
		str += "]"; 
		return str;
	}
	/**
	 * If a comparator is being used, element is added at correct position according to comparator
	 * Otherwise, element is added to the back of the Doubly-Linked List
	 * @param elem Element to be inserted
	 */
	public void add(E elem){
		DNode<E> newNode = new DNode<E>(elem);
		//If not sorted, add to the end
		if(cmp == null){
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}
		else{
			//If using Comparator, add element at correct place
			DNode<E> current = head;
			DNode<E> nextNode = current.next;
			while(nextNode != null && cmp.compare(elem, nextNode.data) > 0){
				current = nextNode;
				nextNode = nextNode.next;		
			}		
			current.next = newNode;
			newNode.previous = current;
			newNode.next = nextNode;
			if(nextNode != null){
				nextNode.previous = newNode;
			}
			else{
				tail = newNode;
			}
		}
		length++;
	}
	/**
	 * Adds element at specified index. If index == listSize, element is added to the end.
	 * Only adds element if a comparator is not present
	 * @param index desired insertion index
	 * @param elem data to be added to Doubly-Linked List
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	public void add(int index, E elem) throws IndexOutOfBoundsException {
		DNode<E> newNode = new DNode<E>(elem);
		if(index > length || index < 0){ //Throw out of bounds exception if index is out of range
			throw new IndexOutOfBoundsException();
		}
		if(cmp == null){
			DNode<E> current = head;
			DNode<E> nextNode = current.next;
			int count = 0;
			if(index == length){
				add(elem);
			}
			else{
				while(count < index){
					current = nextNode;
					nextNode = nextNode.next;
					count++;
				}
				current.next = newNode;
				newNode.previous = current;
				newNode.next = nextNode;
				nextNode.previous = newNode;
				length++;
			}
		}
	}
	/**
	 * Clears DList
	 */
	public void clear(){
		head.next = null;
		tail = head;
		length = 0;
	}
	/**
	 * Returns data at a specified index
	 * @param index specified location of data to return
	 * @return data from specified position, null if index is not valid
	 */
	public E get(int index){
		if(index  < length && index >= 0){
			DNode<E> current = head.next;
			int count = 0;
			while(count < index){
				current = current.next;
				count++;
			}
			return current.data;
		}
		return null;
	}
	/**
	 * Sets the data at the specified index to parameter element
	 * @param elem data to set
	 * @param index position to set elem
	 * @return data that is replaced if index is valid, null if index is not valid
	 */
	public E set(int index, E elem){
		if(index < length && index >= 0){
			DNode<E> current = head.next;
			int count = 0;
			while(count < index){
				current = current.next;
				count++;
			}
			E temp = current.data;
			current.data = elem;
			return temp;
		}
		return null;
	}
	/**
	 * Checks if the DList contains a specific element
	 * @param elem element to look for
	 * @return true if element is contained in DList, false otherwise
	 */
	public boolean contains(E elem){
		DNode<E> current = head.next;
		while(current != null){
			if(current.data.equals(elem)){
				return true;
			}
			current = current.next;
		}
		return false;
	}
	/**
	 * Gives the index of a specified element
	 * @param elem element to find index
	 * @return Given index if element is found. -1 otherwise
	 */
	public int indexOf(E elem){
		DNode<E> current = head.next;
		int index = 0;
		while(current != null){
			if(current.data.equals(elem)){
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	/**
	 * Returns the index of the last occurance of a specified element
	 * @param elem element to find last index of
	 * @return last index if element is present. Otherwise returns -1 
	 */
	public int lastIndexOf(E elem){
		DNode<E> current = head.next;
		int index = 0;
		int elemIndex = 0;
		int count = 0;
		while(current != null){
			if(current.data.equals(elem)){
				elemIndex = index;
				count++;
			}
			current = current.next;
			index++;
		}
		if(count == 0){
			return -1;
		}
		return elemIndex;
	}
	/**
	 * Size of the DList object
	 * @return  Size of the DList object
	 */
	public int size(){
		return length;
	}
	/**
	 * Checks if DList object is empty
	 * @return True if empty. False otherwise
	 */
	public boolean empty(){
		return head.next == null;
	}
	public E[] toArray(){
		E[] arr = (E[]) new Object[length];
		DNode<E> currentPtr = head.next;
		int count = 0;
		while(currentPtr != null){
			arr[count] = currentPtr.data;
			count++;
			currentPtr = currentPtr.next;
		}
		return arr;
	}
	/**
	 * Remove the DNode at the given index
	 * @param index location of DNode to delete
	 * @return the deleted data
	 */
	public E remove(int index){
		if(index < length && index >= 0){
			DNode<E> currentPtr = head.next;
			int count = 0;
			while(count < index){
				currentPtr = currentPtr.next;
				count++;
			}
			E temp = currentPtr.data;
			DNode<E> previousPtr = currentPtr.previous;
			DNode<E> nextPtr = currentPtr.next;
			previousPtr.next = nextPtr;
			if(nextPtr != null){
				nextPtr.previous = previousPtr;
			}
			if(index == 0){
				head.next = nextPtr;
			}
			if(index == length - 1){
				tail = previousPtr;
			}
			length--;
			return temp;
		}
		return null;
	}
	/**
	 * Remove the given element from DLinkedList.
	 * @param target element to remove
	 * @throws Illegal argument exception if target is null
	 * @return true if the element is present/removed. False if element was not found
	 */
	public boolean remove(E target) throws IllegalArgumentException{
		if(target == null){
			throw new IllegalArgumentException();
		}
		DNode<E> ptr = head.next;
		int count = 0;
		while(ptr != null){
			if(ptr.data.equals(target)){
				DNode<E> previousPtr = ptr.previous;
				DNode<E> nextPtr = ptr.next;
				previousPtr.next = nextPtr;
				if(nextPtr != null){
					nextPtr.previous = previousPtr;
				}
				if(count == length - 1){
					tail = previousPtr;
				}
				length--;
				return true;
			}
			ptr = ptr.next;
			count++;
		}
		return false;
	}
	/**
	 * Removes from fromIndex to toIndex, inclusive
	 * @param fromIndex first index to remove
	 * @param toIndex last index to remove
	 * @throws IllegalArgumentException if range is backwards
	 */
	public void removeRange(int fromIndex, int toIndex) throws IllegalArgumentException{
		DNode<E> front = head.next;
		DNode<E> back = tail;
		int fCount = 0;
		int bCount = length - 1;
		if(fromIndex > toIndex || fromIndex > length - 1 || toIndex < 0){ //Throws exception if range is invalid
			throw new IllegalArgumentException();
		}
		if(fromIndex < 0){ //Check and corrects lower boundary 
			fromIndex = 0;
		}
		if(toIndex > length - 1){ //Check and corrects upper boundary
			toIndex = length - 1;
		}

		if(toIndex - fromIndex == length - 1){ //If range is entire list, clear list
			clear();
		}
		else{
			while(fCount < fromIndex - 1){
				fCount++;
				front = front.next;
			}
			while(bCount > toIndex + 1){
				bCount--;
				back = back.previous;
			}
			if(fromIndex == 0){
				head.next = back;
				back.previous = head;
			}
			else if(toIndex == length - 1){
				front.next = null;
				tail = front;
			}
			else{
				front.next = back;
				back.previous = front;
			}
			length = length - ((toIndex-fromIndex) + 1);
		}
	}
	/**
	 * Adds all elements in collection to DList starting at the specified index
	 * @param index to add first element
	 * @param collection to iterate through and add to DList
	 * @throws NullPointerException if collection is null
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public void addAll(int index, Iterable<E> collection) throws NullPointerException, IndexOutOfBoundsException{
		if(cmp == null){
			if(collection == null){ 
				throw new NullPointerException();
			}
			if(index < 0 || index > length){ //Check index validity
				throw new IndexOutOfBoundsException();
			}
			Iterator<E>  iter  =  collection.iterator();
			DNode<E> start = head; 
			int count = 0;
			while(count < index){
				count++;
				start = start.next;
			}
			if(index == length){
				while (iter.hasNext()) {	
					add(iter.next());
				}
			}
			else{
				int size = 0;
				while (iter.hasNext()) {	
					E data = iter.next();       
					DNode<E> newNode = new DNode<E>(data);
					DNode<E> nextNode = start.next;
					newNode.next = start.next;
					newNode.previous = start;

					nextNode.previous = newNode;

					start.next = newNode;
					start = newNode;
					size++;
				}
				if(index == length){
					tail = start;
				}
				length += size;
			}
		}
	}

	private class DListIterator implements Iterator<E>{
		private DNode<E> iterPtr;
		public DListIterator(){
			iterPtr = head.next;
		}
		@Override
		public boolean hasNext() {
			return (iterPtr != null);
		}
		@Override
		public E next() {
			if(iterPtr != null){
				E temp = iterPtr.data;
				iterPtr = iterPtr.next;
				return temp;
			}
			return null;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
	}
}	
