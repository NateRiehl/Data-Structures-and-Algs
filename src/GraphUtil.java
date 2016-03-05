import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
/**
 * Implementation of several utility methods for Graph 
 * @author Nate Riehl
 * CS216, Fall 2015
 */
public class GraphUtil {
	/**
	 * Breadth-first search of Graph
	 * @param g Graph to search
	 * @param vSrc starting
	 *  location
	 */
	public static void bfs(Graph g, Vertex vSrc){
		g.resetVerts();
		Queue<Vertex> q = new LinkedList<Vertex>();
		vSrc.setCost(0);
		vSrc.mark();
		q.add(vSrc);
		System.out.print("bfs("+vSrc.getLabel()+"): ");
		while(!q.isEmpty()){
			Vertex v = q.poll();
			System.out.print(v.getLabel() + "-");
			TreeMap<String, Edge> neighbors = v.getAdj();
			for(Edge e : neighbors.values()){
				Vertex n = e.getDst();
				if(!n.isMarked()){
					n.mark();
					q.add(n);
				}
			}
		}
		System.out.println("(Done) ");
	}
	/**Depth-first search of graph
	 * @param g Graph to search
	 * @param src label of starting location
	 */
	public static void dfs(Graph g, String src){
		g.resetVerts();
		Vertex v = g.getVertex(src);
		dfs(v);
		System.out.println("(Done) ");
	}
	/**
	 * Helper method to call dfs on each Vertex
	 * @param g is the graph
	 */
	public static void dfs(Vertex v){
		TreeMap<String, Edge> neighbors = v.getAdj();
		v.mark();
		System.out.print(v.getLabel() + "-");
		for(Edge e : neighbors.values()){
			Vertex n = e.getDst();
			if(!n.isMarked()){
				n.mark();
				dfs(n);
			}
		}
	}
	/**
	 * Prints path from src to dst
	 * @param g Graph w/ src and dst
	 * @param src label of source vertex
	 * @param dst label of destination vertex
	 */
	public static void printPath(Graph g, String src, String dst){
		Vertex vDst = g.getVertex(dst);
		Vertex vSrc = g.getVertex(src);
		double cost = vDst.getCost();
		if(vDst != vSrc){
			String str = "";
			while(vDst != null &&  vDst != vSrc){
				str = vDst.getLabel() +"-"+ str;
				vDst = vDst.getPred();
			}
			if(vDst == vSrc){
				System.out.println(vSrc.getLabel() + "-->"+ dst +": ("+cost+") "
						+vSrc.getLabel()+ "-" + str +"(Done) ");
			}
			else{
				System.out.println(vSrc.getLabel() + "-->"+ dst +": No path");
			}
		}
	}

	/**
	 * Djikstra's Shortest Path Algorithm
	 * Calculates the shortest path from source vertex to every other vertex
	 * @param g Graph to find paths of
	 * @param vSrc starting vertex
	 */
	public static void dijkstra(Graph g, Vertex vSrc){
		System.out.println("Djikstra's single-source shortest path from "+vSrc.getLabel());
		g.resetVerts();
		vSrc.setCost(0);
		ArrayList<Vertex> pq = new ArrayList<Vertex>();
		pq.addAll(g.getVerts());
		while(!pq.isEmpty()){
			Vertex v = removeMin(pq);
			v.mark();
			TreeMap<String, Edge> neighbors = v.getAdj();
			for(Edge e : neighbors.values()){	
				Vertex n = e.getDst();
				if(!n.isMarked()){			
					if (n.getCost() > v.getCost() + e.getWeight()){
						n.setCost(v.getCost() + e.getWeight());
						n.setPred(v);
						pq.add(n);
					}
				}
			}
		}
		for(Vertex v : g.getVerts()){
			printPath(g, vSrc.getLabel(), v.getLabel());
		}
	}
	/**
	 * Helper method that find the minimum cost in the priority queue and removes/returns it
	 * @param pq priority queue
	 * @return removed Vertex w/ minimum cost
	 */
	private static Vertex removeMin(ArrayList<Vertex> pq){
		VertexCostComp cmp = new VertexCostComp();
		ArrayList<Vertex> verts =pq;
		Vertex minCost = pq.get(0);
		int mindex = 0;
		for(int i = 0; i < pq.size(); i++){
			Vertex v = pq.get(i);
			if(cmp.compare(v, minCost) < 0){
				minCost = v;
				mindex = i;
			}
		}
		return pq.remove(mindex);
	}

	/**
	 * Performs and prints Prim's M.S.T. algorithm
	 * @param g is Graph to call prim on
	 * @param src is the label of the source vertex
	 */
	public static void prim(Graph g, String src){
		System.out.print("prim("+src+"): ");
		g.resetVerts();
		Vertex vSrc = g.getVertex(src);
		vSrc.setCost(0);
		ArrayList<Vertex> pq = new ArrayList<Vertex>();
		pq.addAll(g.getVerts());
		ArrayList<Edge> mst = new ArrayList<Edge>();
		double cost = 0;
		while(!pq.isEmpty()){
			Vertex v = removeMin(pq);
			v.mark();
			if(v.getPred() != null){
				Edge e = v.getAdj(v.getPred().getLabel());
				mst.add(e);
				cost += e.getWeight();
			}
			TreeMap<String, Edge> neighbors = v.getAdj();
			for(Edge e : neighbors.values()){
				Vertex u = e.getDst();
				if(!u.isMarked() && (u.getCost() > e.getWeight())){
					u.setCost(e.getWeight());
					u.setPred(v);
				}
			}
		}
		System.out.print("("+cost+") " + mst.toString());
	}
	/**
	 * Creates and prints Kruskal's Minimum-Spanning Tree
	 * @param g is the graph
	 */
	public static void kruskal(Graph g){
		System.out.println("kruskal's minimum spanning tree: ");
		System.out.print("kruskal: ");
		ArrayList<Edge> mst = new ArrayList<Edge>();
		DisjointSet<Vertex> dj = new DisjointSet<Vertex>(g.getVerts());
		double cost = 0;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.addAll(g.getEdgesUndirected());
		for(Edge e : edges){
			Vertex u = e.getSrc();
			Vertex v = e.getDst();
			if(dj.findRep(u) != dj.findRep(v)){
				cost += e.getWeight();
				mst.add(e);
				dj.union(u, v);
			}
		}
		System.out.print("("+cost+") "+ mst.toString());
	}
	
	/**
	 * Performs and prints floyd-warshalls all-pairs shortest paths algorithm
	 * @param g is the graph
	 */
	public static void floyd_warshall(Graph g){
		double[][] dMatrix = g.adjMatrix();
		Vertex[][] pMatrix = new Vertex[g.nVerts()][g.nVerts()];

		for(int row  = 0; row < g.nVerts(); row++){
			for(int col = 0; col < g.nVerts(); col++){
				if(dMatrix[row][col] != 0 && dMatrix[row][col] != Double.POSITIVE_INFINITY){
					pMatrix[row][col] = g.getVertex(row);
				}
			}
		}
		for(int k = 0; k < g.nVerts(); k++){
			for(int i = 0; i < g.nVerts(); i++){
				for(int j = 0; j < g.nVerts(); j++){
					if(k != i && k != j && i != j){
						double newDist = dMatrix[i][k] + dMatrix[k][j];
						if(newDist < dMatrix[i][j]){
							dMatrix[i][j] = newDist;
							pMatrix[i][j] = pMatrix[k][j];
						}
					}
				}
			}
		}
		//Print the paths
		System.out.println("floyd_warshall's all-pairs shortest path");
		for(Vertex u : g.getVerts()){
			for(Vertex v : g.getVerts()){
				if(u!=v){
					printFloyd(g, u, v, pMatrix, dMatrix);
				}
			}
			System.out.println();
		}
	}
	/**
	 * Helper method to print floyd-warshall paths
	 * @param g is the graph
	 * @param src is the source vertex
	 * @param dst is the destination vertex
	 * @param pMatrix is the matrix of predecessors
	 * @param dMatrix is the matrix of distances
	 */
	public static void printFloyd(Graph g, Vertex src, Vertex dst, Vertex[][] pMatrix, double[][] dMatrix){
		String str = "";
		double cost = dMatrix[src.getID()][dst.getID()];
		String dLabel = dst.getLabel();
		while(dst != null && dst != src){
			str = "-" + dst.getLabel() + str;
			dst =pMatrix[src.getID()][dst.getID()];
		}
		if(dst != null){
			System.out.println(src.getLabel() + "-->"+ dLabel + ": ("+cost+") "
					+src.getLabel() + str);
		}
		else{
			System.out.println(src.getLabel() + "-->"+ dLabel+ ": "+"No path");
		}
	}
}
