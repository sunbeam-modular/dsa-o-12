
def quickSort(a, left, right):
	# 0. if single element or invalid partition, stop.
	if left >= right:
		return
	# 1. consider left-most element as pivot
	i = left
	j = right
	pivot = a[left]
	while i < j:
		# 2. from left find element greater than pivot - ith element
		while i <= right and a[i] <= pivot:
			i = i + 1
		# 3. from right find element less or equal to pivot - jth element
		while j >= left and a[j] > pivot:
			j = j - 1
		# 4. if i & j not crossed, then swap ith element with jth element
		if i < j :
			temp = a[i]
			a[i] = a[j]
			a[j] = temp
		# 5. repeat steps 2-4 until, i & j cross each other
	#  6. swap jth element with pivot=a[left]
	temp = a[left]
	a[left] = a[j]
	a[j] = temp
	#  7. apply quick sort to the left partition
	quickSort(a, left, j - 1)
	#  8. apply quick sort to the right partition
	quickSort(a, j + 1, right)


def main():
	arr = [20, 12, 35, 15, 10, 80, 30, 17, 2, 1]
	print(arr)
	quickSort(arr, 0, len(arr)-1)
	print(arr)


if __name__ == "__main__":
	main()
