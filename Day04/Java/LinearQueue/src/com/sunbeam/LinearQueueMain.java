package com.sunbeam;

import java.util.Scanner;

class LinearQueue{
	// data - array, front, rear
	private int arr[];
	private int front, rear;
	
	public LinearQueue(int size) {
		arr = new int[size];
		front = -1;
		rear = -1;
	}
	
	public void push(int value) {
		//1. reposition rear
		rear++;
		//2. add value at rear index
		arr[rear] = value;
	}
	
	public int pop() {
		int val = arr[front + 1];
		//1. reposition front
		front++;
		return val;
	}
	
	public int peek() {
		//1. read/return next data
		int val = arr[front + 1];
		return val;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return rear == arr.length-1;
	}
}

public class LinearQueueMain {

	public static void mainBasic(String[] args) {
		// create object of LinearQueue class
		LinearQueue q = new LinearQueue(5);
		
		q.push(11);
		q.push(22);
		q.push(33);
		q.push(44);
		q.push(55);
		
		if(q.isFull())
			System.out.println("Queue is full");
		else
			System.out.println("Queue is not full");
		
		System.out.println("Peeked data = " + q.peek());  	//11
		
		while(!q.isEmpty()) {
			System.out.println("Poped data = " + q.pop()); 		// 11, 22, 33, 44, 55
		}
		
		
		q.push(66);

	}
	
	public static void main(String[] args) {
		
		LinearQueue q = new LinearQueue(5);
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("0.Exit\n1. Push\n2. Pop\n3. Peek");
			System.out.print("Enter your choice : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				if(!q.isFull()) {
					System.out.print("Enter data to be pushed : ");
					int data = sc.nextInt();
					q.push(data);
				}else
					System.out.println("Queue is full");
				break;
			case 2:
				if(!q.isEmpty())
					System.out.println("Poped data : " + q.pop());
				else
					System.out.println("Queue is empty");
				break;
			case 3:
				if(!q.isEmpty())
					System.out.println("Peeked data : " + q.peek());
				else
					System.out.println("Queue is empty");
				break;
			}
		}while(choice != 0);
		
		sc.close();
	}

}













