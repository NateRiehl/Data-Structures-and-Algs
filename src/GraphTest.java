import java.io.File;
import java.util.Scanner;
/**
 *Class used to test Graph and GraphUtil
 *@author Nate Riehl
 *CS216, Fall 2015
 */
public class GraphTest {

	/**
	 * Calls dfs method on each vertex.
	 * @param g is the graph to call dfs on
	 */
	public static void dfs(Graph g) {
		for (Vertex v : g.getVerts()) {
			String src = v.getLabel();
			System.out.printf("dfs(%s): ",src);
			GraphUtil.dfs(g, src);
		}
		System.out.println();
	}
	/**
	 * Calls bfs method on each vertex.
	 * @param g is the graph to call bfs on
	 */
	public static void bfs(Graph g) {
		for (Vertex v : g.getVerts()) {
			GraphUtil.bfs(g, v);
		}
		System.out.println();
	}
	/**
	 * Calls dijkstra method on each vertex.
	 * @param g is the graph to call dijkstra on
	 */
	public static void dijkstra(Graph g) {
		for (Vertex v : g.getVerts()) {
			GraphUtil.dijkstra(g, v);
			System.out.println();
		}
	}
	/**
	 * Calls prim method on each vertex.
	 * @param g is the graph to call prim on
	 */
	public static void prim(Graph g) {
		System.out.println("Prim's minimum spanning tree:");
		for (Vertex v : g.getVerts()) {
			GraphUtil.prim(g, v.getLabel());
			System.out.println();
		}
		System.out.println();
	}
	
	public static void kruskal(Graph g){
		GraphUtil.kruskal(g);
		System.out.println();
	}
	/**
	 * Calls floyd-warshall method on each vertex.
	 * @param g is the graph to call F-W on
	 */
	public static void floydWarshall(Graph g) {
			GraphUtil.floyd_warshall(g);
	}
	public static void main(String[] args){
		if(args.length == 2){
			String labels = "graphs/g"+args[0]+"_labels";
			String matrix = "graphs/g"+args[1]+"_w";

			Graph graph = new Graph(labels, matrix);

			System.out.println("Graph: \n"+graph.toString());
			dfs(graph);
			bfs(graph);
			dijkstra(graph);
			floydWarshall(graph);
			
			graph.toUndirected();//Turns graph into undirected
			
			prim(graph);
			kruskal(graph);
		}
		else{
			System.out.println("Try again");
		}
	}
}
