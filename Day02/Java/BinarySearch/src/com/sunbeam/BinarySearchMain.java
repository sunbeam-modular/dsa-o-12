package com.sunbeam;

import java.util.Scanner;

public class BinarySearchMain {

	
	public static int binarySearch(int arr[], int key, int left, int right) {
		//1. stop if partition is invalid
		if(left > right)
			return -1;
		//2. find index of middle element
		int mid = (left + right)/2;
		//3. compare key with middle element
		if(key == arr[mid])
			return mid;
		//4. if key is less than middle element then search it into left partition
		else if(key < arr[mid])
			return binarySearch(arr, key, left, mid - 1);
		//5. uf key is greater than middle element then search it into right partition
		else
			return binarySearch(arr, key, mid + 1, right);
	}
	
	public static void main(String[] args) {
		int arr[] = {11, 22, 33, 44, 55, 66, 77, 88, 99};
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter key to be searched : ");
		int key = sc.nextInt();
		
		int index = binarySearch(arr, key, 0, arr.length-1);
		if(index != -1)
			System.out.println("Key is found at index " + index);
		else
			System.out.println("Key is not found");
		
		sc.close();

	}

}
