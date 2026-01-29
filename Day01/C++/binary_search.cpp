#include <iostream>
using namespace std;

int binary_search(int a[], int n, int key);

int main() {
	int arr[9] = { 11, 22, 33 ,44 ,55 , 66, 77, 88, 99 };
	int i, index, key;
	cout << "enter element to find: ";
	cin >> key;
	index = binary_search(arr, 9, key);
	if (index == -1)
		cout << "element not found" << endl;
	else
		cout << "element found at index " << index << endl;
	return 0;
}

int binary_search(int a[], int n, int key) {
	int l, r, m;
	l = 0;
	r = n - 1;
	while (l <= r) {
		m = (l + r) / 2;
		if (key == a[m])
			return m;
		if (key < a[m])
			r = m - 1;
		else // key > a[m]
			l = m + 1;
	}
	return -1;
}
