package notes;

public class recursion {

	private static void countUp(int n){
		if (n==1){
			System.out.println(1);
		}
		else {
			countUp(n-1);
			System.out.println(n);
		}
	}
}
