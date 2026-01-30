#include <iostream>
using namespace std;

void selection_sort(int a[], int n) {
	int i, j;
	for (i = 0; i < n - 1; i++) {
		for (j = i + 1; j < n; j++) {
			if (a[i] > a[j])
				swap(a[i], a[j]);
		}
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
	int arr[6] = {44, 11, 55, 22, 66, 33};
	print_array(arr, 6);
	selection_sort(arr, 6);
	print_array(arr, 6);
	return 0;
}

