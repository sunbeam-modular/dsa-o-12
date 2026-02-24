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
	
    // Recursive solution
    public int recWarshallFloyd(int i, int j, int k) {
        // Base case: no intermediate vertices allowed
        if (k == 0) {
            return mat[i][j]; // Direct edge or infinity
        }
        
        // Option 1: Don't use vertex k-1 as intermediate
        int withoutK = recWarshallFloyd(i, j, k - 1);
        
        // Option 2: Use vertex k-1 as intermediate  
        int pathItoK = recWarshallFloyd(i, k - 1, k - 1);
        int pathKtoJ = recWarshallFloyd(k - 1, j, k - 1);
        int withK = INF;
        if (pathItoK != INF && pathKtoJ != INF)
            withK = pathItoK + pathKtoJ;
        
        // Return minimum of both options
        return Math.min(withoutK, withK);
    }
    
    // Wrapper to find shortest paths between all pairs
    public void recWarshallFloyd() {
        int[][] dist = new int[vertCount][vertCount];
        
        for (int i = 0; i < vertCount; i++) {
            for (int j = 0; j < vertCount; j++)
                dist[i][j] = recWarshallFloyd(i, j, vertCount);
        	dist[i][i] = 0; // dist of any vertex to itself is 0.            
        }
        
		// display dist of every vertex from each vertex
		for (int s = 0; s < dist.length; s++) {
			System.out.print("Dist from vertex " + s + " to other vertices: ");
			for (int d = 0; d < dist.length; d++)
				System.out.printf("%5s", (dist[s][d]!=INF?dist[s][d]:"##"));
			System.out.println();
		}        
    }
}

public class Demo03Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		Graph g = new Graph(vCount);
		g.accept(sc);
		g.display();
		g.recWarshallFloyd();
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
