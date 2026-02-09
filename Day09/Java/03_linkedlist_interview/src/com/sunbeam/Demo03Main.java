package com.sunbeam;

class SinglyList {

	static class Node {
		private int data;
		private Node next;
		public Node(int val) {
			this.data = val;
			this.next = null;
		}
	}
	
	//SinglyList fields
	private Node head;
	
	//SinglyList methods
	public SinglyList() {
		head = null;
	}
	
	public void display() {
		System.out.print("List: ");
		// traversing begins with head node
		Node trav = head;
		while(trav != null) {
			// for each node print data and go to next node
			System.out.print(trav.data + " -> ");
			trav = trav.next;
		} // repeat until null (end of list) is reached
		System.out.println();
	}
	
	public void addLast(int val) {
		// create new node
		Node newNode = new Node(val);
		// if list is empty, newnode is first node
		if(head == null)
			head = newNode;
		else { // otherwise
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// add newnode to next of last node
			trav.next = newNode;
		}
	}
	
	// selection sort
	public void selectionSort() {
		for(Node i=head; i!=null; i=i.next) {
			for(Node j=i.next; j!=null; j=j.next) {
				if(i.data > j.data) {
					int temp = i.data;
					i.data = j.data;
					j.data = temp;
				}
			}
		}
	}
	
	public void revDisplay(Node trav) {
		// if list is empty, do nothing
		if(trav == null)
			return;
		// reverse display remaining list (ahead of trav)
		revDisplay(trav.next);
		// display current node
		System.out.print(trav.data + ", ");
	}
	
	// wrapper fn to call recursive logic with head as arg
	public void revDisplay() {
		System.out.print("Rev : ");
		revDisplay(head);
		System.out.println();
	}
}

public class Demo03Main {
	public static void main(String[] args) {
		SinglyList list = new SinglyList();
		list.addLast(30);
		list.addLast(50);
		list.addLast(10);
		list.addLast(40);
		list.addLast(20);
		list.display(); // 30 -> 50 -> 10 -> 40 -> 20 -> 
		list.selectionSort();
		list.display(); // 10 -> 20 -> 30 -> 40 -> 50 -> 
		list.revDisplay();
	}
}
