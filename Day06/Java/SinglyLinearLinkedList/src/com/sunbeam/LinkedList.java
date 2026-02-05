package com.sunbeam;

public class LinkedList {
	static class Node{
		private int data;
		private Node next;
		
		public Node(int value) {
			data = value;
			next = null;
		}
	}
	
	private Node head;
	
	public LinkedList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void addFirst(int value) {
		//1. create a newnode
		Node newnode = new Node(value);
		//2. add first node into next of newnode
		newnode.next = head;
		//3. update head by newnode
		head = newnode;
	}
	
	public void addLast(int value) {
		//1. create newnode with given data
		Node newnode = new Node(value);
		//2. if list is empty
		if(head == null)
			// add newnode into head itself
			head = newnode;
		//3. if list is not empty
		else {
			//a. travers till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			//b. add newnode into next of last node
			trav.next = newnode;
		}
	}
	
	public void deleteFirst() {
		//1. if list is empty return
		if(head == null)
			return;
		//2. if list is not empty, move head on second node
		head = head.next;
	}
	
	public void deleteLast() {
		//1. if list is empty, return
		if(head == null)
			return;
		//2. if list has single node, delete it
		else if(head.next == null)
			head = null;
		//3. if list has multiple nodes
		else {
			//a. traverse till second last node
			Node trav = head;
			while(trav.next.next != null)
				trav = trav.next;
			//b. add null into next of second last node
			trav.next = null;
		}
	}
	
	public void display() {
		//1. create trav and start at first node
		Node trav = head;
		System.out.print("List :: ");
		while(trav != null) {
			//2. print current node
			System.out.print(" " + trav.data);
			//3. go on next node
			trav = trav.next;
		}//4. repeat step 2 and 3 till last node
		System.out.println("");
	}
	
}




















