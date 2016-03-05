public class TNode {
	public String vertex;
	public int distance;
	public String parent;
	public TNode(String vertex, int distance, String parent){
		this.vertex = vertex;
		this.distance = distance;
		this.parent = parent;
	}
}
