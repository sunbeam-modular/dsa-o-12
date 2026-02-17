package com.sunbeam;

import java.util.Arrays;

public class MergeSortMain {
	
	public static void mergeSort(int arr[], int left, int right) {
		//0. stop if array has single element
		if(left == right)
			return;
		//1. divide array into two parts
		int mid = (left + right) / 2;
		//2. sort both partitions individually
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		//3. merge sorted partitions into temp array
		int size = right - left + 1;
		int temp[] = new int[size];
		int i = left, j = mid + 1, k = 0;
		while(i <= mid && j <= right) {
			// if ith element is smaller than jth, put ith element into temp
			if(arr[i] < arr[j]) {
				temp[k] = arr[i];
				i++;
				k++;
			}
			// if jth element is smaller than ith, put jth element into temp
			else {
				temp[k] = arr[j];
				j++;
				k++;
			}
		}
		// if left partition is finished, then add remaining elements from right partition
		while(j <= right) {
			temp[k] = arr[j];
			j++;
			k++;
		}
		// if right partition is finished, then add remaining elements from left partition
		while(i <= mid) {
			temp[k] = arr[i];
			i++;
			k++;
		}
		//4. overwrite temp array into original
		for(i = 0 ; i < size ; i++)
			arr[left + i] = temp[i];
	}

	public static void main(String[] args) {
		
		int arr[] = {6, 1, 9, 7, 3, 8, 2, 4, 5};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));

		mergeSort(arr, 0, arr.length-1);
		
		System.out.println("Array after sort : " + Arrays.toString(arr));
	}

}
