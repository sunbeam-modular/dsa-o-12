package com.sunbeam;

import java.util.Arrays;

public class SelectionSortMain {
	
	public static void selectionSort(int arr[], int N) {
		//1. select one position of the array
		for(int i = 0 ; i < N-1 ; i++) {
			//2. find smallest element out of remaining element
			int minIndex = i;
			for(int j = i + 1 ; j < N ; j++) {
				if(arr[j] < arr[minIndex])
					minIndex = j;
			}
			//3. swap selected element and smallest element
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}//4. repeat above steps  untill array is sorted
	}

	public static void main(String[] args) {
		int arr[] = {40, 10, 50, 20, 60, 30};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));

		selectionSort(arr, arr.length);
		
		System.out.println("Array after sort : " + Arrays.toString(arr));
	}

}
