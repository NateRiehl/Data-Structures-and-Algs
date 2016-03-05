import java.util.Comparator;

/**
 * @author Nate Riehl
 *EdgeLabelComp class implements Comparator<Edge> to overrides compare method so it compares the cost of two Edges
 *CS216, Fall 2015
 */
public class EdgeCostComp implements Comparator<Edge>{
	/**
	 * Compares cost of Edge o1 and Edge o2
	 * @return 0 if equal costs. integer < 0 if o1 is smaller. Otherwise, integer > 0 
	 */
	@Override 
	public int compare(Edge o1, Edge o2) {
		Double c1 = new Double(o1.getWeight());
		Double c2 = new Double(o2.getWeight());
		return c1.compareTo(c2);
	}
}
