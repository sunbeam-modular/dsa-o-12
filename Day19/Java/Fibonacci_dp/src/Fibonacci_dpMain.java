
public class Fibonacci_dpMain {
	static int count;

	public static int recFib(int n) {
		count++;
		if(n == 1 || n == 2) 
			return 1;
		int nthTerm = recFib(n-1) + recFib(n-2);
		return nthTerm;
	}
	
	public static int memFib(int n, int terms[]) {
		count++;
		if(terms[n] != 0)
			return terms[n];
		
		if(n == 1 || n == 2) {
			terms[n] = 1;
			return 1;
		}
		terms[n] = memFib(n-1, terms) + memFib(n-2, terms);
		return terms[n];
	}
	
	public static int memFib(int n) {
		int terms[]  =new int[n + 1];
		return memFib(n, terms);
	}
	
	public static int dpFib(int n) {
		int terms[] = new int[n + 1];
		terms[1] = terms[2] = 1;
		for(int i = 3 ; i <= n ; i++)
			terms[i] = terms[i-1] + terms[i-2];
		return terms[n];
	}
	
	public static void main(String[] args) {
		int term, n = 30;
		
		count = 0;
		term = recFib(n);
		System.out.println(n + "th term of fibonacci series = " + term + " with " + count + " recursive calls");

		
		count = 0;
		term = memFib(n);
		System.out.println(n + "th term of fibonacci series = " + term + " with " + count + " recursive calls");

		count = 1;
		term = dpFib(n);
		System.out.println(n + "th term of fibonacci series = " + term + " with " + count + " calls");

	}

}
