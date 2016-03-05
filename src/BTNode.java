import java.util.LinkedList;
import java.util.Queue;
/**
 * BTNode class for BSTree
 * @author Nate Riehl
 *CS216, Fall 2015
 */
public class BTNode<E> {
	private E data; 
	private BTNode<E> left, right;

	public BTNode(){
	}
	public BTNode(E data, BTNode<E> left, BTNode<E> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	/**
	 * Tree traversal in level order
	 * @param node to start traversal
	 * @return String format of tree in level order
	 */
	public static String levelOrder(BTNode node){
		StringBuilder sb = new StringBuilder();
		Queue<BTNode> queue = new LinkedList<BTNode>();
		if(node == null){
			return "";
		}
		queue.add(node);
		while(!queue.isEmpty() && queue.peek() != null){
			BTNode current = queue.poll();
			sb.append(current.getData() + " ");
			if(current.hasLeft()){
				queue.add(current.getLeft());
			}
			if(current.hasRight()){
				queue.add(current.getRight());
			}
		}
		return sb.toString();
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public BTNode<E> getLeft() {
		return left;
	}
	public void setLeft(BTNode<E> left) {
		this.left = left;
	}
	public BTNode<E> getRight() {
		return right;
	}
	public void setRight(BTNode<E> right) {
		this.right = right;
	}

	/**Predicates*/
	public boolean isLeaf(){
		return (left == null && right == null);
	}

	public boolean hasLeft(){
		return (left != null);
	}

	public boolean hasRight(){
		return (right != null);
	}
/**
 * @return String format in preorder
 */
	public String preorder(){
		String str = data + ", ";
		if(left != null){
			str += left.preorder();
		}
		if(right != null){
			str += right.preorder();
		}
		return str;
	}
/**
 * String format of tree in preorder 
 * @param node to start 
 * @return preorder of tree from node
 */
	public static <E> String preorder(BTNode<E> node){
		String str = " "; 
		if(node != null){
			str += node.getData() + " " + preorder(node.getLeft()) + preorder(node.getRight());
		}
		return str;
	}
	/**
	 * String format of tree in preorder 
	 * @param node to start 
	 * @return preorder of tree from node
	 */
		public static <E> String postorder(BTNode<E> node){
			String str = " "; 
			if(node != null){
				str +=  preorder(node.getLeft()) + preorder(node.getRight()) + node.getData() + " " ;
			}
			return str;
		}
	/**
	 * Calculates height of tree
	 * @return height of tree
	 */
	public int getHeight(){
		if(isLeaf()){
			return 1;
		}
		int lHeight = 0;
		int rHeight = 0;

		if(hasLeft()){
			lHeight = left.getHeight();
		}
		if(hasRight()){
			rHeight = right.getHeight();
		}
		if(lHeight > rHeight){
			return (lHeight + 1);
		}
		return (rHeight + 1);
	}
	/**
	 * Calculates height of tree from parameter node
	 * @param node to start calculating height
	 * @return height of tree
	 */
	public static<E> int getHeight(BTNode node){
		if(node != null){
			if(node.isLeaf()){
				return 1;
			}
			int lHeight = 0;
			int rHeight = 0;
			if(node.hasLeft()){
				lHeight = getHeight(node.getLeft());
			}
			if(node.hasRight()){
				rHeight = getHeight(node.getRight());
			}
			if(lHeight > rHeight){
				return (lHeight + 1);
			}
			return (rHeight + 1);
		}
		return 0;
	}

/**
 * Size of tree from node
 * @param node to begin calculating size
 * @return size of tree from node
 */
	public static <E> int getCount(BTNode node){
		if(node == null){
			return 0;
		}
		return (getCount(node.getLeft()) + getCount(node.getRight()) + 1);
	}

	public static void main(String[] args){
		BTNode<Integer> n1 = new BTNode<Integer>(1, null, null);
		BTNode<Integer> n2 = new BTNode<Integer>(7, null, null);
		BTNode<Integer> n3 = new BTNode<Integer>(15, null, null);
		BTNode<Integer> n4 = new BTNode<Integer>(25, null, null);
		BTNode<Integer> n5 = new BTNode<Integer>(40, null, null);
		BTNode<Integer> n6 = new BTNode<Integer>(5, n1, n2);
		BTNode<Integer> n7 = new BTNode<Integer>(10, n6, n3);
		BTNode<Integer> n8 = new BTNode<Integer>(30, n4, n5);
		BTNode<Integer> n9 = new BTNode<Integer>(20, n7, n8);
		System.out.println(preorder(n9));	
		System.out.println(getHeight(n9));
		System.out.println(getCount(n9));
	}
}
