

def mergeSort(a, left, right):
	# 0. if single element in partition or invalid partition, return
	if left >= right:
		return
	# 1. divide array into two equal partitions
	mid = int((left + right) / 2)
	# 2. apply merge sort to left partition - left to mid
	mergeSort(a, left, mid)
	# 3. apply merge sort to right partition - mid+1 to right
	mergeSort(a, mid+1, right)
	# 4. create temp array to accomodate both partitions
	temp = [0]*(right - left + 1)
	# 5. merge both sorted partitions into temp array
	i = left
	j = mid+1
	k = 0
	while i <= mid and j <= right:
		if a[i] < a[j]:
			temp[k] = a[i]
			i = i + 1
			k = k + 1
		else:
			temp[k] = a[j]
			j = j + 1
			k = k + 1
	while i <= mid:
		temp[k] = a[i]
		i = i + 1
		k = k + 1
	while j <= right:
		temp[k] = a[j]
		j = j + 1
		k = k + 1
	# 6. overwrite temp array back to the main array
	for i in range(0, len(temp)):
		a[left+i] = temp[i]


def main():
	arr = [20, 12, 35, 15, 10, 80, 30, 17, 2, 1]
	print(arr)
	mergeSort(arr, 0, len(arr) - 1)
	print(arr)



if __name__ == "__main__":
	main()
