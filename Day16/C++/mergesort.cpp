#include <iostream>
using namespace std;

/*
void merge_sort(int a[], int left, int right) {
	//0. if single ele or no ele in partition, return
	if (left >= right)
		return;
	//1. divide array in two equal partitions
	int m = (left + right) / 2;
	//2. apply merge sort to left partition and right partition
	merge_sort(a, left, m);
	merge_sort(a, m + 1, right);
	//3. merge two sorted partitions into temp array
	int n = right - left + 1;
	int *temp = new int[n]; // malloc(n * sizeof(int));
	int i = left, j = m + 1, k = 0;
	while (i <= m && j <= right) {
		// compare left part ele & right part ele,
		// whichiever is smaller append into temp array.
		if (a[i] < a[j]) {
			temp[k] = a[i];
			i++;
			k++;
		}
		else {
			temp[k] = a[j];
			j++;
			k++;
		}
	} // until any one part is completed.
	// if right part is completed, copy eles of left part
	while (i <= m) {
		temp[k] = a[i];
		i++;
		k++;
	}
	// if left part is completed, copy eles of right part
	while (j <= right) {
		temp[k] = a[j];
		j++;
		k++;
	}
	//4. overwrite temp array back to original array (left onwards)
	for (i = 0; i < n; i++)
		a[left+i] = temp[i];
	delete[] temp;
}
*/


void merge_sort(int a[], int left, int right) {
	//0. if single ele or no ele in partition, return
	if (left >= right)
		return;
	//1. divide array in two equal partitions
	int m = (left + right) / 2;
	//2. apply merge sort to left partition and right partition
	merge_sort(a, left, m);
	merge_sort(a, m + 1, right);
	//3. merge two sorted partitions into temp array
	int n = right - left + 1;
	int* temp = new int[n]; // malloc(n * sizeof(int));
	int i = left, j = m + 1, k = 0;
	while (i <= m && j <= right)
		temp[k++] = a[i] < a[j] ? a[i++] : a[j++];
	while (i <= m)
		temp[k++] = a[i++];
	while (j <= right)
		temp[k++] = a[j++];
	//4. overwrite temp array back to original array (left onwards)
	for (i = 0; i < n; i++)
		a[left + i] = temp[i];
	delete[] temp;
}

int main() {
	int arr[9] = { 6, 3, 9, 1, 7, 2, 8, 4, 5 };
	merge_sort(arr, 0, 9 - 1);
	for (int i = 0; i < 9; i++)
		cout << arr[i] << ", ";
	cout << endl;
	return 0;
}
