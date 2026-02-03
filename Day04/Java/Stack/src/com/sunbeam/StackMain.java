package com.sunbeam;

import java.util.Scanner;

class Stack{
	private int arr[];
	private int top;
	
	public Stack(int size) {
		arr = new int[size];
		top = -1;
	}
	
	public void push(int value) {
		//1. reposition top
		top++;
		//2. add value at top index
		arr[top] = value;
	}
	
	public int pop() {
		int val = arr[top];
		//1. reposition top
		top--;
		return val;
	}
	
	public int peek() {
		//1. read and return value of top next
		int val = arr[top];
		return val;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == arr.length - 1;
	}
}

public class StackMain {

	public static void main(String[] args) {

		Stack st = new Stack(5);
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("0. Exit\n1. Push\n2. Pop\n3. Peek");
			System.out.print("Enter your choice : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				if(!st.isFull()) {
					System.out.print("Enter value to be pushed : ");
					int value = sc.nextInt();
					st.push(value);
				}else
					System.out.println("Stack is Full");
				break;
			case 2:
				if(!st.isEmpty())
					System.out.println("Poped value : " + st.pop());
				else
					System.out.println("Stack is empty");
				break;
			case 3:
				if(!st.isEmpty())
					System.out.println("Peeked value : " + st.peek());
				else
					System.out.println("Stack is empty");
				break;
			}
		}while(choice != 0);
		
		sc.close();

	}

}













