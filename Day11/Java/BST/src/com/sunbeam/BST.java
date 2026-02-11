package com.sunbeam;

public class BST {
	static class  Node{
		private int data;
		private Node left;
		private Node right;
		public Node(int value) {
			data = value;
			left = null;
			right = null;
		}
	}
	
	private Node root;
	
	public BST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void addNode(int value) {
		//1. create a newnode
		Node newnode = new Node(value);
		//2. if BST is empty, then add newnode into root itself
		if(root == null)
			root = newnode;
		//3. if BST is not empty
		else {
			//3.1 create trav reference and start at root
			Node trav = root;
			while(true) {
				//3.2 if value is less than current node data
				if(value < trav.data) {
					//3.2.1 if left of current node is empty, add newnode into left
					if(trav.left == null) {
						trav.left = newnode;
						break;
					}
					//3.2.2 if left of current node is not empty, then traverse into left
					else
						trav = trav.left;
				}
				//3.3 if value is greater or equal than current node data
				else {
					//3.2.1 if right of current node is empty, add newnode into right
					if(trav .right == null) {
						trav.right = newnode;
						break;
					}
					//3.2.2 if right of current node is not empty, then traverse into right
					else
						trav = trav.right;
				}
			}//3.4 repeat step 3.2 and 3.3 untill node is added into BST
		}
	}
	
	private void preOrder(Node trav) {	// original method
		if(trav == null)
			return;
		System.out.print(" " + trav.data);	// visit
		preOrder(trav.left);	// left
		preOrder(trav.right);	// right
	}
	
	public void preOder() {			// wrapper method
		System.out.print("PreOder : ");
		preOrder(root);
		System.out.println("");
	}
	
	private void inOrder(Node trav) {
		if(trav == null)
			return;
		inOrder(trav.left);	// left
		System.out.print(" " + trav.data);	// visit
		inOrder(trav.right);	// right
	}
	
	public void inOrder() {			// wrapper method
		System.out.print("inOrder : ");
		inOrder(root);
		System.out.println("");
	}
	
	private void postOrder(Node trav) {
		if(trav == null)
			return;
		postOrder(trav.left);	// left
		postOrder(trav.right);	// right
		System.out.print(" " + trav.data);	// visit
	}
	
	public void postOrder() {			// wrapper method
		System.out.print("postOrder : ");
		postOrder(root);
		System.out.println("");
	}
	
	public void deleteAll() {
		root = null;
	}
}











