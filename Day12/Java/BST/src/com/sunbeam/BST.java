package com.sunbeam;

import java.util.Stack;

public class BST {
	static class  Node{
		private int data;
		private Node left;
		private Node right;
		private boolean visited;
		public Node(int value) {
			data = value;
			left = null;
			right = null;
			visited = false;
		}
	}
	
	private Node root;
	
	public BST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void addNodeRec(int value) {
		if(root == null)
			root = new Node(value);
		else
			addNodeRec(root, value);
	}
	
	private void addNodeRec(Node trav, int value) {
		if(value < trav.data) {
			if(trav.left == null) {
				trav.left = new Node(value);
				return;
			}
			else
				addNodeRec(trav.left, value);
		}
		else {
			if(trav.right == null) {
				trav.right = new Node(value);
				return;
			}
			else
				addNodeRec(trav.right, value);
		}
	}
	
	public void preOrder(Node trav) {
		if(trav == null)
			return;
		System.out.println(" " + trav.data);
		preOrder(trav.left);
		preOrder(trav.right);
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	public void preOrderNonRec() {
		//1. Create a stack to push Nodes
		Stack<Node> st = new Stack<Node>();
		//2. create a trav and start at root
		Node trav = root;
		System.out.print("PreOrder : ");
		//3. repeat whill trav is not null or stack is not empty
		while(trav != null || !st.isEmpty()) {
			//4. visit current node and push right on stack and go into left
			while(trav != null) {
				System.out.print(" " + trav.data);
				if(trav.right != null)
					st.push(trav.right);
				trav = trav.left;
			}//5. repeat till extreme left
			//6. pop next element (right) from stack
			if(!st.isEmpty())
				trav = st.pop();
		}
		System.out.println("");
	}
	
	public void inOrderNonRec() {
		//1. Create a stack to push Nodes
		Stack<Node> st = new Stack<Node>();
		//2. create a trav and start at root
		Node trav = root;
		System.out.print("InOrder : ");
		//3. repeat whill trav is not null or stack is not empty
		while(trav != null || !st.isEmpty()) {
			//4. push current node on stack and go into left
			while(trav != null) {
				st.push(trav);
				trav = trav.left;
			}//5. repeat till extreme left
			//6. pop next element from stack
			if(!st.isEmpty()) {
				trav = st.pop();
				System.out.print(" " + trav.data);
				trav = trav.right;
			}
		}
		System.out.println("");
	}
	
	public void postOrderNonRec() {
		//1. Create a stack to push Nodes
		Stack<Node> st = new Stack<Node>();
		//2. create a trav and start at root
		Node trav = root;
		System.out.print("PostOrder : ");
		//3. repeat whill trav is not null or stack is not empty
		while(trav != null || !st.isEmpty()) {
			while(trav != null) {
				st.push(trav);
				trav = trav.left;
			}

			if(!st.isEmpty()) {
				trav = st.pop();
				if(trav.right == null || trav.right.visited == true) {
					System.out.print(" " + trav.data);
					trav.visited = true;
					trav = null;
				}
				else {
					st.push(trav);
					trav = trav.right;
				}
			}
		}
	}
	public void deleteAll() {
		root = null;
	}
}











