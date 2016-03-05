import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * BTreeNode<E> Implementation
 * @author Nate Riehl
 * CS216, Fall 2015
 * @param <E> Generic BTreeNode
 */
public class BTreeNode<E> {
	private BTreeNode<E> parent;
	private Comparator<E> cmp;
	private int degree;
	private ArrayList<E> data;
	private ArrayList<BTreeNode<E>> children;
	public BTreeNode(BTreeNode p, Comparator cmp, int deg){
		parent = p;
		this.cmp = cmp;
		degree = deg;
		data = new ArrayList<E>();
		children = new ArrayList<BTreeNode<E>>();
	}
	/**
	 * Degree of BTreeNode
	 * @return degree of BTreeNode
	 */
	public int getDegree(){
		return degree;
	}
	/**
	 * Return data at node enclosed in square brackets
	 * @return data in String format
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		if(dataSize() > 0){
			for(int i = 0; i < dataSize(); i++){
				sb.append(data.get(i) + " ");
			}
		}
		sb.append("]");
		return  sb.toString();
	}
	/**
	 * Check if a node is a leaf
	 * @return True if leaf. False otherwise
	 */
	public boolean isLeaf(){
		return (children.isEmpty());
	}
	/**
	 * Add child node children at end of list
	 * @param child node to add
	 */
	public void addChild(BTreeNode<E> child){
		child.setParent(this);
		children.add(child);
	}
	/**
	 * (Helper-method) Add Child at correct position in children
	 * @param child to add
	 */
	private void setChild(BTreeNode<E> child){
		child.setParent(this);
		int count = 0;
		while(count < children.size() && cmp.compare(child.getData(0), children.get(count).getData(0)) > 0){
			count++;
		}
		children.add(count, child);
	}
	/**
	 * Set ArrayList of children to newChildren
	 * @param kids new ArrayList to set
	 */
	public void setChildren(List<BTreeNode<E>> newChildren){
		for(BTreeNode<E> child : newChildren){
			child.setParent(this);
		}
		children.addAll(newChildren);
	}

	/**
	 * Return child at position index
	 * @param pos index of child
	 * @return child at pos. Null if pos is not a valid index
	 */
	public BTreeNode<E> getChild(int pos){
		if(pos >-1 && pos < children.size()){
			return children.get(pos);
		}
		return null;
	}
	/**
	 * @return ArrayList of BTreeNodes of node's children
	 */
	public ArrayList<BTreeNode<E>> getChildren(){
		return children;
	}
	/**
	 * Amount of data in node
	 * @return Number of data at node
	 */
	public int dataSize(){
		return data.size();
	}
	/**
	 * Amount of children
	 * @return Amount of children
	 */
	public int childrenSize(){
		return children.size();
	}
	/**
	 * @return ArrayList<E> of data
	 */
	public ArrayList<E> getData(){
		return data;
	}
	/**
	 * Returns data at pos
	 * @param pos to get data
	 * @return data at pos
	 */
	public E getData(int pos){
		if(pos > -1 && pos < data.size()){
			return data.get(pos);
		}
		return null;
	}
	/**
	 * Set Data ArrayList to newDataSet
	 * @param newDataSet to put in Data
	 */
	public void setData(List<E> newDataSet){
		data.addAll(newDataSet);
	}
	/**
	 * Check if target is contained in Data
	 * @param target to look for
	 * @return True if target is in data set. False otherwise
	 */
	public boolean contains(E target){
		return data.contains(target);
	}
	/**
	 * Returns parent of node
	 * @return parent of node. Null if no parent
	 */
	public BTreeNode<E> getParent(){
		if(parent != null){
			return parent;
		}
		return null;
	}
	/**
	 * Set parent of node
	 * @param p parent of node
	 */
	public void setParent(BTreeNode<E> p){
		parent = p;	
	}
	/**
	 * Checks if an overflow has occurred 
	 * @return True if overflow. False otherwise
	 */
	public boolean isOverflow(){
		return ((data.size() > degree - 1) || (children.size() > degree));
	}
	/**
	 * Checks if underflow has occurred
	 * @return true if underflow. False otherwise
	 */
	public boolean isUnderflow(){
		/**
		 *  If it is a root node and is a leaf node, return false (to allow the tree to become empty)
		 */
		if(parent == null && isLeaf()){// If it is a root node and is a leaf node, return false (to allow the tree to become empty)
			return false;
		}
		else if(parent == null && !isLeaf()){//If it is a root node but not a leaf,should have at least 1 data value and 2 children
			return dataSize() < 1 && childrenSize() < 2;
		}
		else if(isLeaf()){
			return (dataSize() < Math.ceil(degree/2.0)-1);//If leaf, check only for data list size
		}
		else{
			return (dataSize() < Math.ceil(degree/2.0)-1) || (childrenSize() < Math.ceil(degree/2.0));// If internal, also check children list size
		}
	}
	/**
	 *Handles overload by splitting nodes and creating a new parent if necessary
	 * @return parent if no overflow. Otherwise add and check overflow on parent (if no, Null is returned by add())
	 */
	private BTreeNode<E> split(){
		BTreeNode<E> right = new BTreeNode<E>(this.getParent(), cmp, degree);
		int midData = dataSize()/2;
		int midChild = children.size()/2; 
		right.setData(data.subList(midData + 1, data.size()));
		data.subList(midData + 1, data.size()).clear();
		if(!isLeaf()){
			right.setChildren(children.subList(midData + 1, children.size()));
			children.subList(midData + 1, children.size()).clear();
		}
		if(parent == null){
			parent = new BTreeNode<E>(null, cmp, degree);
			parent.addChild(this);
			parent.setData(data.subList(midData, midData + 1));
			data.remove(dataSize() - 1);
			parent.addChild(right);
			return parent; //Newly created parent is returned
		}
		parent.setChild(right);
		return	parent.add(data.remove(midData)); //Promote data by calling add() on parent. Null if no overflow or calls split()
	}
	/**
	 * Adds d to this node's data collection in the right place (data must be ordered) and split if there is an overflow. 
	 * @param d
	 * @return Null if there is no overflow. Newly created node if split() resulted in new nodes
	 */
	public BTreeNode<E> add(E d){
		int index = 0;
		while(index < dataSize() && cmp.compare(d, data.get(index)) > 0){
			index ++;
		}
		data.add(index, d);
		if(!isOverflow()){
			return null;
		}
		return split();	
	}
	/**
	 * Checks node's siblings and returns the sibling with more data
	 * @return Sibling with more data values. Null if node has no siblings
	 */
	public BTreeNode<E> findDonorSibling(){ 
		ArrayList<BTreeNode<E>> siblings = parent.getChildren();
		int indexOfNode = siblings.indexOf(this);

		if(indexOfNode - 1 < 0 && indexOfNode + 1 <= siblings.size() - 1){
			return siblings.get(indexOfNode + 1);
		}
		else if(indexOfNode - 1 >= 0 && indexOfNode + 1 > siblings.size() - 1){
			return siblings.get(indexOfNode - 1);
		}
		BTreeNode<E> leftSib = siblings.get(indexOfNode - 1);
		BTreeNode<E> rightSib = siblings.get(indexOfNode + 1);
		if(leftSib.dataSize() >= rightSib.dataSize()){
			return leftSib;
		}
		return rightSib;
	}
	/**
	 * Checks if BTreeNode can donate without becoming deficient
	 * @return True if it can donate. False otherwise
	 */
	public boolean canDonate(){
		if(data.size() >= Math.ceil((degree)/2.0) - 1){
			return true;
		}
		return false;
	}
	/**
	 * Fixes deficiency in node by receiving data from sibling (left OR right),parent which can donate
	 * @param sib Sibling to donate to this
	 * @param myGainIndex Index in this node to add new data
	 * @param sibLossIndex Index of data to take from Sibling
	 * @param pSepIndex parent separator index
	 */
	private void rotate(BTreeNode<E> sib, int myGainIndex, int sibLossIndex, int pSepIndex){
		E pData = parent.getData(pSepIndex);
		E sibData = sib.getData(sibLossIndex);
		ArrayList<BTreeNode<E>> siblings = parent.getChildren();

		data.add(myGainIndex, pData);

		parent.getData().set(pSepIndex, sibData);
		sib.getData().remove(sibLossIndex);

		if(!sib.isLeaf()){
			boolean leftSib = siblings.indexOf(sib) < siblings.indexOf(this);
			if(leftSib){
				setChild(sib.getChildren().remove(sib.childrenSize() - 1));
			}
			else{
				setChild(sib.getChildren().remove(0));	
			}
		}
	}
	/**
	 *Fixes deficiency in node by joining of nodes
	 * @param left node to join
	 * @param right node to join
	 * @param pSepIndex Parent Separator
	 * @return  Joined node if root changes 
	 */
	public static<E> BTreeNode<E> join(BTreeNode<E> left, BTreeNode<E> right, int pSepIndex){
		BTreeNode<E> p = left.getParent();//Parent
		left.add(p.getData(pSepIndex));  //Add parent data

		left.setData(right.getData()); //Add right data
		right.getData().clear(); //empty right
		if(!right.isLeaf()){
			for(int i = 0; i < right.getChildren().size(); i++){
				left.setChild(right.getChild(i)); //Get right's children
			}
		}
		p.getChildren().remove(right); //Remove right
		right.setParent(null);

		return p.remove(p.getData(pSepIndex)); //Will return joined node (OR call handleUndeflow) in remove() method if necessary 
	}
	/**
	 * Handler for when a node underflows. Uses join() (Sibling can't donate) or rotate() (sibling can donate) to fix deficiency in node
	 * @return Null if handled. Implicitly calls handleUnderflow on parent if necessary
	 */
	public BTreeNode<E> handleUnderflow(){
		BTreeNode<E> helperNode = findDonorSibling();

		ArrayList<BTreeNode<E>> siblings = parent.getChildren();
		boolean leftSib = siblings.indexOf(helperNode) < siblings.indexOf(this);
		int pSepIndex = leftSib ? siblings.indexOf(this) - 1 : siblings.indexOf(this);
		if(!helperNode.canDonate()){ /**No sibling can help so join*/
			return join(helperNode, this, pSepIndex);
		}
		if(leftSib){ /** Have left. rotate right*/
			BTreeNode<E> left = siblings.get(pSepIndex);
			rotate(helperNode, 0, left.dataSize() - 1, pSepIndex);
		}
		else{ /**Have right. rotate left*/
			rotate(helperNode, dataSize(), 0, pSepIndex);
		}
		return null;
	}
	/**
	 * Removes target from node and checks if underflow
	 * @param target to remove
	 * @return Null if no underflow. Joined node if root has to changed. Otherwise, handleUnderflow().
	 */
	public BTreeNode<E> remove(E target){
		data.remove(target);
		if((parent == null || parent.dataSize() == 0)&& dataSize() == 0){//Check for if root changes
			return getChild(0); //New root
		}
		else if(!isUnderflow()){
			return null;
		}
		return handleUnderflow();
	}
}
