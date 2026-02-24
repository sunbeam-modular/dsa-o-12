package com.sunbeam;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PuneRouteGraph {	
	static class Edge {
		private String type;
		private String id;
		private int src;
		private int dest;
		private String srcStr;
		private String destStr;
		private double weight;
		
		public Edge(String type, String id, int src, int dest, String srcStr, String destStr, double weight) {
			this.type = type;
			this.id = id;
			this.src = src;
			this.dest = dest;
			this.srcStr = srcStr;
			this.destStr = destStr;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return String.format("Edge [type=%s, id=%s, src=%s, dest=%s, srcStr=%s, destStr=%s, weight=%s]", type, id,
					src, dest, srcStr, destStr, weight);
		}
	}
	
	public static final double INF = 99999.0;
	private final HashMap<Integer, String> vm1 = new HashMap<>();
	private final HashMap<String, Integer> vm2 = new HashMap<>();
	private final ArrayList<Edge> edges = new ArrayList<>();
	private final int vertCount;
	private final double[][] mat;
	
	public PuneRouteGraph() {
		loadEdges("PuneRoutes.csv");
		vertCount = vm1.size();
		mat = new double[vertCount][vertCount];
		initGraph();
	}
	
	public void initGraph() {
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = INF;
		}
		for (Edge e : edges)
			mat[e.src][e.dest] = e.weight;
	}
	
	public void loadEdges(String filePath) {
		//System.out.println(System.getProperty("user.dir"));
		File file = new File(filePath);
		try(Scanner sc = new Scanner(file)) {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				try {
					String[] tokens = line.split(",");
					String road = tokens[2].toUpperCase();
					int temp = road.indexOf(" TO ");
					String srcStr = road.substring(0, temp).trim();
					String destStr = road.substring(temp + 4).trim();
					double distance = Double.parseDouble(tokens[3]);
					int newSrcVertex = vm2.size();
					int srcVertex = vm2.getOrDefault(srcStr, -1);
					if (srcVertex == -1) {
						vm1.put(newSrcVertex, srcStr);
						vm2.put(srcStr, newSrcVertex);
						srcVertex = newSrcVertex;
					}
					int newDestVertex = vm2.size();
					int destVertex = vm2.getOrDefault(destStr, -1);
					if (destVertex == -1) {
						vm1.put(newDestVertex, destStr);
						vm2.put(destStr, newDestVertex);
						destVertex = newDestVertex;
					}
					Edge e = new Edge(tokens[0], tokens[1], srcVertex, destVertex, srcStr, destStr, distance);
					edges.add(e);
				} catch (Exception e) {
					System.out.println("Parse error: " + line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getMin(double[] dist, boolean[] spt) {
		double min = INF;
		int minVert = 0;
		for (int i = 0; i < vertCount; i++) {
			if(spt[i] == false && dist[i] < min) {
				min = dist[i];
				minVert = i;
			}
		}
		return minVert;
	}
	
	public int[] dijkstra(int start, double[] dist) {
		boolean[] spt = new boolean[vertCount];
		int sptVertCount = 0;
		// set dist of all vertices to inf and parent to -1
		int[] parent = new int[vertCount];
		for (int i = 0; i < vertCount; i++) {
			dist[i] = INF;
			parent[i] = -1;
		}
		// set dist of start vertex to 0.
		dist[start] = 0;
		// until all vertices are added into spt
		while(sptVertCount < vertCount) {
			// add minimum distance vertex into spt
			int v = getMin(dist, spt);
			spt[v] = true;
			sptVertCount++;
			// update distance and parent of its adjacent vertices
			for (int j = 0; j < vertCount; j++) {
				if(mat[v][j] != INF && spt[j]==false && dist[j] > dist[v]+mat[v][j]) {
					dist[j] = dist[v]+mat[v][j];
					parent[j] = v;
				}
			}
		}
		return parent;
	}
	public int getVertCount() {
		return vertCount;
	}
	public String getVertexName(int id) {
		return vm1.get(id);
	}
	public Integer getVertexId(String name) {
		return vm2.get(name);
	}
	
	public void display() {
		System.out.println("Edges count: " + edges.size());
		System.out.println("Vertex count: " + vm1.size() + " / " + vm2.size());
		vm1.forEach((v,vs) -> System.out.println(v + " -> " + vs));
	}
	
	public void displayPath(int[] parent, int src, int dest) {
		System.out.println("\nRoute from " + src + " to " + dest);
		int v = dest;
		double sum = 0.0;
		do {
			String vStr = getVertexName(v);
			String pStr = getVertexName(parent[v]);
			System.out.println(pStr + " -> " + vStr + " (" + mat[parent[v]][v] + ") ");
			sum += mat[parent[v]][v];
			v = parent[v];
		} while(v != src);
		System.out.println("Total Distance = " + sum);
	}
	
	public static void main(String[] args) {
		PuneRouteGraph g = new PuneRouteGraph();
		g.display();
		int src = g.getVertexId("Katraj".toUpperCase());
		int dest = g.getVertexId("Hinjawadi - Maan Phase 3".toUpperCase());
		double[] dist = new double[g.getVertCount()];
		int[] path = g.dijkstra(src, dist);
		g.displayPath(path, src, dest);
	}
}
