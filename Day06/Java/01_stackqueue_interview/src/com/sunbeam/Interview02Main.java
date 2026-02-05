package com.sunbeam;

import java.util.Objects;
import java.util.Stack;

// Rat in a Maze
public class Interview02Main {
	public static class Cell {
		int r, c;
		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public boolean equals(Object obj) {
			Cell other = (Cell) obj;
			return c == other.c && r == other.r;
		}
	}
	
	public static boolean isValid(Cell cell, int[][] maze) {
		if(cell.r < 0 || cell.r >= maze.length)
			return false;
		if(cell.c < 0 || cell.c >= maze.length)
			return false;
		if(maze[cell.r][cell.c] == 1) // obstacle
			return false;
		return true; // valid
	}
	
	public static boolean isMarked(Cell cell, boolean[][] marked) {
		return marked[cell.r][cell.c];
	}
	
	public static void mark(Cell cell, boolean[][] marked) {
		marked[cell.r][cell.c] = true;
	}
	
	public static boolean isReachable(int[][] maze, Cell rat, Cell cheese) {
		// 2-d array for marked
		boolean[][] marked = new boolean[maze.length][maze.length];
		// stack of cells
		Stack<Cell> s = new Stack<>();
		// push rat (src) on stack and mark it
		s.push(rat);
		while(!s.isEmpty()) {
			// pop cell
			Cell cur = s.pop();
			// reached cheese (dest)?
			if(cur.equals(cheese))
				return true;
			// find its neighbors
			Cell[] neighbors = {
				new Cell(cur.r-1, cur.c),
				new Cell(cur.r+1, cur.c),
				new Cell(cur.r, cur.c+1),
				new Cell(cur.r, cur.c-1),
			};
			// push all valid & unmarked neighbors on stack
			for (Cell neighbor : neighbors) {
				if(isValid(neighbor, maze) && !isMarked(neighbor, marked)) {
					s.push(neighbor);
					mark(neighbor, marked); // also mark the neighbor
				}
			}
		} // repeat until stack is empty
		return false; 
	}
	
	public static void main(String[] args) {
		int[][] maze = {
				{0, 1, 0, 1, 1},
				{0, 0, 0, 0, 0},
				{1, 0, 1, 0, 1},
				{0, 0, 1, 0, 0},
				{1, 0, 0, 1, 0}
		};
		Cell rat = new Cell(0, 0);
		Cell cheese = new Cell(4, 4);
		boolean success = isReachable(maze, rat, cheese);
		System.out.println("Rat can get cheese: " + success);
	}
}
