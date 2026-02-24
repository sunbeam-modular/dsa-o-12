package com.sunbeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	
	public List<Integer> topologicalOrder() {
		List<Integer> order = new ArrayList<>();
		// create a queue for vertices
		Queue<Integer> q = new LinkedList<>();
		// create an array to calculate in-degree of each vertex
		int[] indeg = new int[vertCount];
		Arrays.fill(indeg, 0);
		// calculate indeg of each vertex
		for (int s = 0; s < vertCount; s++) {
			for (int d = 0; d < vertCount; d++) {
				if(mat[s][d] != INF)
					indeg[d]++;
			}
		}
		// push all vertices with indeg 0 on the queue
		for (int v = 0; v < vertCount; v++) {
			if(indeg[v] == 0)
				q.offer(v);
		}
		while(!q.isEmpty()) {
			// pop a vertex u from the queue
			int u = q.poll();
			// add the vertex u in topological order
			order.add(u);
			// decrement in-degree all its neighbors
			for (int v = 0; v < vertCount; v++) {
				if(mat[u][v] != INF) {
					indeg[v]--;
					// if in-deg of neighbor is zero, push it in queue
					if(indeg[v] == 0)
						q.offer(v);
				}
			}
		} // repeat until queue is empty
		// check if all vertices added into topological order
		if(order.size() != vertCount)
			return null; // topological order is not possible
		return order;
	}
}

public class TopologicalOrderMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		Graph g = new Graph(vCount);
		g.accept(sc);
		g.display();
		List<Integer> list = g.topologicalOrder();
		System.out.println("Topological Order: " + list);
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
