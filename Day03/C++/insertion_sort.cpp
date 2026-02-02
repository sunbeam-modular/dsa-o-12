#include <iostream>
using namespace std;

void insertion_sort(int a[], int n) {
	int i, j, temp;
	for (i = 1; i < n; i++) {
		temp = a[i];
		for (j = i - 1; j >= 0 && a[j] > temp; j--)
			a[j + 1] = a[j];
		a[j + 1] = temp;
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
	int arr[6] = { 55, 44, 22, 66, 11, 33 };
	print_array(arr, 6);
	insertion_sort(arr, 6);
	print_array(arr, 6);
	return 0;
}

