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
	
	public Node binarySearch(int key) {
		//1. create trav and start at root
		Node trav = root;
		while(trav != null) {
			//2. if key is matching with current node data return its refernce
			if(key == trav.data)
				return trav;
			//3. if key is less than current node data, search into left sub tree
			else if(key < trav.data)
				trav = trav.left;
			//4. if key is greater than current node data, search into right sub tree
			else
				trav = trav.right;
		}//5. repeat above 3 steps till leaf nodes
		//6. if key is not found
		return null;
	}
	
	public void deleteNode(int key) {
		//1. search key along with its parent
		Node trav = root, parent = null;
		while(trav != null) {
			if(key == trav.data)
				break;
			parent = trav;
			if(key < trav.data)
				trav = trav.left;
			else
				trav = trav.right;
		}
		//2. if node is not found then return
		if(trav == null)
			return;
		//3. if node is found then delete it
		//3.1 node having two childs
		if(trav.left != null && trav.right != null) {
			//3.1.1 find inoder predecessor
			Node pred = trav.left;
			parent = trav;
			while(pred.right != null) {
				parent = pred;
				pred = pred.right;
			}
			//3.1.2 replace value of node by value of predecessor
			trav.data = pred.data;
			//3.1.3 mark predecessor for deletion
			trav = pred;
		}
		//3.2 node having single child(right)
		if(trav.left == null) {
			if(trav == root)
				root = trav.right;
			else if(trav == parent.left)
				parent.left = trav.right;
			else if(trav == parent.right)
				parent.right = trav.right;
		}
		//3.3 node having single child(left)	
		else {		//if(trav.right == null) {
			if(trav == root)
				root = trav.left;
			else if(trav == parent.left)
				parent.left = trav.left;
			else if(trav == parent.right)
				parent.right = trav.left;
		}
	}
	
	public void DFSTraversal() {
		//0. create stack to push nodes
		Node st[] = new Node[10];
		int top = -1;
		System.out.print("DFS Traversal : ");
		//1. push root on stack
		st[++top] = root;
		while(top != -1) {
			//2. pop node from stack
			Node curr = st[top--];
			//3. visit node
			System.out.print(" " + curr.data);
			//4. if right exist, push it on stack
			if(curr.right != null)
				st[++top] = curr.right;
			//5. if left exist, push it on stack
			if(curr.left != null)
				st[++top] = curr.left;
		}
		//6. repeat above steps till stack is not empty
		System.out.println("");
	}
	
	public void BFSTraversal() {
		//0. create queue to push nodes
		Node q[] = new Node[10];
		int front = -1, rear = -1;
		System.out.print("BFS Traversal : ");
		//1. push root node on queue
		q[++rear] = root;
		while(front != rear) {
			//2. pop node from queue
			Node curr = q[++front];
			//3. print current node
			System.out.print(" " + curr.data);
			//4. if left exist, then push it on queue
			if(curr.left != null)
				q[++rear] = curr.left;
			//5. if right exist, then push it on queue
			if(curr.right != null)
				q[++rear] = curr.right;
		}
		//6. repeat while queue is not empty
		System.out.println("");
	}
	
	private int height(Node trav) {
		//1. if tree is empty return -1
		if(trav == null)
			return -1;
		//2. find height of left subtree
		int hl = height(trav.left);
		//3. find height of right sub tree
		int hr = height(trav.right);
		//4. find maximum height
		int max = hl > hr ? hl : hr;
		//5. return max height + 1
		return max + 1;
	}
	
	public int height() {
		return height(root);
	}
	
	
	public void inOrder(Node trav) {
		if(trav == null)
			return;
		inOrder(trav.left);
		System.out.print(" " + trav.data);
		inOrder(trav.right);
	}
	
	public void inOrder() {
		System.out.print("Inorder : ");
		inOrder(root);
		System.out.println("");
	}
	
	
	public void deleteAll() {
		root = null;
	}
}











