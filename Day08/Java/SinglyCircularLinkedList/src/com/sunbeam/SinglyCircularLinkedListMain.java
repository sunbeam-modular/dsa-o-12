package com.sunbeam;

public class SinglyCircularLinkedListMain {

	public static void main(String[] args) {
		
		LinkedList l1 = new LinkedList();
		
		l1.addFirst(20);
		l1.addFirst(10);
		l1.addLast(30);
		l1.addLast(40);
		
		//l1.deleteFirst();
		l1.deleteLast();
		
		l1.display();

	}

}
