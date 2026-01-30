#include <iostream>
using namespace std;

int bin_search(int a[], int l, int r, int key) {
	int m, i;
	if (l > r)
		return -1;
	m = (l + r) / 2;
	if (key == a[m])
		return m;
	if (key < a[m])
		i = bin_search(a, l, m - 1, key);
	else
		i = bin_search(a, m + 1, r, key);
	return i;
}

int main() {
	int arr[] = {11, 22, 33, 44, 55, 66, 77, 88, 99};
	int index;
	index = bin_search(arr, 0, 9 - 1, 30);
	if (index == -1)
		cout << "Element not found." << endl;
	else
		cout << "Element found at : " << index << endl;
	return 0;
}
