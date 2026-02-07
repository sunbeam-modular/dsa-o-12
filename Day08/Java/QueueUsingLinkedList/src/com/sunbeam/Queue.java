package com.sunbeam;

public class Queue {
	private List l1;
	public Queue() {
		l1 = new List();
	}
	
	public void push(int value) {
		l1.addLast(value);
	}
	
	public void pop() {
		l1.deleteFirst();
	}
	
	public int peek() {
		return l1.getData();
	}
	
	public boolean isEmpty() {
		return l1.isEmpty();
	}

}





