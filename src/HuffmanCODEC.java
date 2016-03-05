/**
 * Implementation of Encoding/Decoding algorithms for a HuffmanTree
 * @author Nate Riehl
 *CS216, Fall 2015
 */
public class HuffmanCODEC {
	private HuffmanTree codeTree;
	public HuffmanCODEC(HuffmanTree tree){
		codeTree = tree;
	}

	public void setTree(HuffmanTree tree){
		codeTree =tree;
	}
	/**
	 * Encodes msg using HuffmanTree 
	 * @param msg to encode
	 * @return Encoded string using Huffman tree. Null if a problem occurred 
	 */
	public String encode(String msg){
		HuffmanNode ptr = codeTree.getRoot();
		String encoded = "";
		if(codeTree.empty()){
			return null;
		}
		for(int c = 0; c < msg.length(); c++){
			ptr = (HuffmanNode) codeTree.getRoot();
			if(!ptr.contains(msg.charAt(c))){
				return null;
			}
			while(!ptr.isLeaf()){
				HuffmanNode left = (HuffmanNode) ptr.getLeft();
				HuffmanNode right = (HuffmanNode) ptr.getRight();
				if(left.contains(msg.charAt(c))){
					ptr = left;
					encoded += "0";
				}
				else{
					ptr = right;
					encoded += "1";
				}
			}
		}
		return encoded;
	}
	/**
	 * Decodes bits using HuffmanTree and returns the decoded message
	 * @param bits to decode
	 * @return Decoded message. Null if a problem occurred 
	 */
	public String decode(String bits){
		HuffmanNode ptr = (HuffmanNode) codeTree.getRoot();
		String decoded = "";
		if(codeTree.empty()){
			return null;
		}
		for(int b = 0; b < bits.length(); b++){
			if(bits.charAt(b) == '0'){
				ptr = (HuffmanNode) ptr.getLeft();
			}
			else if(bits.charAt(b) == '1'){
				ptr = (HuffmanNode) ptr.getRight();
			}
			if(ptr.isLeaf()){
				decoded += ptr.leafValue();
				ptr = (HuffmanNode) codeTree.getRoot();
			}
		}
		if(ptr != codeTree.getRoot()){
			return null;
		}
		return decoded;
	}

	public static void main(String[] args){
		/**Testing provided by Professor Kim*/
		HuffmanTree tree1 = new HuffmanTree();
		HuffmanTree tree2 = new HuffmanTree();

		char[] a1 = {'a', 'b', 'c', 'd'}; // array of 1 set of characters
		char[] a2 = {'e', 'f', 'g', 'h'}; // array of another set of characters
		int[] w1 ={1,2,3,4} ; // array of weights for a1
		int[] w2 ={2,1,1,3} ; // array of weights for a2  (same # of elements as a2)

		tree1.build(a1, w1);
		tree2.build(a2, w2);

		HuffmanCODEC codec = new HuffmanCODEC(tree1);  
		String msg = "abcd";
		String bits =  codec.encode(msg);    // encode using tree1
		System.out.println("Bits are: " + bits);
		codec.setTree(tree2);                   // change code tree
		String decoded = codec.decode(bits);                    // try to decoding using tree2
		System.out.println("Decoded is: " + decoded);
	}
}
