#include <iostream>
using namespace std;

void quick_sort(int a[], int left, int right) {
	//8. nothing to sort if left >= right i.e. invalid part or part contains only one ele
	if (left >= right)
		return;
	//0. consider a[left] as pivot
	int i=left, j=right;
	while(i < j)
	{
		//1. from left find ele > pivot (i.e. ith)
		while (i <= right && a[i] <= a[left])
			i++;
		//2. from right find ele <= pivot (i.e. jth)
		while (a[j] > a[left])
			j--;
		//3. if i & j are not crossed, swap ith ele with jth ele
		if (i < j)
			swap(a[i], a[j]);
	}//4. repeat 1-3 until i & j are not crossed

	//5. swap jth ele with pivot
	swap(a[left], a[j]);

	//6. apply quick sort to left partition i.e. left to j-1
	quick_sort(a, left, j - 1);
	//7. apply quick sort to right partition i.e. j+1 to right
	quick_sort(a, j + 1, right);
}

int main() {
	int arr[9] = { 66, 33, 99, 11, 77, 22, 55, 66, 88 };
	quick_sort(arr, 0, 9 - 1);
	for (int i = 0; i < 9; i++)
		cout << arr[i] << ", ";
	cout << endl;
	return 0;
}