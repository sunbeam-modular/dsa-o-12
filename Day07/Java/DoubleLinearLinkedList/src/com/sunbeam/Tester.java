package com.sunbeam;

public class Tester {

	public static void main(String[] args) {
		
		LinkedList l1 = new LinkedList();
		
		
		l1.addFirst('n');
		l1.addFirst('u');
		l1.addFirst('s');
		l1.addLast('b');
		l1.addLast('e');
		l1.addLast('a');
		l1.addLast('m');
		
		//l1.addPosition('@', 4);
		//l1.addPosition('@', 0);
		//l1.addPosition('@', 9);
		//l1.addPosition('@', 1);
		//l1.addPosition('@', 7);
		//l1.addPosition('@', 8);
		
		//l1.deleteFirst();
		//l1.deleteLast();
		
		//l1.deletePosition(3);
		//l1.deletePosition(0);
		//l1.deletePosition(8);
		//l1.deletePosition(1);
		l1.deletePosition(7);
		
		l1.displayForward();
		l1.displayBackward();
		System.out.println("size = " + l1.size());
		
		l1.deleteAll();

	}

}
