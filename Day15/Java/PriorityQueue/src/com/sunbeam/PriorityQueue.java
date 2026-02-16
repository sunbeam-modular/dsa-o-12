package com.sunbeam;

public class PriorityQueue {
	private Heap h;
	private int capacity;
	
	public PriorityQueue(int cap) {
		capacity = cap;
		h = new Heap(cap);
	}
	
	public void push(int value) {
		h.addHeap(value);
	}
	
	public int pop() {
		return h.deleteHeap();
	}
	
	public int peek() {
		return h.getRoot();
	}
	
	public boolean isEmpty() {
		return h.isEmpty();
	}
	
	public boolean isFull() {
		return h.isFull();
	}

}
