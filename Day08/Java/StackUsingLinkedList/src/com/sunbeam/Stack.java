package com.sunbeam;

public class Stack {
	private List l1;
	public Stack() {
		l1 = new List();
	}
	
	public void push(int value) {
		l1.addFirst(value);
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





