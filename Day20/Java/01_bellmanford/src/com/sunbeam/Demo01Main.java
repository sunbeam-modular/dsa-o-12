package com.sunbeam;

import java.util.ArrayList;
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
	
    // Recursive solution
    public int recBellamanFord(int source, int target, int maxEdges) {
        // Base case: 0 edges allowed
        if (maxEdges == 0)
            return (source == target) ? 0 : INF;
        
        // Start with path using k-1 edges
        int minDistance = recBellamanFord(source, target, maxEdges - 1);
        
        // Try all incoming edges to target vertex
        for (Edge edge : edgeList) {
            if (edge.dest == target) {
                int prevDistance = recBellamanFord(source, edge.src, maxEdges - 1);
                if (prevDistance != INF)
                    minDistance = Math.min(minDistance, prevDistance + edge.weight);
            }
        }
        
        return minDistance;
    }
    
    // Wrapper to find shortest paths to all vertices
    public void recBellamanFord(int source) {
    	System.out.println("\nBellaman Ford using Recursion: ");
        int[] dist = new int[vertCount];
        for (int v = 0; v < vertCount; v++) {
            dist[v] = recBellamanFord(source, v, vertCount-1);
            System.out.println("From vertex " + source + " to vertex " + v + " distance = " + dist[v]);
        }
    }
	
    // Memoized solution
    static Map<String, Integer> minDist = new HashMap<>();
    public int memBellamanFord(int source, int target, int maxEdges) {
    	// Create unique key for memoization
        String key = target + "," + maxEdges;
        if(minDist.containsKey(key))
        	return minDist.get(key);
        
        // Base case: 0 edges allowed
        if (maxEdges == 0) {
            int result = (source == target) ? 0 : INF;
            minDist.put(key, result);
            return result;
        }
        
        // Start with path using k-1 edges
        int minDistance = memBellamanFord(source, target, maxEdges - 1);
        
        // Try all incoming edges to target vertex
        for (Edge edge : edgeList) {
            if (edge.dest == target) {
                int prevDistance = memBellamanFord(source, edge.src, maxEdges - 1);
                if (prevDistance != INF)
                    minDistance = Math.min(minDistance, prevDistance + edge.weight);
            }
        }
        
        return minDistance;
    }
    
    // Wrapper to find shortest paths to all vertices
    public void memBellamanFord(int source) {
    	System.out.println("\nBellaman Ford using Memoization: ");
    	minDist.clear();
        int[] dist = new int[vertCount];
        for (int v = 0; v < vertCount; v++) {
            dist[v] = memBellamanFord(source, v, vertCount-1);
            System.out.println("From vertex " + source + " to vertex " + v + " distance = " + dist[v]);
        }
    }

}

public class Demo01Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		Graph g = new Graph(vCount);
		g.accept(sc);
		g.display();
		g.recBellamanFord(0);
		g.memBellamanFord(0);
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
