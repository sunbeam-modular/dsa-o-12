package sunbeam;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

class BinSearchTree {
	static class Node {
		int data;
		Node left, right;
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
		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}
	}

	private Node root;
	public BinSearchTree() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public void add(int val) {
		Node newNode = new Node(val);
		if(isEmpty())
			root = newNode;
		else {
			Node trav = root;
			while(true) {	
				if(val < trav.data) { // if less, goto left
					if(trav.left == null) {
						trav.left = newNode;
						break;
					}
					trav = trav.left;
				}
				else { // if greater or equal, goto right
					if(trav.right == null) {
						trav.right = newNode;
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
		System.out.print("IN  : ");
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

	public void bfs() {
		System.out.print("BFS: ");
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			System.out.print(trav.data + ", ");
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right!= null)
				q.offer(trav.right);
		}
		System.out.println();
	}

	public void dfs() {
		System.out.print("DFS: ");
		Stack<Node> s = new Stack<>();
		s.push(root);
		while(!s.isEmpty()) {
			Node trav = s.pop();
			System.out.print(trav.data + ", ");
			if(trav.right!= null)
				s.push(trav.right);
			if(trav.left != null)
				s.push(trav.left);
		}
		System.out.println();
	}
	
	public boolean isStrict() {
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			if( (trav.left == null) != (trav.right == null) )
				return false;
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right!= null)
				q.offer(trav.right);
		}
		return true;
	}
	
	public boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}
	
	public boolean isComplete() {
		int leafLevel = 0;
		Queue<SimpleEntry<Node, Integer>> q = new LinkedList<>();
		q.offer(new SimpleEntry<>(root, 1));
		while(!q.isEmpty()) {
			SimpleEntry<Node, Integer> ent = q.poll();
			Node trav = ent.getKey();
			int level = ent.getValue();
			if(isLeaf(trav)) {
				if(leafLevel == 0) // when first leaf found, record its level.
					leafLevel = level;
				else // when next leaf is found
					if(level != leafLevel) // if new level is not same as last leaf level
						return false;
			}
			if(trav.left != null)
				q.offer(new SimpleEntry<>(trav.left, level+1));
			if(trav.right!= null)
				q.offer(new SimpleEntry<>(trav.right, level+1));
		}
		return true;
	}

	public boolean isAlmostComplete() {
		boolean leafFound = false;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			// if first leaf is found, all nodes after that must be leaf.
			if(!leafFound && isLeaf(trav))
				leafFound = true;
			else if(leafFound && !isLeaf(trav))
				return false;
			// if node has right child, it must have left child
			if(trav.left == null && trav.right != null)
				return false;
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right!= null)
				q.offer(trav.right);
		}
		return true;
	}
	
	public boolean find(Node trav, int v) {
		while(trav != null) {
			if(v == trav.data)
				return true;
			if(v < trav.data)
				trav = trav.left;
			else
				trav = trav.right;
		}
		return false;
	}
	
	// least common ancestor -- nearest ancestor for both nodes
	public int nca(int v1, int v2) {
		Node trav = root;
		while(trav != null) {
			if(v1 < trav.data && v2 < trav.data)
				trav = trav.left;
			else if(v1 > trav.data && v2 > trav.data)
				trav = trav.right;
			else
				break;
		}
		if(!find(trav, v1) || !find(trav, v2))
			throw new RuntimeException("Node not found. Cannot find ancestor.");
		return trav.data; // return ancestor
	}
	
	void bfsLevelwise() {
		System.out.print("BFS: ");
		Queue<Node> q1 = new LinkedList<>();
		Queue<Node> q2 = new LinkedList<>();
		q1.offer(root);
		while(!q1.isEmpty() || !q2.isEmpty()) {
			while(!q1.isEmpty()) {
				Node trav = q1.poll();
				System.out.print(trav.data + ", ");
				if(trav.left != null)
					q2.offer(trav.left);
				if(trav.right!= null)
					q2.offer(trav.right);
			}
			System.out.print(" | ");
			while(!q2.isEmpty()) {
				Node trav = q2.poll();
				System.out.print(trav.data + ", ");
				if(trav.left != null)
					q1.offer(trav.left);
				if(trav.right!= null)
					q1.offer(trav.right);
			}
			System.out.print(" | ");
		}
		System.out.println();
	}
	
	void boundary() {
		System.out.print("Boundary: ");
		boolean flag;
		Queue<Node> q1 = new LinkedList<>();
		Queue<Node> q2 = new LinkedList<>();
		q1.offer(root);
		while(!q1.isEmpty() || !q2.isEmpty()) {
			flag = true;
			while(!q1.isEmpty()) {
				Node trav = q1.poll();
				if(flag == true || q1.isEmpty() || isLeaf(trav)) {
					System.out.print(trav.data + ", ");
					flag = false;
				}
				if(trav.left != null)
					q2.offer(trav.left);
				if(trav.right!= null)
					q2.offer(trav.right);
			}

			flag = true;
			while(!q2.isEmpty()) {
				Node trav = q2.poll();
				if(flag == true || q2.isEmpty() || isLeaf(trav)) {
					System.out.print(trav.data + ", ");
					flag = false;
				}
				if(trav.left != null)
					q1.offer(trav.left);
				if(trav.right!= null)
					q1.offer(trav.right);
			}
		}
		System.out.println();
	}


	void spiral() {
		System.out.print("Spiral: ");
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();
		s1.push(root);
		while(!s1.isEmpty() || !s2.isEmpty()) {
			while(!s1.isEmpty()) {
				Node trav = s1.pop();
				System.out.print(trav.data + ", ");
				if(trav.right!= null)
					s2.push(trav.right);
				if(trav.left != null)
					s2.push(trav.left);
			}
			System.out.print(" | ");
			while(!s2.isEmpty()) {
				Node trav = s2.pop();
				System.out.print(trav.data + ", ");
				if(trav.left != null)
					s1.push(trav.left);
				if(trav.right!= null)
					s1.push(trav.right);
			}
			System.out.print(" | ");
		}
		System.out.println();
	}
}

public class TreeInteview2 {
	public static void main(String[] args) {
		BinSearchTree t = new BinSearchTree();
		t.add(50);
		t.add(30);
		t.add(90);
		t.add(10);
		t.add(40);
		t.add(70);
		t.add(100);
		t.add(20);
		t.add(60);
		t.add(80);
//		t.add(5);
//		t.add(35);
//		t.add(45);
		t.preorder();
		t.inorder();
		t.postorder();
		t.dfs();
		System.out.println("Is Strict: " + t.isStrict());
		System.out.println("Is Complete: " + t.isComplete());
		System.out.println("Is Almost Complete: " + t.isAlmostComplete());
		System.out.println("NCA : " + t.nca(60, 80));
		t.bfs();	
		t.bfsLevelwise();
		t.boundary();
		t.spiral();
	}
}

/*
Tree terminologies from Tenanbaum book:
* Strictly Binary Tree: Each non-leaf node has exactly two child.
	* For each node, degree is 0 or 2.
	* If node has left child, it must have right child and vice versa.
* Complete Binary Tree: All leaf nodes are at same level.
* Almost Complete Binary Tree:
	* All leaf nodes are at last (d) or second-last level (d-1).
	* All leaf nodes at last (d) level should be aligned as left as possible.
	* Observations:
		* In BFS traversal if first leaf is found, all subsequent nodes are leaf nodes.
		* If node has right child, it must have left child as well.	
*/











