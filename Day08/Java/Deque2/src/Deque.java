
public class Deque {
	static class Node{
		private int data;
		private Node next, prev;
		public Node(int value) {
			data = value;
			next = prev = null;
		}
	}
	
	private Node front;		// head	-- push - Add first, pop - delete first
	private Node rear;		// tail -- push - Add last, pop - delete last
	
	public Deque() {
		front = rear = null;
	}
	
	public boolean isEmpty() {
		return front == null && rear == null;
	}
	
	public int getFront() {
		return front.data;
	}
	
	public int getRear() {
		return rear.data;
	}
	
	public void pushFront(int value) {	// add first
		// create a newnode
		Node newnode = new Node(value);
		// add newnode at first position
		if(isEmpty())
			front = rear = newnode;
		else {
			newnode.next = front;
			front.prev = newnode;
			front = newnode;
		}
	}
	
	public void pushRear(int value) { 	// add last
		// create a newnode
		Node newnode = new Node(value);
		// add newnode at last position
		if(isEmpty())
			front = rear = newnode;
		else {
			newnode.prev = rear;
			rear.next = newnode;
			rear = newnode;
		}
	}
	
	public int popFront() {			// delete first
		if(isEmpty())
			return -1;
		int temp = front.data;
		// delete node from first position
		if(front == rear)
			front = rear = null;
		else {
			front = front.next;
			front.prev = null;
		}
		return temp;
	}
	
	public int popRear() {			// delete last
		if(isEmpty())
			return -1;
		int temp = rear.data;
		// delete node from first position
		if(front == rear)
			front = rear = null;
		else {
			rear = rear.prev;
			rear.next = null;
		}
		return temp;
	}

}












