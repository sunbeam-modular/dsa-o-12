package com.sunbeam;

public class StackUsingLinkedListMain {

	public static void main(String[] args) {

		Queue st = new Queue();
		
		System.out.println("isEmpty : " + st.isEmpty());	//true
		
		st.push(10);
		st.push(20);
		st.push(30);
		st.push(40);
		
		System.out.println("Peeked value : " + st.peek()); //10

		st.pop();
		System.out.println("Peeked value : " + st.peek()); //20
		st.pop();
		
		System.out.println("Peeked value : " + st.peek()); //30
		
		System.out.println("isEmpty : " + st.isEmpty());	//false
		
	}

}
