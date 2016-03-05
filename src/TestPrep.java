import java.util.HashMap;
import java.util.Stack;

public class TestPrep {

	public static void printPath(Stack<Integer> s){
		String str = "";
		while(!s.isEmpty()){
			str = s.pop() + " " + str;
		}
		System.out.println(str);
	}

	public static void printPathRec(Stack<Integer> s){
		if(s.isEmpty()){
			System.out.println();
		}
		else{
			Integer pos = s.pop();
			printPathRec(s);
			System.out.println(pos);
		}
	}

	public static int fact(int n){
		if(n > 0){
			return n * fact(n-1);
		}
		return 1;
	}
	
	public static DList<Integer> reverse(DList<Integer> list){
		DList<Integer> reversed = new DList<Integer>();
		for(int i = list.size() - 1; i >= 0; i--){
			reversed.add(reversed.size(), list.get(i));
		}
		return reversed;
	}
	
	public static DList<Integer> intersect(DList<Integer> l1, DList<Integer> l2){
		DList<Integer> inter = new DList<Integer>();
		while(!l1.empty()){
			if(l2.contains(l1.get(0))){
				inter.add(l1.get(0));
			}
			l1.remove(0);
		}
	//	System.out.println(inter.toString());
		return inter;
	}

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);

		printPathRec(s);
		System.out.println(fact(4));

		DList<Integer> l1 = new DList<Integer>();
		DList<Integer> l2 = new DList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		
		l2.add(5);
		l2.add(4);
		l2.add(2);
		l2.add(1);
		l2.add(3);
		l2.add(7);
		
		System.out.println(intersect( l1, l2 ));
	}
}
