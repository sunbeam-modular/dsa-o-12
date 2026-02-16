package com.sunbeam;

public class PriorityQueueMain {

	public static void main(String[] args) {
		
		PriorityQueue q = new PriorityQueue(9);
		
		q.push(20);
		q.push(12);
		q.push(35);
		q.push(15);
		
		System.out.println("isFull : " + q.isFull());		// false
		
		System.out.println("Peeked value : " + q.peek());	// 35
		
		while(!q.isEmpty())
			System.out.println("Popped value : " + q.pop());	//35 20 15 12
		

	}

}
