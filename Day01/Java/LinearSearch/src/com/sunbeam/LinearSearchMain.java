package com.sunbeam;

import java.util.Scanner;

public class LinearSearchMain {

	public static int linearSearch(int arr[], int key) {
		//1. traverse array from 0 to n-1 index
		for(int i = 0 ; i < arr.length ; i++) {
			//2. compare ith element with key
			if(key == arr[i])
				//2.1 key is found return its index
				return i;
		}
		//3. if key is not found return -1
		return -1;
	}
	
	public static void main(String[] args) {
		int arr[] = {88, 33, 66, 99, 11, 77, 22, 55, 44};
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter key to be searched : ");
		int key = sc.nextInt();
		
		int index = linearSearch(arr, key);
		if(index != -1)
			System.out.println("Key is found at index " + index);
		else
			System.out.println("Key is not found");
		
		sc.close();

	}

}
