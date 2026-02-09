package com.sunbeam;

import com.sunbeam.Que03Main.SinglyList;

public class Que4Main {
	
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
		
		public void printMid() {
			if(head != null) {
				Node slow = head, fast = head;
				while(fast != null && fast.next != null) {
					slow = slow.next;
					fast = fast.next.next;
				}
				System.out.println("Middle Node: " + slow.data);
			}
			else
				System.out.println("List is empty!");
		}
	}

	public static void main(String[] args) {
		SinglyList list = new SinglyList();
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		list.addLast(50);
		list.addLast(60);
		list.addLast(70);
		list.addLast(80);
		list.display();
		list.printMid();
	}
}
