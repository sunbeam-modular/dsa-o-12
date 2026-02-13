package com.sunbeam;

public class BSTMain {

	public static void main(String[] args) {
		
		BST bst = new BST();
		
		bst.addNodeRec(8);
		bst.addNodeRec(3);
		bst.addNodeRec(10);
		bst.addNodeRec(2);
		bst.addNodeRec(15);
		bst.addNodeRec(6);
		bst.addNodeRec(14);
		bst.addNodeRec(4);
		bst.addNodeRec(7);
		
		
		//bst.inOrder();
		/*
		BST.Node ret = bst.binarySearch(20);
		if(ret != null)
			System.out.println("Key is found");
		else
			System.out.println("Key is not dound");
		
		
		
		bst.inOrder();
		bst.deleteNode(8);
		bst.inOrder();
		
		*/
		bst.DFSTraversal();
		bst.BFSTraversal();
		
		System.out.println("Height = " + bst.height());
		

	}

}
