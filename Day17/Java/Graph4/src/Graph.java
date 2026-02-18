import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
	static class Adjacent{
		private int v;
		private int w;
		public Adjacent(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	private int vertexCount, edgeCount;
	private List<Adjacent> adjList[];
	
	public Graph(int v) {
		vertexCount = v;
		adjList = new List[vertexCount];
		for(int i = 0 ; i < vertexCount ; i++)
			adjList[i] = new ArrayList<Adjacent>();
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter edge count : ");
		edgeCount = sc.nextInt();
		System.out.println("Enter edges (src, dest, wt) : ");
		for(int i = 0 ; i < edgeCount ; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			int wt = sc.nextInt();
			adjList[src].add(new Adjacent(dest, wt));
			adjList[dest].add(new Adjacent(src, wt));	// comment this line for directed graph
		}
	}
	
	public void print() {
		System.out.println("Graph : ");
		System.out.println("Vertex count : " + vertexCount);
		System.out.println("Edge count : " + edgeCount);
		for(int u = 0 ; u < vertexCount ; u++) {
			System.out.print(u + " : ");
			for(Adjacent v : adjList[u]) {
				System.out.print(" " + v.v + "(" + v.w + ")");
			}
			System.out.println("");
		}
	}
}













