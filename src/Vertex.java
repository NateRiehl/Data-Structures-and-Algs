import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * Implementation of Vertex for Graph.java
 * @author Nate Riehl
 * CS216, Fall 2015
 */
public class Vertex {
	private int id;
	private static int nextID;
	private String label;
	private Vertex pred;
	private double cost;
	private boolean visited;
	private TreeMap<String, Edge> adj;
	private static StringComparator stringComp = new StringComparator();
	/**
	 * Creates a new vertex with given label
	 * @param label of vertex
	 */
	public Vertex(String label){
		this.label = label;
		cost = Double.POSITIVE_INFINITY;
		adj = new TreeMap<String, Edge>(stringComp);
		id = nextID;
		nextID++;
	}	
	/**
	 * resets global variables of vertex
	 */
	public void	reset(){
		visited = false;
		cost = Double.POSITIVE_INFINITY;
		pred = null;
	}
	/**
	 * Marks as visited
	 */
	public void	mark(){
		visited= true;
	}	
	/**
	 * marks as not visited
	 */
	public void	unmark(){
		visited = false;
	}	
	/**
	 * set the predecessor of this Vertex to p
	 */
	public void	setPred(Vertex p)	{
		pred = p;
	}
	/**
	 * set the cost of Vertex to c
	 * @param c new cost of vertex
	 */
	public void	setCost(double c){
		cost = c;
	}	
	/**
	 * adds c to current cost of Vertex
	 * @param c cost to add to current cost
	 */
	public void	addCost(double c){
		cost += c;
	}	
	/**
	 * Checks if vertex is visited
	 * @return True if visited. False otherwise
	 */
	public boolean isMarked(){
		return (visited == true);
	}
	/**
	 * Gets label of vertex
	 * @return the label of vertex
	 */
	public String getLabel(){
		return label;
	}	
	/**
	 * Gets the id of Vertex
	 * @return Id of vertex
	 */
	public int getID(){
		return id;
	}
	/**
	 * 	cost of Vertex's path
	 * @return  cost of Vertex path
	 */
	public double	getCost(){
		return cost;
	}
	/**
	 * 	Gets the predecessor of this Vertex 
	 * @return predecessor Vertex
	 */
	public Vertex	getPred(){
		return pred;
	}
	/**
	 * Gets the number of neighbors for this vertex
	 * @return  Size of adjacency list
	 */
	public int getAdjSize(){
		return adj.size();
	}
	/**
	 * Gets adjacency list in form of a TreeMap sorted by Destination vertex
	 * @return Adjacency list as a TreeMap sorted by Destination Vertex
	 * DSt label, Edge
	 */
	public TreeMap<String, Edge> getAdj(){
		return adj;
	}
	/**
	 * Gets a specific edge between this vertex and dst vertex
	 * @param dst destination of edge
	 * @return edge that has this as vSrc and dst as vDst
	 */
	public Edge getAdj(String dst){
		return adj.get(dst);
	}
	/**
	 * Override of toString method to provide label, id, and neighbor information
	 * @return Vertex information in string format
	 */
    public String toString() {
        String str = String.format("%s (%2d, %2d): ", label, id, adj.size());
        for (Edge e : adj.values()) {
            str += String.format("%s (%.1f) ", e.getDst().getLabel(), e.getWeight());
        }
        return str;
    }
	/**
	 * Adds a new edge between this and vdst with a weight of w 
	 * @param vdst destination vertex of edge
	 * @param w weight of edge
	 */
	public void	addAdj(Vertex vdst, double w){
		Edge e = new Edge(this, vdst, w);
		adj.put(vdst.getLabel(), e);
	}
	/**
	 * Adds edge to adjacency list
	 * @param e edge to add
	 */
	public void addAdj(Edge e){
		Vertex vDest = e.getDst();
		adj.put(vDest.getLabel(), e);
	}

}
