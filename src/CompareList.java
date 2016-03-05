import java.util.ArrayList;
import java.util.Random;

public class CompareList {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		//	SList<Integer> sl = new SList<Integer>(); On school computer
		DList<Integer> dl = new DList<Integer>();

		Random r = new Random();

		long start = System.nanoTime();
		int n = 100000;
		for(int i = 0; i < n; i ++){
			al.add(r.nextInt());
		}
		long finish = System.nanoTime();
		System.out.println("" + (finish-start));

		start = System.nanoTime();
		n = 100000;
		for(int i = 0; i < n; i ++){
			dl.add(r.nextInt());
		}
		finish = System.nanoTime();
		System.out.println("" + (finish-start));
	}
}
