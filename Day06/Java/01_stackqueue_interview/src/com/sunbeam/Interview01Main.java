package com.sunbeam;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueue {
	// implemented using two queues (main, temp)
	private Queue<Integer> main;
	private Queue<Integer> temp;
	
	public StackUsingQueue() {
		main = new LinkedList<>();
		temp = new LinkedList<>();
	}
	
	public void push(int val) {
		// pop all elems from main queue and push in temp queue
		while(!main.isEmpty())
			temp.offer(main.poll());
		// push new elem in main queue
		main.offer(val);
		// pop all elems from temp queue and push them back in main queue
		while(!temp.isEmpty())
			main.offer(temp.poll());
	}
	
	public int pop() {
		return main.poll();
	}
	
	public boolean isEmpty() {
		return main.isEmpty();
	}
}

public class Interview01Main {
	public static void main(String[] args) {
		StackUsingQueue s = new StackUsingQueue();
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);
		s.push(50);
		while(!s.isEmpty())
			System.out.println("Popped: " + s.pop()); // 50, 40, 30, 20, 10
	}
}
