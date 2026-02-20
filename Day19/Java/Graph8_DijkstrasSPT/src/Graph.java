import java.util.Scanner;

public class Graph {
	private final int INF = 999;
	private int vertexCount, edgeCount;
	private int adjMat[][];
	
	public Graph(int vCount) {
		vertexCount = vCount;
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
	
	private int findMinDistVertex(int dist[], boolean spt[]) {
		int minDist = INF, minDistVertex = -1;
		for(int i = 0 ; i < vertexCount ; i++) {
			if(!spt[i] && dist[i] < minDist) {
				minDist = dist[i];
				minDistVertex = i;
			}
		}
		return minDistVertex;
	}
	
	public void dijkstrasSPT(int start) {
		//.0 create array to keep track of SPT
		boolean spt[] = new boolean[vertexCount];
		//0. create array to keep track of parents
		int parent[] = new int[vertexCount];
		//0. create array to maintain dist of vertices
		int dist[] = new int[vertexCount];
		//0. also maintain count of vertices in spt
		int count = 0;
		//0. mark parent as -1 and dist as INF for all the vertices
		for(int i = 0 ; i < vertexCount ; i++) {
			parent[i] = -1;
			dist[i] = INF;
			spt[i] = false;
		}
		//0. make dist of start vertex to 0
		dist[start] = 0;
		// while all vertices are not added into SPT
		while(count < vertexCount) {						// v times
			//1. find minimum dist vertex which not added into SPT
			int u = findMinDistVertex(dist, spt);				// v times
			//2. add min dist vertex into spt
			spt[u] = true;
			//increment spt vertex count
			count++;
			//3. update dist and parent of adjacent which are not added into spt
			for(int v = 0 ; v < vertexCount ; v++) {			// v times
				if(!spt[v] && adjMat[u][v] != INF && dist[u] + adjMat[u][v] < dist[v]) {
					dist[v] = dist[u] + adjMat[u][v];
					parent[v] = u;
				}
			}
		}
		
		System.out.println("Shorted distances from " + start);
		for(int i = 0 ; i < vertexCount ; i++)
			System.out.println(i + " = " + dist[i]);
			
	}
}













