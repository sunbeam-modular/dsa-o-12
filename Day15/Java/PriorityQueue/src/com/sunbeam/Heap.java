package com.sunbeam;

public class Heap {
	private int arr[];
	private int size;
	
	public Heap(int length) {
		this.size = 0;
		arr = new int[length + 1];
	}
	
	public void addHeap(int value) {
		//1. increase size of heap by one
		size++;
		//2. add new value at first empty index from left side
		arr[size] = value;
		//3. adjust position of newly added value
		int ci = size;
		int pi = ci / 2;
		while(pi > 0) {
			//3.1 if parent is already greater then break
			if(arr[pi] > arr[ci])
				break;
			//3.2 if parent is smaller than child then swap
			int temp = arr[pi];
			arr[pi] = arr[ci];
			arr[ci] = temp;
			//3.3  update parent and child index
			ci = pi;
			pi = ci / 2;
		}
	}
	
	public int deleteHeap() {
		//1. take back up of root element
		int max = arr[1];
		//2. promote last element of heap to root place
		arr[1] = arr[size];
		//3. reduce heap size by one
		size--;
		//4. adjust position  of promoted element 
		int pi = 1;
		int ci = pi * 2;
		while(ci <= size) {
			//4.1 find index of maximum child
			if((ci + 1) <= size && arr[ci + 1] > arr[ci])
				ci = ci + 1;
			//4.2 if parent is already greater than max child then break
			if(arr[pi] > arr[ci])
				break;
			//4.3 if parent is smaller than max child
			int temp = arr[pi];
			arr[pi] = arr[ci];
			arr[ci] = temp;
			//4.4 update parent and child index
			pi = ci;
			ci = pi * 2;
		}		
		//5.  return max/deleted element
		return max;
	}
	
	public int getRoot() {
		return arr[1];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == arr.length - 1;
	}
	
	
}

















