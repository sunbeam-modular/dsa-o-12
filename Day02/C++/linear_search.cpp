#include <iostream>
using namespace std;

int linear_search(int a[], int n, int key);

int main() {
	int arr[9];
	int i, index, key;
	cout << "enter 9 elements: " << endl;
	for (i = 0; i < 9; i++)
		cin >> arr[i];
	cout << "enter element to find: ";
	cin >> key;
	index = linear_search(arr, 9, key);
	if (index == -1)
		cout << "element not found" << endl;
	else
		cout << "element found at index " << index << endl;
	return 0;
}

int linear_search(int a[], int n, int key) {
	int i;
	for (i = 0; i < n; i++) {
		if (a[i] == key)
			return i;
	}
	return -1;
}
