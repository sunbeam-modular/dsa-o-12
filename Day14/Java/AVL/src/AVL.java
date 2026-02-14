

public class AVL {
	static class Node{
		private int data;
		private Node left, right;
		private int height;
		public Node(int value) {
			data = value;
			left = right = null;
			height = 0;
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getLeftHeight() {
			return left == null ? -1 : left.height;
		}
		
		public int getRightHeight() {
			return right == null ? -1 : right.height;
		}
		
		public int getBF() {
			return getLeftHeight() - getRightHeight();
		}
	}
	
	private Node root;
	
	public AVL() {
		root = null;
	}
	
	public void addNode(int value) {
		if(root == null)
			root = new Node(value);
		else
			addNode(null, root, value);
	}
	
	public void addNode(Node parent, Node trav, int value) {
		if(value < trav.data) {
			if(trav.left == null)
				trav.left = new Node(value);
			else
				addNode(trav, trav.left, value);
		}
		else {
			if(trav.right == null)
				trav.right = new Node(value);
			else
				addNode(trav, trav.right, value);
		}
		
		trav.height = Integer.max(trav.getLeftHeight(), trav.getRightHeight()) + 1;
		int bf = trav.getBF();
		// LL
		if(bf > 1 && value < trav.left.data) {
			rightRotation(parent, trav);
			return;
		}
		// RR
		if(bf < -1 && value > trav.right.data) {
			leftRotation(parent, trav);
			return;
		}
		// LR
		if(bf > 1 && value > trav.left.data) {
			leftRotation(trav, trav.left);
			rightRotation(parent, trav);
			return;
			
		}
		// RL
		if(bf < -1 && value < trav.right.data) {
			rightRotation(trav, trav.right);
			leftRotation(parent, trav);
			return;
		}		
	}
	
	public void deleteNode(int key) {
		deleteNode(null, root, key);
	}
	
	public void deleteNode(Node parent, Node temp, int key) {
		if(temp == null)
			return;
		if(key < temp.data)
			deleteNode(temp, temp.left, key);
		else if(key > temp.data)
			deleteNode(temp, temp.right, key);
		else {
			if(temp.left != null && temp.right != null) {
				Node succ = temp.right;
				while(succ.left != null)
					succ = succ.left;
				temp.data = succ.data;
				deleteNode(temp, temp.right, succ.data);
			}
			if(temp.left == null || temp.right == null) {
				Node child = temp.left == null ? temp.right : temp.left;
				if(temp == root)
					root = child;
				else if(temp == parent.left)
					parent.left = child;
				else if(temp == parent.right)
					parent.right = child;
				
				temp = child;
			}
		}
		if(temp == null)
			return;
		
		temp.height = Integer.max(temp.getLeftHeight(), temp.getRightHeight()) + 1;
		int bf = temp.getBF();
		
		// LL
		if(bf > 1 && temp.left.getBF() >= 0) {
			rightRotation(parent, temp);
		}
		// LR
		if(bf > 1 && temp.left.getBF() < 0) {
			leftRotation(temp, temp.left);
			rightRotation(parent, temp);
		}
		// RR
		if(bf < -1 && temp.right.getBF() <= 0) {
			leftRotation(parent, temp);
		}
		// RL
		if(bf < -1 && temp.right.getBF() > 0) {
			rightRotation(temp, temp.right);
			leftRotation(parent, temp);
		}
	}
	
	public void leftRotation(Node parent, Node axis) {
		Node newaxis = axis.right;
		axis.right = newaxis.left;
		newaxis.left = axis;
		if(axis == root)
			root = newaxis;
		else if(axis == parent.left)
			parent.left = newaxis;
		else if(axis == parent.right)
			parent.right = newaxis;
		axis.height = Integer.max(axis.getLeftHeight(), axis.getRightHeight()) + 1;
		newaxis.height = Integer.max(newaxis.getLeftHeight(), axis.getRightHeight()) + 1;
	}
	
	public void rightRotation(Node parent, Node axis) {
		Node newaxis = axis.left;
		axis.left = newaxis.right;
		newaxis.right = axis;
		if(axis == root)
			root = newaxis;
		else if(axis == parent.left)
			parent.left = newaxis;
		else if(axis == parent.right)
			parent.right = newaxis;
		axis.height = Integer.max(axis.getLeftHeight(), axis.getRightHeight()) + 1;
		newaxis.height = Integer.max(newaxis.getLeftHeight(), axis.getRightHeight()) + 1;
	}
	
	public void inOrder(Node trav) {
		if(trav == null)
			return;
		inOrder(trav.left);
		System.out.print(" " + trav.data);
		inOrder(trav.right);
	}
	
	public void inOrder() {
		System.out.print("AVL Tree : ");
		inOrder(root);
		System.out.println("");
		System.out.println("Height : " + root.getHeight());
		System.out.println("Root : " + root.data);
	}
	
}














