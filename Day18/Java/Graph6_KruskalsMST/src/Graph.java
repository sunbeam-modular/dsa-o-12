import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Graph {
	static class Edge{
		private int src, dest;
		private int wt;
		public Edge(int s, int d, int w) {
			src = s;
			dest = d;
			wt = w;
		}
	}
	private final int INF = 999;
	private int vertexCount, edgeCount;
	private int adjMat[][];
	private List<Edge> edges;
	
	public Graph(int vCount) {
		vertexCount = vCount;
		edges = new ArrayList<Graph.Edge>();
		adjMat = new int[vertexCount][vertexCount];
		for(int u = 0 ; u < vertexCount ; u++)
			for(int v = 0 ; v < vertexCount ; v++)
				adjMat[u][v] = INF;
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter edge count : ");
		edgeCount = sc.nextInt();
		System.out.println("Enter edges (src, dest, wt) : ");
		for(int i = 0 ; i < edgeCount ; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			int wt = sc.nextInt();
			adjMat[src][dest] = wt;
			adjMat[dest][src] = wt;	// comment this line for directed graph
			edges.add(new Edge(src, dest, wt));
		}
	}
	
	public void print() {
		System.out.println("Graph : ");
		for(int u = 0 ; u < vertexCount ; u++) {
			for(int v = 0 ; v < vertexCount ; v++) {
				System.out.print("\t" + (adjMat[u][v] == INF ? "INF" : adjMat[u][v]));
			}
			System.out.println("");
		}
	}
	
	public int find(int parent[], int v) {
		while(parent[v] != -1)
			v = parent[v];
		return v;
	}
	
	public void union(int parent[], int srcRoot, int destRoot) {
		parent[srcRoot] = destRoot;
	}
	// union find algo
	public boolean hasCycle(List<Edge> edges) {
		int parent[] = new int[vertexCount];
		Arrays.fill(parent, -1);
		for(Edge e : edges) {
			int srcRoot = find(parent, e.src);
			int destRoot = find(parent, e.dest);
			if(srcRoot == destRoot)
				return true;		// cycle is detected
			union(parent, srcRoot, destRoot);
		}
		return false;	// cycle is not deleted
	}
	
	public void kruskalsMST() {
		//sort edges in ascending order of wt
		edges.sort((e1, e2) -> e1.wt - e2.wt);
		// create list to store mst
		List<Edge> mst = new ArrayList<Graph.Edge>();
		// repeat for every edge of graph
		for(Edge e : edges) {
			// add edge into mst
			mst.add(e);
			// if edge is forming cycle, discard it
			if(hasCycle(mst) == true)
				mst.remove(mst.size()-1);
			// if V-1 edges then stop
			if(mst.size() == (vertexCount-1))
				break;
		}
		
		// print MST and find its weight
		int wt = 0;
		System.out.println("MST : ");
		for(Edge e : mst) {
			System.out.println("(" + e.src + "," + e.dest + ")");
			wt += e.wt;
		}
		System.out.println("Weight = " + wt);
	}
}













