package com.sunbeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinearSearchMain {

	
	public static List<Integer> linearSearchAll(int arr[], int key) {
		List<Integer> indices = new ArrayList<Integer>();
		//1. traverse array from 0 to n-1 index
		for(int i = 0 ; i < arr.length ; i++) {
			//2. compare ith element with key
			if(key == arr[i])
				//2.1 key is found return its index
				indices.add(i);			// add index into array list
		}
		//3. return array list of indices
		return indices;
	}
	
	
	
	public static void main(String[] args) {
		int arr[] = {88, 33, 66, 99, 11, 77, 22, 11, 44};
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter key to be searched : ");
		int key = sc.nextInt();
				
		List<Integer> indices = linearSearchAll(arr, key);
		if(indices.size() != 0)
			System.out.println("Key is found at " + indices);
		else
			System.out.println("Key is not found");
		
		sc.close();

	}

}
