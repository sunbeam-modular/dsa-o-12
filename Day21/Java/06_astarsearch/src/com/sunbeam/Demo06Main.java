package com.sunbeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import com.sunbeam.Graph.Cell;

class Graph {
	static class Cell {
		private int row, col;
		private double f, g, h;
		private Cell parent;
		private boolean onPath;
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public Cell(int row, int col, double f, double g, double h) {
			this.row = row;
			this.col = col;
			this.f = f;
			this.g = g;
			this.h = h;
		}
		@Override
		public int hashCode() {
			return Objects.hash(col, row);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Cell))
				return false;
			Cell other = (Cell) obj;
			return col == other.col && row == other.row;
		}
		@Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + "]";
		}
	}

	public static final int INF = 999;
	private int[][] mat;
	private int vertCount;

	public Graph(int[][] mat) {
		vertCount = mat.length;
		this.mat = new int[vertCount][vertCount];
		for (int r = 0; r < vertCount; r++) {
			for (int c = 0; c < vertCount; c++)
				this.mat[r][c] = mat[r][c];
		}
	}
	
	public boolean isValid(Cell c) {
		return (c.row >= 0 && c.row < vertCount) && (c.col >= 0 && c.col < vertCount);
	}
	
	public boolean isBlocked(Cell c) {
		return mat[c.row][c.col] == 0;
	}
	
	public List<Cell> buildPath(Cell c) {
		List<Cell> path = new ArrayList<>();
		while(c != null) {
			path.add(c);
			c = c.parent;
		}
		return path;
	}
	
	public List<Cell> aStarSearch(final int r1, final int c1, final int r2, final int c2) {
		// create a matrix for all cells
		Cell[][] cells = new Cell[vertCount][vertCount];
		for (int r = 0; r < vertCount; r++) {
			for (int c = 0; c < vertCount; c++)
				cells[r][c] = new Cell(r, c, INF, INF, INF);
		}
		// create priority queue to add cells -- based on their f(v)
		PriorityQueue<Cell> q = new PriorityQueue<>((x,y) -> (int)Math.signum(x.f - y.f));
		// push source cell on queue & mark it
		Cell src = cells[r1][c1];
		src.f = src.g = src.h = 0;
		q.offer(src);
		while(!q.isEmpty()) {
			// pop a cell from queue
			Cell v = q.poll();
			v.onPath = true;
			
			// if dest found, build the path and return it.
			if(v.row == r2 && v.col == c2)  
				return buildPath(v);
			
			// find all its neighbors
			List<Cell> neighbors = new ArrayList<>();
			if(v.row > 0)
				neighbors.add(cells[v.row-1][v.col]); // UP
			if(v.row < vertCount-1)
				neighbors.add(cells[v.row+1][v.col]); // DOWN
			if(v.col < vertCount-1)
				neighbors.add(cells[v.row][v.col+1]); // RIGHT
			if(v.col > 0)
				neighbors.add(cells[v.row][v.col-1]); // LEFT
			if(v.row > 0 && v.col > 0)				
				neighbors.add(cells[v.row-1][v.col-1]); // UP-LEFT
			if(v.row > 0 && v.col < vertCount-1)
				neighbors.add(cells[v.row-1][v.col+1]); // UP-RIGHT
			if(v.row < vertCount-1 && v.col > 0)				
				neighbors.add(cells[v.row+1][v.col-1]); // DOWN-LEFT
			if(v.row < vertCount-1 && v.col < vertCount-1)								
				neighbors.add(cells[v.row+1][v.col+1]); // DOWN-RIGHT

			// for each valid neighbor
			for(Cell u: neighbors) {
				if(u.row == r2 && u.col == c2) { // if dest found, build the path and return it. 
					u.onPath = true;
					u.parent = v;
					return buildPath(u);
				}
				if(!u.onPath && isValid(u) && !isBlocked(u)) {
					// calculate f(v) for the neighbor
					double newg = v.g + 1;
					double newh = calcHeuristic(u.row, u.col, r2, c2);
					double newf = newg + newh;
					if(newf < u.f) {
						u.g = newg;
						u.h = newh;
						u.f = newf;
						// if already on queue -- rearrange as per new f(v)
						if(q.contains(u))
							q.remove(u);
						// otherwise push on queue and mark it
						q.offer(u);
						u.parent = v;
					}
				}
			}
		} // repeat until queue is empty
		return null;
	}

	public double calcHeuristic(int y1, int x1, int y2, int x2) {
		//return Math.abs(y2-y1) + Math.abs(x2-x1); // manhattan distance
		//return Double.max(Math.abs(y2-y1), Math.abs(x2-x1)) // diagonal distance
		return Math.sqrt((y2-y1)*(y2-y1) + (x2-x1)*(x2-x1)); // euclidean distance
	}
	
	public void printPath(List<Cell> path) {
		System.out.println("\nPath: ");
		for (int r = 0; r < vertCount; r++) {
			for (int c = 0; c < vertCount; c++) {
				Cell cell = new Cell(r, c);
				System.out.printf("%3s", path.contains(cell) ? "*" : mat[r][c]);
			}
			System.out.println();
		}		
	}
}
public class Demo06Main {
	public static void main(String[] args) {
		int [][] grid = new int[][] { 
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
			{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, 
			{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
			{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, 
			{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
			{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
			{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 }
		};
		Graph g = new Graph(grid);
		List<Cell> path = g.aStarSearch(0, 0, grid.length-1, grid.length-1);
		if(path != null) {
			System.out.println("Path: ");
			path.forEach(c -> System.out.println("  " + c));
			g.printPath(path);
		}
		else
			System.out.println("Path Not Found.");
	}
}
