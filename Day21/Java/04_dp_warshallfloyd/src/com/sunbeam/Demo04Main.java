package com.sunbeam;

import java.util.Scanner;

class Graph {
	public static int INF = 999;
	private int[][] mat;
	private int vertCount, edgeCount;
	public Graph(int vCount) {
		vertCount = vCount;
		edgeCount = 0;
		mat = new int[vertCount][vertCount];
		for (int s = 0; s < vertCount; s++)
			for (int d = 0; d < vertCount; d++)
				mat[s][d] = INF;
	}
	public void accept(Scanner sc) {
		System.out.print("Enter number of edges: ");
		edgeCount = sc.nextInt();
		for(int i=1; i<=edgeCount; i++) {
			System.out.print("Enter edge " + i + " (src dest weight): ");
			int s = sc.nextInt();
			int d = sc.nextInt();
			int wt = sc.nextInt();
			mat[s][d] = wt;
		}
	}
	public void display() {
		System.out.println("Graph: ");
		for(int s=0; s<vertCount; s++) {
			for(int d=0; d<vertCount; d++) {
				System.out.print((mat[s][d]==INF?"##":mat[s][d]) + "\t");
			}
			System.out.println();
		}
	}
	
	// time: O(V * V * V)
	//	better than bellman ford algo repeating for each vertex: O(V * V * E)
	//	not better than dijkstra algo repeating for each vertex: O(V * V * log V)
    public void warshallFloyd() {
    	// dp state init -- min dist between all vertices
    	// init state = adj matrix i.e. all edges weight
    	int[][] dist = new int[vertCount][vertCount];
    	for(int i=0; i<vertCount; i++) {
    		for(int j=0; j<vertCount; j++)
    			dist[i][j] = mat[i][j];
    		// dist of each vertex to itself
    		dist[i][i] = 0;
    	}
    	
    	// consider k as intermediate vertex between all pairs (i,j) of vertices
    	// and update min distance between all pairs (i,j)
    	for(int k=0; k<vertCount; k++) {
	    	for(int i=0; i<vertCount; i++) {
	    		for(int j=0; j<vertCount; j++) {
	    			if(dist[i][k] != INF &&  dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j])
	    				dist[i][j] = dist[i][k] + dist[k][j];
	    		}
	    	}
    	}
    	
    	// print shortest dist between vertices pair
    	for(int i=0; i<vertCount; i++) {
    		System.out.print("from vertex " + i + " : ");
    		for(int j=0; j<vertCount; j++)
    			System.out.print((dist[i][j]==INF?"##":dist[i][j]) + "\t");
    		System.out.println();
    	}
    }    
}

public class Demo04Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		Graph g = new Graph(vCount);
		g.accept(sc);
		g.display();
		g.warshallFloyd();
	}
}

/*
5
7
0	1	6
0	2	5
1	3	-1
2	1	-2
2	3	4
2	4	3
3	4	3
*/
