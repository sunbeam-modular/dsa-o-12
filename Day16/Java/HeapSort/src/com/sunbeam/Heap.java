package com.sunbeam;

public class Heap {
	private int size;
	
	public Heap(int length) {
		size = length;
	}
	
	public void heapify(int arr[]) {
		// start from first non leaf node (parent) from right side
		for(int i = size / 2 ;  i >= 1 ; i--) {
			int temp = arr[i];
			//4. find appropriate position for last element
			int ci = i * 2;
			while(ci <= size) {
				//4.1 find index of maximum child
				if((ci + 1) <= size && arr[ci + 1] > arr[ci])
					ci = ci + 1;
				//4.2 if parent is already greater than max child then break
				if(temp > arr[ci])
					break;
				//4.3 promote maximum child to parent place
				arr[ci/2] = arr[ci];
				//4.4 update ci to get further childs
				ci = ci * 2;
			}
			//put temp at the parent place
			arr[ci/2] = temp;
		}
		
	}
	
	public void deleteHeap(int arr[]) {
		//1. delete all elements from heap one by one
		for(int i = 1 ; i < arr.length ; i++) {
			//2. swap root(max) element with last element of heap
			int temp = arr[size];
			arr[size] = arr[1];

			//3. reduce heap size by one
			size--;
			
			//4. find appropriate position for last element
			int ci = 1 * 2;
			while(ci <= size) {
				//4.1 find index of maximum child
				if((ci + 1) <= size && arr[ci + 1] > arr[ci])
					ci = ci + 1;
				//4.2 if parent is already greater than max child then break
				if(temp > arr[ci])
					break;
				//4.3 promote maximum child to parent place
				arr[ci/2] = arr[ci];
				//4.4 update ci to get further childs
				ci = ci * 2;
			}
			//put temp at the parent place
			arr[ci/2] = temp;
		}
	}
	
	public void heapSort(int arr[]) {
		//1. convert array into heap(max)
		heapify(arr);
		//2. delete all elements of heap one by one
		deleteHeap(arr);
	}
}
