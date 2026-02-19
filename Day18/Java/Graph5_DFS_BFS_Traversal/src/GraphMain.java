import java.util.Scanner;

public class GraphMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter vertex count : ");
		int vCount = sc.nextInt();
		
		Graph g = new Graph(vCount);
		
		g.accept(sc);
		//g.print();
		//g.DFSTraversal(1);
		//g.BFSTraversal(1);
//		if(g.isConnected(0))
//			System.out.println("Graph is connected");
//		else
//			System.out.println("Graph is not connected");
//		if(g.isBipartite(0))
//			System.out.println("Graph is Bipartite");
//		else
//			System.out.println("Graph is not Bipartite");
//		
		
//		g.singleSourcePathLength(2);

		g.DFSSpanningTree(5);
		g.BFSpanningTree(5);
		
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


6
6
0 1
0 2
0 3
1 2
1 4
3 4


6
6
0 3
0 4
1 5
2 3
2 4
2 5



*/

















