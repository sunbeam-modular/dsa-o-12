package com.sunbeam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.sunbeam.BinSearchTree.Node;

class BinSearchTree {
	static class Node {
		private int data;
		private Node left;
		private Node right;
		public Node() {
			data = 0;
			left = null;
			right = null;
		}
		public Node(int val) {
			data = val;
			left = null;
			right = null;
		}
		public int getData() {
			return data;
		}
	}
	
	private Node root;
	public BinSearchTree() {
		root = null;
	}
	public void add(int val) {
		// create and initialize node
		Node nn = new Node(val);
		// if tree is empty, new node is the root node
		if(root == null)
			root = nn;
		else {
			Node trav = root;
			while(true) {
				// compare val with the cur node
				if(val < trav.data) {
					// if val is smaller then add node to the left (if left is null) or go to left & re-compare
					if(trav.left == null) {
						trav.left = nn;
						break;
					}
					trav = trav.left;
				}
				else {
					// if val is greater/equal then add node to the right(if right is null) or go to right & re-compare
					if(trav.right == null) {
						trav.right = nn;
						break;
					}
					trav = trav.right;
				}
			}
		}
	}
	
	public void preorder(Node trav) {
		if(trav == null)
			return;
		System.out.print(trav.data + ", ");
		preorder(trav.left);
		preorder(trav.right);
	}
	public void preorder() {
		System.out.print("PRE : ");
		preorder(root);
		System.out.println();
	}
	
	public void inorder(Node trav) {
		if(trav == null)
			return;
		inorder(trav.left);
		System.out.print(trav.data + ", ");
		inorder(trav.right);
	}
	public void inorder() {
		System.out.print(" IN : ");
		inorder(root);
		System.out.println();
	}
	
	public void postorder(Node trav) {
		if(trav == null)
			return;
		postorder(trav.left);
		postorder(trav.right);
		System.out.print(trav.data + ", ");
	}
	public void postorder() {
		System.out.print("POST: ");
		postorder(root);
		System.out.println();
	}
	
	// binary search
	public Node find(Node start, int key) {
		Node trav = start;
		while(trav != null) {
			if(key == trav.data)
				return trav;
			if(key < trav.data)
				trav = trav.left;
			else // key > trav.data
				trav = trav.right;
		}
		return null;
	}
	
	public Integer nearestCommonAncestor(int val1, int val2) {
		Node trav = root;
		while(trav != null) {
			if(val1 < trav.data && val2 < trav.data)
				trav = trav.left;
			else if(val1 > trav.data && val2 > trav.data)
				trav = trav.right;
			else
				break;
		}
		Node temp = find(trav, val1);
		if(temp == null)
			return null; // val1 not found, so no ancestor
		temp = find(trav, val2);
		if(temp == null)
			return null; // val2 not found, so no ancestor
		return trav.data;
	}
	
	public boolean isStrictlyBinTree() {
		// create queue for BFS traversal
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			boolean hasLeftChild = (trav.left != null);
			boolean hasRightChild = (trav.right != null);
			if(hasLeftChild != hasRightChild)
				return false;
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right != null)
				q.offer(trav.right);
		}
		return true;
	}
	
	public int countLeafNodes() {
		int count = 0;
		// create queue for BFS traversal
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			if(trav.left == null && trav.right == null) // leaf node
				count++;
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right != null)
				q.offer(trav.right);
		}
		return count;
	}
	
	public void spiralTraversal() {
		System.out.println("Spiral: ");
		// create two stacks -- s1, s2
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();
		// push root on stack1
		s1.push(root);
		while(!s1.isEmpty() || !s2.isEmpty()) {
			while(!s1.isEmpty()) {
				// pop node from stack1 & print it
				Node temp = s1.pop();
				System.out.print(temp.data + ", ");
				// push its both child on stack2 (left, right)
				if(temp.left != null)
					s2.push(temp.left);
				if(temp.right != null)
					s2.push(temp.right);
			} // repeat for all nodes in stack1
		
			while(!s2.isEmpty()) {
				// pop node from stack2 & print it
				Node temp = s2.pop();
				System.out.print(temp.data + ", ");
				// push its both child on stack1 (right, left)
				if(temp.right != null)
					s1.push(temp.right);
				if(temp.left != null)
					s1.push(temp.left);
			} // repeat for all nodes in stack2		
		} // repeat until both stacks are empty
		System.out.println();
	}
}

public class BinSearchTreeMain {
	public static void main(String[] args) {
		BinSearchTree t = new BinSearchTree();
		t.add(50);
		t.add(30);
		t.add(10);
		t.add(90);
		t.add(100);
		t.add(40);
		t.add(70);
		t.add(20);
		t.add(60);
		t.add(80);
		t.add(5);
		t.preorder();
		t.inorder();
		t.postorder();
		Integer ancestor = t.nearestCommonAncestor(30, 75);
		if(ancestor == null)
			System.out.println("No Ancestor Found");
		else
			System.out.println("Nearest Ancestor: " + ancestor);
		System.out.println("Is Strictly Bin Tree: " + t.isStrictlyBinTree());
		t.spiralTraversal();
	}
}
