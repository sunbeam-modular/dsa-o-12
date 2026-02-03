package com.sunbeam;

import java.util.Scanner;

class CircularQueue{
	// data - array, front, rear
	private int arr[];
	private int front, rear;
	
	public CircularQueue(int size) {
		arr = new int[size];
		front = -1;
		rear = -1;
	}
	
	public void push(int value) {
		//1. reposition rear
		rear = (rear + 1) % arr.length;
		//2. add value at rear index
		arr[rear] = value;
	}
	
	public int pop() {
		int val = arr[(front + 1) % arr.length];
		//1. reposition front
		front = (front + 1) % arr.length;
		// if queue is becoming empty then reset front and rear back to -1
		if(front == rear)
			front = rear = -1;
		return val;
	}
	
	public int peek() {
		//1. read/return next data
		int val = arr[(front + 1) % arr.length];
		return val;
	}
	
	public boolean isEmpty() {
		return front == rear && rear == -1;
	}
	
	public boolean isFull() {
		return (front == -1 && rear == arr.length-1) || (front == rear && rear != -1);
	}
}

public class CircularQueueMain {

	public static void main(String[] args) {
		CircularQueue q = new CircularQueue(5);
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
