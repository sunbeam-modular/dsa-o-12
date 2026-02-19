import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
	private int vertexCount, edgeCount;
	private boolean adjMat[][];
	
	public Graph(int v) {
		vertexCount = v;
		adjMat = new boolean[vertexCount][vertexCount];
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter edge count : ");
		edgeCount = sc.nextInt();
		System.out.println("Enter edges (src, dest) : ");
		for(int i = 0 ; i < edgeCount ; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			adjMat[src][dest] = true;
			adjMat[dest][src] = true;	// comment this line for directed graph
		}
	}
	
	public void print() {
		System.out.println("Graph : ");
		for(int u = 0 ; u < vertexCount ; u++) {
			for(int v = 0 ; v < vertexCount ; v++) {
				System.out.print(" " + (adjMat[u][v] ? "1" : "0"));
			}
			System.out.println("");
		}
	}
	
							//1. choose start vertex
	public void DFSTraversal (int start) {
		//0. create stack to push vertices
		Stack<Integer> st = new Stack<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		System.out.print("DFS Traversal : ");
		//2. push start vertex on stack and mark it
		st.push(start);
		marked[start] = true;
		while(!st.isEmpty()) {
			//3. pop vertex from stack
			int u = st.pop();
			//4. print vertex
			System.out.print(" " + u);
			//5. push non marked vertices on stack and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					st.push(v);
					marked[v] = true;
				}
			}
		}//6. repeat step 3-5 untill stack is empty
		System.out.println("");
	}
	
	//1. choose start vertex
	public void BFSTraversal (int start) {
		//0. create queue to push vertices
		Queue<Integer> q = new LinkedList<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		System.out.print("BFS Traversal : ");
		//2. push start vertex on queue and mark it
		q.offer(start);			// push
		marked[start] = true;
		while(!q.isEmpty()) {
			//3. pop vertex from queue
			int u = q.poll();			// pop
			//4. print vertex
			System.out.print(" " + u);
			//5. push non marked vertices on queue and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					q.offer(v);
					marked[v] = true;
				}
			}
		}//6. repeat step 3-5 untill queue is empty
		System.out.println("");
	}
	
	//1. choose start vertex
	public boolean isConnected (int start) {
		//0. create stack to push vertices
		Stack<Integer> st = new Stack<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		//2. push start vertex on stack and mark it
		st.push(start);
		marked[start] = true;
		int cnt = 1;
		while(!st.isEmpty()) {
			//3. pop vertex from stack
			int u = st.pop();
			//4. print vertex
			//System.out.print(" " + u);
			//5. push non marked vertices on stack and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					st.push(v);
					marked[v] = true;
					cnt++;
				}
			}
			if(cnt == vertexCount)
				return true;
		}//6. repeat step 3-5 untill stack is empty
		return false;
	}
	
	public boolean isBipartite (int start) {
		//0. create queue to push vertices
		Queue<Integer> q = new LinkedList<Integer>();
		//0. create an array to colour vertices
		int colour[] = new int[vertexCount];
		//2. push start vertex on queue and mark it
		q.offer(start);			// push
		colour[start] = 1;
		while(!q.isEmpty()) {
			//3. pop vertex from queue
			int u = q.poll();			// pop
			//5. push non marked vertices on queue and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(adjMat[u][v]) {
					// if colour of both is same then return false
					if(colour[u] == colour[v])
						return false;
					if(colour[v] == 0) {
						q.offer(v);
						colour[v] = colour[u] * -1;
					}
				}
			}
		}//6. repeat step 3-5 untill queue is empty
		return true;
	}
	
	public void singleSourcePathLength (int start) {
		//0. create queue to push vertices
		Queue<Integer> q = new LinkedList<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		//0. create length array
		int length[] = new int[vertexCount];
		//2. push start vertex on queue and mark it
		q.offer(start);			// push
		marked[start] = true;
		length[start]  =0;
		while(!q.isEmpty()) {
			//3. pop vertex from queue
			int u = q.poll();			// pop
			//5. push non marked vertices on queue and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					q.offer(v);
					marked[v] = true;
					length[v] = length[u] + 1;
				}
			}
		}//6. repeat step 3-5 untill queue is empty
		System.out.println("Path length from vertex " + start);
		for(int i = 0 ; i < vertexCount ; i++)
			System.out.println(i + " : " + length[i]);
	}
	
	public void DFSSpanningTree (int start) {
		//0. create stack to push vertices
		Stack<Integer> st = new Stack<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		System.out.print("DFS spanning Tree : ");
		//2. push start vertex on stack and mark it
		st.push(start);
		marked[start] = true;
		while(!st.isEmpty()) {
			//3. pop vertex from stack
			int u = st.pop();
			//4. print vertex
			//System.out.print(" " + u);
			//5. push non marked vertices on stack and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					st.push(v);
					marked[v] = true;
					System.out.print("(" + u + "," + v + ")");
				}
			}
		}//6. repeat step 3-5 untill stack is empty
		System.out.println("");
	}
	
	//1. choose start vertex
	public void BFSpanningTree (int start) {
		//0. create queue to push vertices
		Queue<Integer> q = new LinkedList<Integer>();
		//0. create an array to mark vertices
		boolean marked[] = new boolean[vertexCount];
		System.out.print("BFS Spanning Tree : ");
		//2. push start vertex on queue and mark it
		q.offer(start);			// push
		marked[start] = true;
		while(!q.isEmpty()) {
			//3. pop vertex from queue
			int u = q.poll();			// pop
			//4. print vertex
			//System.out.print(" " + u);
			//5. push non marked vertices on queue and mark them
			for(int v = 0 ; v < vertexCount ; v++) {
				if(!marked[v] && adjMat[u][v]) {
					q.offer(v);
					marked[v] = true;
					System.out.print("(" + u + "," + v + ")");
				}
			}
		}//6. repeat step 3-5 untill queue is empty
		System.out.println("");
	}
}













