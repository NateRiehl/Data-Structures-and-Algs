import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * Implementation of Graph
 * @author Nate Riehl
 * CS216, Fall 2015
 */
public class Graph {
	private HashMap<String, Vertex> vertices;
	private ArrayList<Vertex> vOrdered;
	private ArrayList<Edge> edges;
	private double[][] adjMatrix;
	private Scanner scanner;
	/**
	 * Creates a graph with vertices from given vFile and using the given adjacency matrix (eFile)
	 * @param vfile File of vertices
	 * @param efile Adjacency matrix with weights
	 */
	public Graph(String vFile, String eFile){
		edges = new ArrayList<Edge>();
		vertices = new HashMap<String, Vertex>(); 
		vOrdered = new ArrayList<Vertex>();  
		setupVerts(vFile);
		setupMatrix(eFile);
	}
	/**
	 * Helper method to add vertices to ArrayList and HashMap
	 * @param vFile is file of vertices
	 */
	private void setupVerts(String vFile){
		try{
			File verts = new File(vFile);
			scanner = new Scanner(verts);
			while(scanner.hasNext()){
				Vertex v = new Vertex(scanner.next());
				vertices.put(v.getLabel(), v);
				vOrdered.add(v);
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Error reading vertex file");
			System.out.println(e.getStackTrace());
		}
	}
	/**
	 * Helper method to read eFile and setup adj matrix and adj for each vertex
	 * @param eFile is the adjacency matrix file
	 */
	private void setupMatrix(String eFile){
		try{
			File edgeFile = new File(eFile);
			scanner = new Scanner(edgeFile);
			adjMatrix = new double[vOrdered.size()][vOrdered.size()];
				for(int r = 0; r < vOrdered.size(); r++){
					for(int c = 0; c < vOrdered.size(); c++){
						adjMatrix[r][c] = scanner.nextDouble();
						if(adjMatrix[r][c] != 0){
							Vertex fromV = vOrdered.get(r);
							Vertex toV = vOrdered.get(c);
							Edge e = new Edge(fromV, toV, adjMatrix[r][c]);
							fromV.addAdj(e);
							edges.add(e);
						}
						else if(r != c){
							adjMatrix[r][c] = Double.POSITIVE_INFINITY;
						}
					}
				}
			}
		catch(FileNotFoundException e){
			System.out.println("Error reading edge file");
			System.out.println(e.getStackTrace());
		}
	}
	/**
	 * Resets each vertex in graph to standard variable values. (pred = null, cost = infinity, marked = false)
	 */
	public void	resetVerts(){
		for(Vertex v : vOrdered){
			v.reset();
		}
	}	
	/**
	 * Gets vertices in ArrayList format sorted alphabetically
	 * @return vertices in graph
	 */
	public ArrayList<Vertex> getVerts(){
		return vOrdered;
	}	
	/**
	 * Gets edges in ArrayList format 
	 * @return edges in graph
	 */
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	/**
	 * Number of vertices in graph
	 * @return number of vertices
	 */
	public int nVerts(){
		return vOrdered.size();
	}
	/**
	 * Number of edges in graph
	 * @return number of edges
	 */
	public int nEdges(){
		return edges.size();
	}
	/**
	 * Gets vertex with given label
	 * @param label to find vertex with
	 * @return vertex with label. Null if not present
	 */
	public Vertex getVertex(String label)	{
		return vertices.get(label);
	}
	/**
	 * Gets vertex with given index
	 * @param index of vertex
	 * @return vertex at given index. Null if not present.
	 */
	public Vertex getVertex(int index){
		if(index > -1 && index < vOrdered.size()){
			return vOrdered.get(index);
		}
		return null;
	}	
	/**
	 * Gets edge with vsrc and vdst vertices
	 * @param vsrc source vertex
	 * @param vdst dest. vertex
	 * @return edge with vsrc/vdst. Null if not present
	 */
	public Edge getEdge(Vertex vsrc, Vertex vdst){
		return getEdge(vsrc.getLabel(), vdst.getLabel());
	}
	/**
	 * Gets edge with src/dst labels
	 * @param src label of source vertex
	 * @param dst label of dest. vertex
	 * @return edge with src/dst. Null if not present
	 */
	public Edge getEdge(String src, String dst){
		Vertex vSrc = vertices.get(src);
		Vertex vDst = vertices.get(dst);
		for(Edge e : edges){
			if(e.equals(new Edge(vSrc, vDst))){
				return e;
			}
		}
		return null;
	}
	/**
	 * Gets adjacency matrix of graph
	 * @return adjacency matrix
	 */
	public double[][]	adjMatrix(){
		return adjMatrix;
	}
	/**
	 * toString method that returns the toString() of each vertex in graph
	 * @return concatenation of toString() from each vertex
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Vertex v : vOrdered){
			sb.append(v.toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * (to be used for Kruskal's algorithm)
	 * convert directed graph to undirected graph and return the edges
	 * @return list of edges sorted by weight first then by edge label in increasing order
	 */
	public ArrayList<Edge> getEdgesUndirected() {
	    ArrayList<Edge> und = new ArrayList<Edge>();
	    EdgeComp edgeComp = new EdgeComp();

	    for (Edge e : edges) {
	        Vertex src = e.getSrc();
	        Vertex dst = e.getDst();
	        double w   = e.getWeight();

	        // each edge's src vertex is < dst vertex in undirected graph for easier comparison
	        String srcLabel = src.getLabel();
	        String dstLabel = dst.getLabel();

	        if (srcLabel.compareTo(dstLabel) > 0) {
	            Vertex tmp = src;
	            src = dst;
	            dst = tmp;
	        }

	        Edge ne = new Edge(src, dst, w);

	        // similar to one iteration of the insertion sort algorithm
	        // add the newly created edge to a correct location according to the sort order
	        int i = 0;
	        while (i < und.size() && edgeComp.compare(ne, und.get(i)) > 0) {
	            ++i;
	        }
	        und.add(i, ne);
	    }

	    return und;
	}

	  /**
     * (to be used before applying Prim's algorithm)
     * for each directed edge (v->u) add edge (u->v) if it doesn't exist
     * if it does, update edge (u->v)'s weight to the weight of edge (v->u)
     * update adjacency matrix accordingly
     */
    public void toUndirected() {
        for (Vertex v : vOrdered) {
            int vID = v.getID(); // or v.getIndex() if your selector is getIndex instead of getID
            TreeMap<String, Edge> v_adj = v.getAdj();
            // for each edge (v->u)
            for (Edge e : v_adj.values()) {
                Vertex u = e.getDst();
                double w = e.getWeight();
                int uID  = u.getID(); // or u.getIndex() if your selector is getIndex instead of getID
                // add v as neighbor to dst if it isn't already a neighbor
                // edge (u->v)
                if (u.getAdj(v.getLabel()) == null) {
                    Edge newE = new Edge(u, v, w);
                    u.addAdj(newE);
                    edges.add(newE);
                }
                // if edge (u->v) already exists, need to update it with the same weight
                else {
                    Edge oldE = u.getAdj(v.getLabel());
                    oldE.setWeight(w);
                }
                // update the adjacency matrix accordingly
                adjMatrix[uID][vID] = w;
            }
        }
    }
    
    public void reciprocate(Vertex v){
    		int index = v.getID();
    		for(int row = 0; row < adjMatrix.length; row++){
    			if(adjMatrix[row][index] == Double.POSITIVE_INFINITY && adjMatrix[index][row] != Double.POSITIVE_INFINITY){
    				adjMatrix[index][row] = 0;
    			}
    		}
    }
	/**
	 * EdgeComp.java
	 * comparator for edge weights first then edge labels
	 */
	private class EdgeComp implements Comparator<Edge> {
	    public int compare(Edge e1, Edge e2) {
	        int diff = (int) (Math.round((e1.getWeight() - e2.getWeight()) * 100)) / 100;

	        // if weights of the two edges are the same, compare the edge labels
	        if (diff == 0) {
	            String eLabel1 = e1.getSrc().getLabel() + e1.getDst().getLabel();
	            String eLabel2 = e2.getSrc().getLabel() + e2.getDst().getLabel();

	            return eLabel1.compareTo(eLabel2);
	        }
	        else {
	            return diff;
	        }
	    }
	}
}
