import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * BSTree.java
 * @author Nate Riehl
 *Implementation of Binary Search Tree
 *CS216, Fall 2015
 *HW 5
 */
public class BSTree<E> {
	private Comparator cmp;
	private BTNode<E> root;
	private int count;
	/**
	 * Constructor utilizing Comparator for ordering of tree
	 * @param cmp
	 */
	public BSTree(Comparator cmp){
		this.cmp = cmp;
		count = 0;
	}
	/**
	 * Adds data to the correct place in the tree according to the comparator cmp
	 * @param data to add
	 */
	public void add(E data){
		if(empty()){
			root = new BTNode<E>(data, null, null);
			count++;
		}
		else if(data != null){
			BTNode<E> current = root;
			BTNode<E> parent;
			while(current != null){
				parent = current;
				if(cmp.compare(data, current.getData()) <= 0){
					current = current.getLeft();
					if(current == null){
						parent.setLeft(new BTNode<E>(data, null, null));
					}
				}
				else{
					current = current.getRight();
					if(current == null){
						parent.setRight(new BTNode<E>(data, null, null));
					}
				}
			}
			count++;
		}
	}
	/**
	 * Checks if target data is contained in BSTree
	 * @param target to look for
	 * @return True if present. False otherwise
	 */
	public boolean contains(E target){
		if(target == null || empty()){
			return false;
		}
		BTNode<E> current = root;
		while(current != null){
			if(cmp.compare(target, current.getData()) == 0){
				return true;
			}
			else if(cmp.compare(target, current.getData()) < 0){
				current = current.getLeft();
			}
			else{
				current = current.getRight();
			}
		}
		return false;
	}
	/**
	 * Remove node at right most of left subtree
	 * @param node to check's subtree
	 * @return Data of removed node
	 */
	private E removeRightMostNode(BTNode<E> node){
		BTNode<E> current = node.getLeft();
		BTNode<E> parent = null;
		while(current.hasRight()){
			parent = current;
			current = current.getRight();
		}
		E temp = current.getData();
		if(current.hasLeft()){
			parent.setRight(current.getLeft());
		}
		else{
			parent.setRight(null);
		}
		return temp;
	}

	/**
	 * Removes target data from BSTree if it is present
	 * @param target to find and remove
	 * @return removed data. Null if data is not present
	 */
	public E remove(E target){ 
		BTNode<E> parent = null;
		BTNode<E> current = root;
		while(current != null && cmp.compare(target, current.getData()) != 0){ 
			parent = current;
			if(cmp.compare(target, current.getData()) < 0){
				current = current.getLeft();
			}
			else{
				current = current.getRight();
			}
		}
		if(current != null){
			if(current.isLeaf()){
				if(size() == 1){
					root = null;
				}
				else if(cmp.compare(current.getData(), parent.getData()) < 0){
					parent.setLeft(null);
				}
				else{		
					parent.setRight(null);
				}
			}
			else if(current.hasLeft()){
				if(current.getLeft().hasRight()){
					current.setData(removeRightMostNode(current));
				}
				else{
					current.setData(current.getLeft().getData());
					current.setLeft(current.getLeft().getLeft());
				}
			}
			else{
				if(!(parent == null)){ 
					if(cmp.compare(current.getData(), parent.getData()) < 0){
						parent.setLeft(current.getRight());
					}
					else{
						parent.setRight(current.getRight());
					}
				}
				else{			
					root = current.getRight();
				}
			}
			count--;
			return target;
		}
		return null;
	}
	/**
	 * Return root of tree
	 * @return Root node. Null if no root
	 */
	public BTNode<E> getRoot(){
		return root;
	}
	/**
	 * Return size of tree
	 * @return size of tree
	 */
	public int size(){
		return count;
	}
	/**
	 * Checks if tree is empty
	 * @return true if empty. False otherwise
	 */
	public boolean empty(){
		return (size() == 0);
	}
	/**
	 * Clears tree and resets count
	 */
	public void clear(){
		if(root != null){
			root.setLeft(null);
			root.setRight(null);
			root = null;
			count = 0;
		}
	}

	/**For exam two*/
	public boolean isHeightBalanced(BTNode<E> n){//TODO
		if(n == null){
			return true;
		}
		int heightDiff = Math.abs(n.getLeft().getHeight() - n.getRight().getHeight());
		if(heightDiff > 1){
			return false;
		}
		else{
			return isHeightBalanced(n.getLeft()) && isHeightBalanced(n.getRight());
		}
	}
	/**For exam two*/
	public int sizeTree(){
		return size(root);
	}

	public int size(BTNode<E> node){
		if(node == null){
			return 0;
		}
		else{
			return 1 + size(node.getLeft())+ size(node.getRight());
		}
	}

	/**Print paths in tree*/
	public void printPaths(){
		E[] list = (E[])new Object[1000];
		print(root, list, 0);
	}
	public void print(BTNode<E> n, E[] arr, int pLength){
		if(n != null){
			arr[pLength] = n.getData();
			pLength++;			
			if(n.isLeaf()){
				printArray(arr, pLength);
				System.out.println();
			}
			else{
				print(n.getLeft(), arr, pLength);
				print(n.getRight(), arr, pLength);
			}
		}
	}
	private void printArray(E[] ints, int len) { 
		  int i; 
		  for (i=0; i<len; i++) { 
		   System.out.print(ints[i] + " "); 
		  } 
		  System.out.println(); 
		} 
	/**
	 * Returns tree in String format in level order
	 */
	public String toString(){
		return "[ " + BTNode.levelOrder(root) + "]";
	}
}
