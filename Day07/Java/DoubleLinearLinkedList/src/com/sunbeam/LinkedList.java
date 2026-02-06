package com.sunbeam;

public class LinkedList {
	static class Node{
		private char data;
		private Node next;
		private Node prev;
		
		public Node(char value) {
			data = value;
			next = prev = null;
		}
	}
	
	
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList() {
		head = tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
		//return head == null && tail == null;
		//return size == 0;
	}
	
	public void addFirst(char value) {
		//1. create node
		Node newnode = new Node(value);
		//2. if list is empty add newnode into head and tail
		if(head == null)
			head = tail = newnode;
		//3. if list is not empty
		else {
			//a. add first node into next of newnode
			newnode.next = head;
			//b. add newnode into prev of first node
			head.prev = newnode;
			//c. move head on newnode
			head = newnode;
		}
		size++;
	}
	
	public void addLast(char value) {
		//1. create node
		Node newnode = new Node(value);
		//2. if list is empty add newnode into head and tail
		if(head == null)
			head = tail = newnode;
		//3. if list is not empty
		else {
			//a. add last node into prev of newnode
			newnode.prev = tail;
			//b. add newnode into next of last node
			tail.next = newnode;
			//c. move tail on newnode
			tail = newnode;
		}
		size++;
	}
	
	public void addPosition(char value, int pos) {
		//0. validate position		
			// valid position :	pos >= 1 && pos <= size + 1
			// invalid position:  pos < 1 || pos > size + 1
		if(pos < 1 || pos > size + 1)
			return;
		//1. create a newnode
		Node newnode = new Node(value);
		//2. if list is empty, add newnode into head and tail
		if(head == null)
			head = tail = newnode;
		// special case : pos = 1
		else if(pos == 1) {
			//a. add first node into next of newnode
			newnode.next = head;
			//b. add newnode into prev of first node
			head.prev = newnode;
			//c. move head on newnode
			head = newnode;
		}
		// special case : pos = size + 1
		else if(pos == size + 1) {
			//a. add last node into prev of newnode
			newnode.prev = tail;
			//b. add newnode into next of last node
			tail.next = newnode;
			//c. move tail on newnode
			tail = newnode;
		}
		//3. if list is not empty
		else {
			//a. traverse till pos - 1
			Node trav = head;
			for(int i = 1 ; i < pos - 1 ; i++)
				trav = trav.next;
			//b. add pos -1 node into prev of newnode
			newnode.prev = trav;
			//c. add pos node into next of newnode
			newnode.next = trav.next;
			//d. add newnode into next of pos -1 node
			trav.next = newnode;
			//e. add newnode into prev of pos node
			newnode.next.prev = newnode;
		}
		size++;
	}
	
	public void deleteFirst() {
		//1. if list is empty, return 
		if(head == null)
			return;
		//2. if list has single node
		else if(head == tail)
			head = tail = null;
		//3. if list has multiple nodes
		else {
			//a. move head on second node
			head = head.next;
			//b. add null into prev of second node
			head.prev = null;
		}
		size--;
	}
	
	public void deleteLast() {
		//1. if list is empty, return 
		if(head == null)
			return;
		//2. if list has single node
		else if(head == tail)
			head = tail = null;
		//3. if list has multiple nodes
		else {
			//a. move tail on second last node
			tail = tail.prev;
			//b. add null into next of second last node
			tail.next = null;
		}
		size--;
	}
	
	public void deletePosition(int pos) {
		//0. validate positions
		if(pos < 1 || pos > size)
			return;
		//1. if list is empty return
		if(head == null)
			return;
		//2. if list has single node
		else if(head == tail)
			head = tail = null;
		//special case 1: pos = 1
		else if(pos == 1) {
			//a. move head on second node
			head = head.next;
			//b. add null into prev of second node
			head.prev = null;
		}
		//special case 2 : pos = size
		else if(pos == size) {
			//a. move tail on second last node
			tail = tail.prev;
			//b. add null into next of second last node
			tail.next = null;
		}
		//2. if list has multiple nodes
		else {
			//a. traverse till pos node
			Node trav = head;
			for(int i = 1 ; i < pos ; i++)
				trav = trav.next;
			//b. add pos + 1 node into next of pos - 1 node
			trav.prev.next = trav.next;
			//c. add pos - 1 node into prev of pos + 1 node
			trav.next.prev = trav.prev;
		}
		size--;
	}
	
	public void displayForward() {
		Node trav = head;
		System.out.print("Forward List :");
		while(trav != null) {
			System.out.print(" " + trav.data);
			trav = trav.next;
		}
		System.out.println("");
	}
	
	public void displayBackward() {
		Node trav = tail;
		System.out.print("Backward List :");
		while(trav != null) {
			System.out.print(" " + trav.data);
			trav = trav.prev;
		}
		System.out.println("");
	}
	
	public int size() {
		return size;
	}
	
	public void deleteAll() {
		head = tail = null;
		size = 0;
	}
}





















