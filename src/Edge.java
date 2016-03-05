/**
 * Implementation of Edge for Graph
 * @author Nate Riehl
 * CS216, Fall 2015
 */
public class Edge {
	private Vertex src;
	private Vertex dest;
	private double weight;
	private static EdgeLabelComp cmp = new EdgeLabelComp();
	/**
	 * Creates edge with source and destination vertices and weight
	 * @param src is source vertex
	 * @param dest is destination vertex
	 * @param weight is the weight of edge
	 */
	public Edge(Vertex src, Vertex dest, double weight){
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		cmp = new EdgeLabelComp();
	}
	/**
	 * Creates edge between vsrc and vdst with a standard weight of 1
	 * @param vsrc is source vertex
	 * @param vdst is destination vertex
	 */
	public Edge(Vertex vsrc, Vertex vdst){
		src = vsrc;
		dest = vdst;
		weight = 1;
	}	
	/**
	 * Gets weight of edge
	 * @return weight of edge
	 */
	public double getWeight(){
		return weight;
	}
	/**
	 * Gets source vertex of edge
	 * @return source vertex
	 */
	public Vertex getSrc(){
		return src;
	}
	/**
	 * 	Gets destination vertex of edge
	 * @return destination vertex
	 */
	public Vertex getDst(){
		return dest;
	}
	/**
	 * overrides the equals method in Object class
	 *@return True if obj is an edge and src/dst labels match
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Edge){
			Edge edge = (Edge) obj;
			if(cmp.compare(this, edge) == 0 && src.getLabel().equals(edge.getSrc().getLabel())){
				return true;
			}
		}
		return false;
	}
	/**
	 * toString() method that returns src/dst information as well as weight
	 * @return this edge's information in String format
	 */
	@Override
	public String toString(){
		return "" + getSrc().getLabel()+"-"+getDst().getLabel()+" "+" ("+getWeight()+")";
	}
	/**
	 * Sets weight to w
	 * @param w is weight to set edge to
	 */
	public void setWeight(double w) {
		weight = w;
		}
}
