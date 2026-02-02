#include <iostream>
using namespace std;

/*
void bubble_sort(int a[], int n) {
	int i, j;
	// repeat for n-1 times.
	for (i = 1; i < n; i++) {
		// compare consecutive eles.
		for (j = 0; j < n - 1; j++) {
			// if left ele > right ele, then swap them
			if (a[j] > a[j + 1])
				swap(a[j], a[j + 1]);
		}
	}
}
*/

/*
// improved bubble sort
void bubble_sort(int a[], int n) {
	int i, j;
	// repeat for n-1 times.
	for (i = 1; i < n; i++) {
		// compare consecutive eles.
		for (j = 0; j < n - i; j++) {
			// if left ele > right ele, then swap them
			if (a[j] > a[j + 1])
				swap(a[j], a[j + 1]);
		}
	}
}
*/

// further improved bubble sort ??
void bubble_sort(int a[], int n) {
	int i, j, swap_flag;
	// repeat for n-1 times.
	for (i = 1; i < n; i++) {
		swap_flag = 0;
		// compare consecutive eles.
		for (j = 0; j < n - i; j++) {
			// if left ele > right ele, then swap them
			if (a[j] > a[j + 1]) {
				swap(a[j], a[j + 1]);
				swap_flag = 1;
			}
		}
		// if no swaps in last pass, stop (bcoz array is already sorted)
		if (swap_flag == 0)
			break;
	}
}

void print_array(int a[], int n) {
	int i;
	cout << "ARR: ";
	for (i = 0; i < n; i++)
		cout << a[i] << ", ";
	cout << endl;
}

int main() {
	int arr[6] = { 44, 11, 55, 22, 66, 33 };
	print_array(arr, 6);
	bubble_sort(arr, 6);
	print_array(arr, 6);
	return 0;
}

