package com.sunbeam;

import java.util.Arrays;

public class BubbleSortMain {
	
	public static void bubbleSort(int arr[], int N) {
		int passes = 0, comps = 0;
		//1. repeat N-1 times
		for(int i = 1 ; i < N ; i++) {
			passes++;
			//2. compare all pairs one by one
			for(int j = 0 ; j < N-1 ; j++) {
				comps++;
				//3. if left element is greater than right element  then swap
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("passes : " + passes);
		System.out.println("comps : " + comps);
	}
	
	public static void improvedBubbleSort(int arr[], int N) {
		int passes = 0, comps = 0;
		//1. repeat N-1 times
		for(int i = 1 ; i < N ; i++) {
			passes++;
			//2. compare all pairs one by one
			for(int j = 0 ; j < N-i ; j++) {
				comps++;
				//3. if left element is greater than right element  then swap
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("passes : " + passes);
		System.out.println("comps : " + comps);
	}
	
	public static void furtherImprovedBubbleSort(int arr[], int N) {
		int passes = 0, comps = 0,flag;
		//1. repeat N-1 times
		for(int i = 1 ; i < N ; i++) {
			passes++;
			flag = 0;
			//2. compare all pairs one by one
			for(int j = 0 ; j < N-i ; j++) {
				comps++;
				//3. if left element is greater than right element  then swap
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					flag = 1;
				}
			}
			if(flag == 0)
				break;
		}
		System.out.println("passes : " + passes);
		System.out.println("comps : " + comps);
	}

	public static void main(String[] args) {
		//int arr[] = {30, 20, 60, 50, 40, 10};
		int arr[] = {10, 20, 30, 40, 50, 60};
		
		System.out.println("Array before sort : " + Arrays.toString(arr));

		//bubbleSort(arr, arr.length);
		//improvedBubbleSort(arr, arr.length);
		furtherImprovedBubbleSort(arr, arr.length);
		
		System.out.println("Array after sort : " + Arrays.toString(arr));
	}

}
