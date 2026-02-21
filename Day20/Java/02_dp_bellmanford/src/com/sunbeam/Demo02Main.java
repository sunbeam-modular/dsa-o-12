package com.sunbeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Graph {
	static class Edge {
		private int src, dest;
		private int weight;
		public Edge() {
		}
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [src=" + src + ", dest=" + dest + ", weight=" + weight + "]";
		}
	}
	
	public static int INF = Integer.MAX_VALUE;
	private int[][] mat;
	private int vertCount, edgeCount;
	private List<Edge> edgeList;
	
	public Graph(int vCount) {
		vertCount = vCount;
		edgeCount = 0;
		mat = new int[vertCount][vertCount];
		for (int s = 0; s < vertCount; s++)
			for (int d = 0; d < vertCount; d++)
				mat[s][d] = INF;
		edgeList = new ArrayList<>();
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
			Edge e = new Edge(s, d, wt);
			edgeList.add(e);
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
	
	// time: O(V*E)
    public void bellamanFord(int source) {
    	// dp state = dist of all vertices from source vertex
    	// initially all vertex dist is infinity except start vertex dist = 0
    	int[] dist = new int[vertCount];
    	Arrays.fill(dist, INF);
    	dist[source] = 0;
    	
    	//time: (V-1) * E
    	for(int i=1; i<vertCount; i++) {
    		// pass - for each edge update the dist of neighbor
    		for (Edge e : edgeList) {
				if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
					dist[e.dest] = dist[e.src] + e.weight;
			}
    	} // repeat v-1 times
    	
    	// pass - to check if graph contains -ve weight cycle
		// time: E
    	for (Edge e : edgeList) {
			if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest]) {
				System.out.println("Graph has -ve weight cycle.");
				return;
			}
		} 
		
		// print the distances
		for(int v=0; v<vertCount; v++)
			System.out.println("Min Dist of vertex " + v + " from vertex " + source + " = " + dist[v]);
    }
}

public class Demo02Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		Graph g = new Graph(vCount);
		g.accept(sc);
		g.display();
		g.bellamanFord(0);
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
