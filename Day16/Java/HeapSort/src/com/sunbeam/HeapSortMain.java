package com.sunbeam;

import java.util.Arrays;

public class HeapSortMain {

	public static void main(String[] args) {
		int arr[] = {0, 25, 10, 20, 15, 50, 30, 40, 60, 100};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));
		
		Heap h = new Heap(arr.length-1);
		h.heapSort(arr);

		System.out.println("Array after sort : " + Arrays.toString(arr));
	}

}
