package com.sunbeam;

public class SinglyLinearLinkedListMain {

	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		
		list.addFirst(40);
		list.addFirst(30);
		list.addFirst(20);
		list.addFirst(10);
		// 10 -> 20 -> 30 -> 40
		
		list.addLast(50);
		list.addLast(60);
		
		list.deleteFirst();
		list.deleteLast();
		
		list.display();
	}

}
