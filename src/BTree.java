import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Implementation of generic BTree
 * @author Nate Riehl
 * CS216, Fall 2015
 */
public class BTree<E> {
	private Comparator<E> cmp;
	private int degree;
	private BTreeNode<E> root;
	public BTree(Comparator<E> c, int deg){
		cmp = c;
		degree = deg;
		root = null;
	}
	/**
	 * Finds possible child to place target
	 * @param ptr data values to look through
	 * @param target to find
	 * @return Child to which target might belong. Null if ptr is leaf
	 */
	private BTreeNode<E> stepDown(BTreeNode<E> ptr, E target){ 
		if(!ptr.isLeaf()){
			int count = 0;
			while(count < ptr.dataSize() && cmp.compare(target, ptr.getData(count)) > 0){
				count++;
			}
			return ptr.getChild(count);
		}
		return null;
	}
	/**
	 * Locates leaf node to which target should be added 
	 * @param target to find location for
	 * @return Leaf node location for target
	 */
	private BTreeNode<E> findLeaf(E target){
		BTreeNode<E> current = root;
		while(stepDown(current, target) != null){
			current = stepDown(current,target);
		}
		return current;
	}

	/**
	 * Adds data to the BTree
	 * @param data to add to BTree
	 */
	public void add(E data){
		if(root != null){
			BTreeNode<E> leaf = findLeaf(data);
			BTreeNode<E> newParent = leaf.add(data);
			if(newParent != null){
				BTreeNode<E> r = newParent;
				while(r.getParent() != null){
					r = r.getParent();
				}
				root = r;
			}
		}
		else{
			root = new BTreeNode<E>(null, cmp, degree);
			root.add(data);
		}
	}
	/**
	 * Checks for node containing target in this tree
	 * @param target to find
	 * @return Node containing target. Null if target is not present
	 */
	private BTreeNode<E> getNode(E target){
		BTreeNode<E> ptr = root;
		while(ptr != null && !ptr.contains(target)){
			ptr = stepDown(ptr, target);
		}
		return ptr;
	}

	/**
	 * Traverses tree in level order and returns in String format
	 * @return Level-order of tree in String format
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Queue<BTreeNode<E>> queue = new LinkedList<BTreeNode<E>>();
		if(root == null){
			return "";
		}
		queue.add(root);
		while(!queue.isEmpty() && queue.peek() != null){
			BTreeNode<E> current = queue.poll();
			sb.append(current.toString() + " ");
			queue.addAll(current.getChildren());
		}
		return sb.toString();
	}
	/**
	 * @return Degree of BTree
	 */
	public int getDegree(){
		return degree;
	}
	/**
	 * @return Root of BTree
	 */
	public BTreeNode<E> getRoot(){ 
		return root;
	}
	/**
	 * Checks if target is in BTree
	 * @param target to look for
	 * @return True if present. False otherwise
	 */
	public boolean contains(E target){
		return (getNode(target) != null);
	}
	/**
	 * Removes target if it is in BTree
	 * @param target to remove
	 * @return True if removed. False if target is not in BTree
	 */
	public boolean remove(E target){
		BTreeNode<E> targetNode = getNode(target);
		BTreeNode<E> r = null;
		if(targetNode != null){
			if(targetNode.isLeaf()){//Leaf node
				r =targetNode.remove(target);
			}
			else{ //Internal node
				int tIndex = targetNode.getData().indexOf(target); //Index of target in targetNode
				BTreeNode<E> successor = targetNode.getChild(tIndex);
				while(!successor.isLeaf()){
					successor = successor.getChild(successor.childrenSize() - 1);
				}
				int sIndex = successor.dataSize() - 1;
				targetNode.getData().set(tIndex, successor.getData(sIndex));
				r = successor.remove(successor.getData(sIndex));
			}
			if(r != null){
				root = r;
			}
			if(root.dataSize() == 0){ //Tree is empty. Set root null.
				root = null;
			}
			return true;
		}
		return false;
	}
}
