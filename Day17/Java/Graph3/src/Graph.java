import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
	private int vertexCount, edgeCount;
	private List<Integer> adjList[];
	
	public Graph(int v) {
		vertexCount = v;
		adjList = new List[vertexCount];
		for(int i = 0 ; i < vertexCount ; i++)
			adjList[i] = new ArrayList<Integer>();
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter edge count : ");
		edgeCount = sc.nextInt();
		System.out.println("Enter edges (src, dest) : ");
		for(int i = 0 ; i < edgeCount ; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			adjList[src].add(dest);
			adjList[dest].add(src);	// comment this line for directed graph
		}
	}
	
	public void print() {
		System.out.println("Graph : ");
		System.out.println("Vertex count : " + vertexCount);
		System.out.println("Edge count : " + edgeCount);
		for(int u = 0 ; u < vertexCount ; u++) {
			System.out.print(u + " : ");
			for(Integer v : adjList[u]) {
				System.out.print(" " + v);
			}
			System.out.println("");
		}
	}
}













