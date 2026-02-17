#include <iostream>
using namespace std;

class maxheap {
private:
	int *arr;
	int size;
	int length;
public:
	maxheap(int* a, int len) {
		arr = a;
		length = len;
		size = 0;
	}
	int get_size() {
		return size;
	}
	void make_heap() {
		// size of heap - length of array - 1
		size = length - 1;
		// from mid node to first node, check if they are at proper pos
		for (int i = size / 2; i >= 1; i--) {
			// take ith ele in temp
			int ci = i * 2, temp = arr[i];
			// find appropriate pos for temp
			while (ci <= size) {
				// if right child of i is present & greater than its left child
				if ((ci + 1) <= size && arr[ci + 1] > arr[ci])
					ci++;
				// if temp is greater than its greatest child, temp is at right pos
				if (temp > arr[ci])
					break;
				// shift child to its parent pos & check for its child in next itr
				arr[ci / 2] = arr[ci];
				ci = ci * 2;
			}
			// put temp at ci parent pos
			arr[ci / 2] = temp;
		}
	}

	int del() {
		// delete max & return
		int max = arr[1];
		// reduce the size of heap & find pos for last ele (temp)
		int temp = arr[size];
		size--;

		int i = 1, ci = i * 2;
		// find appropriate pos for temp
		while (ci <= size) {
			// if right child of i is present & greater than its left child
			if ((ci + 1) <= size && arr[ci + 1] > arr[ci])
				ci++;
			// if temp is greater than its greatest child, temp is at right pos
			if (temp > arr[ci])
				break;
			// shift child to its parent pos & check for its child in next itr
			arr[ci / 2] = arr[ci];
			ci = ci * 2;
		}
		// put temp at ci parent pos
		arr[ci / 2] = temp;
		return max;
	}

	void insert(int temp) {
		// if heap is full, return
		if (size == length - 1)
			return;
		// find place for temp starting from last pos (to root)
		int ci = ++size;
		while (ci != 1 && temp > arr[ci / 2]) {
			// move parent down & trav to parent
			int i = ci / 2;
			arr[ci] = arr[i];
			ci = i;
		}
		// add root to the child
		arr[ci] = temp;
	}

	void display() {
		int i;
		cout << "heap: ";
		for (i = 1; i <= size; i++)
			cout << arr[i] << ", ";
		cout << endl;
	}
};

void display_arr(int a[], int n) {
	int i;
	cout << "arr : ";
	for (i = 1; i < n; i++)
		cout << a[i] << ", ";
	cout << endl;
}

void heap_sort(int* a, int len) {
	maxheap h(a, len);
	h.make_heap();
	int size = len - 1;
	for (int i = size; i >= 1; i--) {
		int max = h.del();
		a[size] = max;
		size--;
	}
}

int main() {
	int arr[] = { 0, 20, 12, 35, 15, 10, 80, 30, 17, 2, 1 };
	
	/*
	maxheap h(arr, 11);
	display_arr(arr, 11);
	h.make_heap();
	h.display();
	int ele = h.del();
	cout << "max deleted: " << ele << endl;
	h.display();
	ele = h.del();
	cout << "max deleted: " << ele << endl;
	h.display();
	ele = h.del();
	cout << "max deleted: " << ele << endl;
	h.display();
	*/

	/*
	display_arr(arr, 11);
	heap_sort(arr, 11);
	display_arr(arr, 11);
	*/

	int harr[11] = {0};
	maxheap h(harr, 11);
	h.insert(20);
	h.insert(12);
	h.insert(35);
	h.insert(15);
	h.insert(10);
	h.insert(80);
	h.insert(17);
	h.insert(2);
	h.insert(1);
	h.display();
	while (h.get_size() > 0) {
		cout << "del : " << h.del() << endl;
	}
	return 0;
}