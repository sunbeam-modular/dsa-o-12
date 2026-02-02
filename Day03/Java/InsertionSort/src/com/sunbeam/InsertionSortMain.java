package com.sunbeam;

import java.util.Arrays;

public class InsertionSortMain {
	
	public static void insertionSort(int arr[], int N) {
		//1. pick one element of array (start from 2nd index)
		for(int i = 1 ; i < N ; i++) {
			int temp = arr[i];
			int j;
			//2. compare picked element with all its left neighbors one by one
			for(j = i - 1 ; j >= 0 ; j--) {
				//3. if left neighbor is greater than picked element then move it one position ahead
				if(arr[j] > temp)
					arr[j+1] = arr[j];
				else
					break;
			}
			//4. insert picked element at its appropriate position
			arr[j+1] = temp;
		}
	}

	public static void main(String[] args) {
		int arr[] = {50, 40, 20, 60, 10, 30};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));

		insertionSort(arr, arr.length);
		
		System.out.println("Array after sort : " + Arrays.toString(arr));
	}

}
