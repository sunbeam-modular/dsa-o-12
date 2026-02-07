
public class LinkedList {
	static class Node{
		private int data;
		private Node next, prev;
	
		public Node(int value) {
			data = value;
			next = prev = null;
		}
	}
	
	private Node head;
	
	public LinkedList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int getFirst() {
		return head.data;
	}
	
	public int getLast() {
		return head.prev.data;
	}
	
	public void addFirst(int value) {
		//a. create a newnode
		Node newnode = new Node(value);
		//b. if list is empty
		if(head == null) {
			//1. add newnode into head
			head = newnode;
			//2. make list circular
			newnode.next = newnode.prev = newnode;
		}
		//c. if list is not empty
		else {
			//1. add last node into prev of newnode
			newnode.prev = head.prev;
			//2. add first node into next of newnode
			newnode.next = head;
			//3. add newnode into prev of first node
			head.prev = newnode;
			//4. add newnode into next of last node
			newnode.prev.next = newnode;
			//5. move head on newnode
			head = newnode;
		}
	}
	
	public void addLast(int value) {
		//a. create a newnode
		Node newnode = new Node(value);
		//b. if list is empty
		if(head == null) {
			//1. add newnode into head
			head = newnode;
			//2. make list circular
			newnode.next = newnode.prev = newnode;
		}
		//c. if list is not empty
		else {
			//1. add last node into prev of newnode
			newnode.prev = head.prev;
			//2. add first node into next of newnode
			newnode.next = head;
			//3. add newnode into prev of first node
			head.prev = newnode;
			//4. add newnode into next of last node
			newnode.prev.next = newnode;
		}
	}
	
	public void deleteFirst() {
		//1. if list is emmpty
		if(head == null)
			return;
		//2. if list has single node
		else if(head.next == head)
			head = null;
		//3. if list has multiple nodes
		else {
			//a. add second node into next of last node
			head.prev.next = head.next;
			//b. add last node into prev of second node
			head.next.prev = head.prev;
			//c. move head on second node
			head = head.next;
		}
	}
	
	public void deleteLast() {
		//1. if list is emmpty
		if(head == null)
			return;
		//2. if list has single node
		else if(head.next == head)
			head = null;
		//3. if list has multiple nodes
		else {
			//a. add second last node into prev of first node
			head.prev = head.prev.prev;
			//b. add first node into next of second last node
			head.prev.next = head;
		}
	}
	
	
	public void fDisplay() {
		if(head == null)
			return;
		System.out.print("Forward List : ");
		Node trav = head;
		do {
			System.out.print(" " + trav.data);
			trav = trav.next;
		}while(trav != head);
		System.out.println("");
	}
	
	public void rDisplay() {
		if(head == null)
			return;
		System.out.print("Backward List : ");
		Node trav = head.prev;
		do {
			System.out.print(" " + trav.data);
			trav = trav.prev;
		}while(trav != head.prev);
		System.out.println("");
	}
	
	public void deleteAll() {
		head = null;
	}
}




















