import java.util.Comparator;

/**
 * @author Nate Riehl
 *Class implements Comparator<Vertex> to to compare the cost of two vertices
 *CS216, Fall 2015
 */
public class VertexCostComp implements Comparator<Vertex>{
	@Override
	/**
	 *Compares cost of Vertex v1 and Vertex v2
	 *@return 0 if o1 and 02 have same cost. integer < 0 if o1 has smaller cost. Otherwise integer > 0.
	 */
	public int compare(Vertex o1, Vertex o2) {
		Double c1 = new Double(o1.getCost());
		Double c2 = new Double(o2.getCost());
		return c1.compareTo(c2);
	}

}
