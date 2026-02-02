

def bubbleSort(arr):
	# run n-1 passes for bubble sort
	for i in range(len(arr)-1):
		# compare all consecutive elements of the array
		for j in range(len(arr)-1):
			# if j th element > j+1 th element, swap them
			if(arr[j] > arr[j+1]):
				temp = arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp


def bubbleSortImproved(arr):
	for i in range(len(arr)-1):
		for j in range(len(arr)-1-i):
			if(arr[j] > arr[j+1]):
				temp = arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp


def bubbleSortFurtherImproved(arr):
	for i in range(len(arr)-1):
		flag = True
		for j in range(len(arr)-1-i):
			if(arr[j] > arr[j+1]):
				temp = arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp
		if not flag:
			break


def insertionSort(arr):
	for i in range(1, len(arr)):
		temp = arr[i]
		j = i - 1
		while j >= 0 and arr[j] > temp:
			arr[j+1] = arr[j]
			j = j - 1
		arr[j+1] = temp;


def main():
	arr = [ 5, 4, 2, 6, 3, 1 ]
	bubbleSort(arr)
	print(arr)

	arr = [ 5, 4, 2, 6, 3, 1 ]
	bubbleSortImproved(arr)
	print(arr)

	arr = [ 1, 2, 3, 4, 5, 6 ]
	bubbleSortFurtherImproved(arr)
	print(arr)

	arr = [ 5, 4, 2, 6, 3, 1 ]
	insertionSort(arr)
	print(arr)


if __name__ == "__main__":
	main()
