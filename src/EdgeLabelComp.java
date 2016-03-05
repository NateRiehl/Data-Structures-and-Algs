import java.util.Comparator;
/**
 * @author Nate Riehl
 * EdgeLabelComp class implements Comparator<Edge> and overrides compare(Edge e1, Edge e2) method
 * Compares the destination vertex labels of e1 and e2.
 */
public class EdgeLabelComp implements Comparator<Edge>{
	@Override
	/**
	 * Compares two edges and checks which has alphabetically smaller vdst
	 */
	public int compare(Edge o1, Edge o2) {
		String label1 = o1.getDst().getLabel();
		String label2 = o2.getDst().getLabel();
		return label1.compareTo(label2);
	}
}
