package com.sunbeam;

public class Que03Main {
	static class SinglyList {

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
		
		// reversing linked list using three pointers (including head).
		// aux space: O(1)			// time: O(n) 
		public void revList() {
			// consider list as old list
			Node oldhead = head;
			// consider new list as empty
			head = null;
			while(oldhead != null) {
				// delete first node from old list
				Node temp = oldhead;
				oldhead = oldhead.next;
				// add that node at first in new list
				temp.next = head;
				head = temp;
			} // repeat until old list is empty
		}
		
		// recursive list reverse using three pointers
		// time: O(n)		aux space: O(n) -- fn activation records on fn stack
		/*
		public Node recRevList(Node trav) {
			// if list has single node, make it head & return it
			if(trav.next == null) {
				head = trav;
				return trav;
			}
			// reverse remaining list (ahead of trav)
			Node last = recRevList(trav.next);
			// add current node (trav) to next of the last node (of remaining linked list)
			last.next = trav;
			// current node will be now last node i.e. make its next null
			trav.next = null;
			// return that node (to prev call)
			return trav;
		}
		*/

		// recursive list reverse using only two pointers
		// time: O(n)		aux space: O(n) -- fn activation records on fn stack
		public Node recRevList(Node trav) {
			// if list has single node, make it head & return it
			if(trav.next == null) {
				head = trav;
				return trav;
			}
			// reverse remaining list (ahead of trav)
			// + add current node (trav) to next of the last node (of remaining linked list)
			recRevList(trav.next).next = trav;
			// current node will be now last node i.e. make its next null
			trav.next = null;
			// return that node (to prev call)
			return trav;
		}
		
		public void recRevList() {
			if(head != null)
				recRevList(head);
		}
	}
	
	public static void main(String[] args) {
		SinglyList list = new SinglyList();
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		list.addLast(50);
		list.display();
		//list.revList();	// list reverse without recursion
		list.recRevList();  // list reverse using recursion
		list.display();
	}
}
