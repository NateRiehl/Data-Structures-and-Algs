import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SymmetricOrder {
	private static PrintStream debug  = System.err;
	private static PrintStream sysout = System.out;	
	public static void symmetric(){
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int num = scanner.nextInt();
			if(num == 0){
				return;
			}
			String[] strings = new String[num];
			int count = 0;
			boolean isOdd = false;
			
			while(count < num/2){
				strings[count] = scanner.next();
				strings[num - count - 1] = scanner.next();
				count++;
	//			debug.println("count: " + count + " " + Arrays.toString(strings));
			}
			if(num % 2 != 0){
				strings[count] = scanner.next();
			}

			for(int j = 0 ; j < strings.length; j ++){
				sysout.println("" + strings[j]);
			}
		}
	}

	public static void main(String[] args){
		symmetric();
	}
}
