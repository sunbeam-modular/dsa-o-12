package com.sunbeam;

public class List {
	static class Node{
		private int data;
		private Node next;
		private Node prev;
		public Node(int value) {
			data = value;
			next = prev = null;
		}
	}
	
	private Node head;
	public List() {
		head = null;
	}
	
	public void insert(Node after, Node newnode, Node before) {
		//1. add before node into next of newnode
		newnode.next = before;
		//2. add after node into prev of newnode
		newnode.prev = after;
		//3. add newnode into next of after node
		after.next = newnode;
		//4. add newnode into prev of before node
		before.prev = newnode;
	}
	
	public void addFirst(int value) {
		//1. create newnode
		Node newnode = new Node(value);
		//2. if list is empty
		if(head == null) {
			//a. add newnode into head
			head = newnode;
			//b. make list circular
			newnode.next = newnode.prev = newnode;
		}
		//3. if list is not empty
		else {
			//a. call insert 
			insert(head.prev, newnode, head);
			//b. move head on newnode
			head = newnode;
		}
	}
	
	public void addLast(int value) {
		//1. create newnode
		Node newnode = new Node(value);
		//2. if list is empty
		if(head == null) {
			//a. add newnode into head
			head = newnode;
			//b. make list circular
			newnode.next = newnode.prev = newnode;
		}
		//3. if list is not empty
		else {
			//a. call insert 
			insert(head.prev, newnode, head);
		}
	}
	public void delete(Node after, Node before) {
		//1. add before node into next of after node
		after.next = before;
		//2. add after node into prev of before node
		before.prev = after;
	}
	
	public void deleteFirst() {
		//1. if list is empty
		if(head == null)
			return;
		//2. if list has single node
		else if(head.next == head)
			head = null;
		//3. if list has multiple nodes
		else {
			//a. call delete method
			delete(head.prev, head.next);
			//b. move head on second node
			head = head.next;
		}
	}
	
	public void deleteLast() {
		//1. if list is empty
		if(head == null)
			return;
		//2. if list has single node
		else if(head.next == head)
			head = null;
		//3. if list has multiple nodes
		else {
			//a. call delete method
			delete(head.prev.prev, head);
		}
	}
	
	public int getData() {
		return head.data;
	}

		
	public boolean isEmpty() {
		return head == null;
	}
	
	public void deleteAll() {
		head = null;
	}
	
}











