import java.util.Scanner;

public class GraphMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter vertex count : ");
		int vCount = sc.nextInt();
		
		Graph g = new Graph(vCount);
		
		g.accept(sc);
		//g.print();
		g.DFSTraversal(1);
		g.BFSTraversal(1);
		
		
		sc.close();

	}
}

/*
6
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5



*/

















