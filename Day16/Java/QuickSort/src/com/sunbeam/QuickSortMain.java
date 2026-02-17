package com.sunbeam;

import java.util.Arrays;

public class QuickSortMain {
	
	public static void quickSort(int arr[], int left, int right) {
		//0. stop if partition has single element or invalid
		if(left >= right)
			return;
		//1. select pivot
		//int pivot = arr[left];
		//2,3. arrage elements - lesser on left and greater on right of pivot
		int i = left, j = right;
		while(i < j) {
			// find greater element from  left side
			for( ; i <= right && arr[i] <= arr[left] ; i++);
			// find smaller and equal element from right side
			for( ; arr[j] > arr[left] ; j--);
			// if i and j are node crossed, swap ith and jth element
			if(i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// if i and j are crossed, swap jth and pivot element
		int temp = arr[left];
		arr[left] = arr[j];
		arr[j] = temp;
		//4. sort left and right side of pivot
		quickSort(arr, left, j-1);
		quickSort(arr, j+1, right);
	}

	public static void main(String[] args) {
			
		int arr[] = {66, 33, 99, 11, 77, 22, 55, 66, 88};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));

		quickSort(arr, 0, arr.length-1);
		
		System.out.println("Array after sort : " + Arrays.toString(arr));

	}

}
