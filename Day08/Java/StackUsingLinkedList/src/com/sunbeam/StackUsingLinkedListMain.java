package com.sunbeam;

public class StackUsingLinkedListMain {

	public static void main(String[] args) {

		Stack st = new Stack();
		
		System.out.println("isEmpty : " + st.isEmpty());	//true
		
		st.push(10);
		st.push(20);
		st.push(30);
		st.push(40);
		
		System.out.println("Peeked value : " + st.peek()); //40

		st.pop();
		System.out.println("Peeked value : " + st.peek()); //30
		st.pop();
		
		System.out.println("Peeked value : " + st.peek()); //20
		
		System.out.println("isEmpty : " + st.isEmpty());	//false
		
	}

}
