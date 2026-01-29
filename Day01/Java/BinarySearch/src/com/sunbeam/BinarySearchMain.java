package com.sunbeam;

import java.util.Scanner;

public class BinarySearchMain {

	public static int binarySearch(int arr[], int key) {
		//1. decide left and right of the array
		int left = 0, right = arr.length-1, mid;
		
		//2. search while partition is valid, stop if partition is invalid
		while(left <= right) {
			//3. find index if middle element
			mid = (left + right) / 2;
			//4. compare key with middle element
			if(key == arr[mid])
				//4.1 if key is matching then return index
				return mid;
			//4.2 if key is less than middle element
			else if(key < arr[mid])
				right = mid - 1;
			//4.3 if key is greater than middle element
			else
				left = mid + 1;
		}
		//5. if key is not found
		return -1;
	}
	
	public static void main(String[] args) {
		int arr[] = {11, 22, 33, 44, 55, 66, 77, 88, 99};
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter key to be searched : ");
		int key = sc.nextInt();
		
		int index = binarySearch(arr, key);
		if(index != -1)
			System.out.println("Key is found at index " + index);
		else
			System.out.println("Key is not found");
		
		sc.close();

	}

}
