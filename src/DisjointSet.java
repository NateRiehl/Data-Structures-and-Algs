import java.util.HashMap;
/**
 * @author Nate Riehl
 * CS216, Fall 2015
 * Implementation of DisjointSet which is utilized in Kruskal's MST algorithm
 */
public class DisjointSet<E> {
	HashMap<E, DSNode<E>> set;
	/**
	 * Instantiates DisjointSet and makes a set for each item
	 * @param items is a list of items
	 */
	public DisjointSet(Iterable<E> items){
		set = new HashMap<E, DSNode<E>>();
		for(E item : items){
			DSNode<E> node = new DSNode<E>();
			set.put(item, node);
			makeSet(item);
		}
	}
	/**
	 * Gets DSNode object from set then
	 *  its parent to item and rank to 0.
	 * @param item to make set w/
	 */
	public void makeSet(E item){
		DSNode<E> n = set.get(item);
		n.parent = item;
		n.rank = 0;
	}
	/**
	 * Gets node with item as key from set, then returns representative of item's set 
	 * @param item that is key
	 * @return representative of item's set
	 */
	public E findRep(E item){
		DSNode<E> n = set.get(item);
				if (n.parent != item){
					n.parent = findRep(n.parent);
				}
		return n.parent;
	}
	/**
	 * Gets nodes w/ d1,d2 as key(sets d1 and d2 are in) then, if they are different, performs union
	 * @param d1 used to get node associated w/ 
	 * @param d2 used to get node associated w/
	 */
	public void union(E d1, E d2){
		DSNode<E> n1 = set.get(d1);		
		DSNode<E> n2 = set.get(d2);
		
		DSNode<E> r1 = set.get(n1.parent);
		DSNode<E> r2 = set.get(n2.parent);
		if(!r1.equals(r2)){
			if(r1.rank == r2.rank){
				r2.parent = findRep(d1);
				r1.rank ++;
			}
			else if(r1.rank > r2.rank){
				r2.parent = findRep(d1);
			}
			else{
				r1.parent = findRep(d2);
			}
		}
	}
	/**
	 * DSNode<E> Class for use in DisjointSet.java
	 * Private class utilized in DisjointSet to implement kruskal's MST algorithm
	 */
	private class DSNode<E>{
		E data, parent; 
		int rank;
	}
}
