

class MaxHeap:
	def __init__(self, a):
		self.arr = a
		self.size = len(a) - 1

	def makeHeap(self):
		# heap size is number of elements in heap (1 to length)
		self.size = len(self.arr) - 1
		# from middle node to first node check if it is greater than each child node
		for i in range(int(self.size/2), 0, -1):
			# take ith element into temp variable & also take child index of ith node
			temp = self.arr[i]
			ci = i * 2
			# find appropriate location for temp variable.
			while ci <= self.size:
				# check if right child is available & is greater than left child
				if (ci+1) <= self.size and self.arr[ci+1] > self.arr[ci]:
					ci = ci + 1
				# if temp is greater than max child (ci)
				if temp > self.arr[ci]:
					break
				# if child is greater than temp, promote child to its parent location
				self.arr[int(ci / 2)] = self.arr[ci]
				# check if its child is valid location for temp?
				ci = ci * 2
			# insert temp as parent of ci
			self.arr[int(ci / 2)] = temp

	def delMax(self):
		# max node is always self.arr[1]
		val = self.arr[1]
		# take last node into temp variable
		temp = self.arr[self.size]
		# decrement self.size of heap by 1
		self.size = self.size - 1
		# find approrpiate position for temp.
		ci = 2 # child of self.arr[1]
		while ci <= self.size:
			# check if right child is available & is greater than left child
			if (ci+1) <= self.size and self.arr[ci+1] > self.arr[ci]:
				ci = ci + 1
			# if temp is greater than max child (ci)
			if temp > self.arr[ci]:
				break
			# if child is greater than temp, promote child to its parent location
			self.arr[int(ci / 2)] = self.arr[ci]
			# check if its child is valid location for temp?
			ci = ci * 2
		# insert temp as parent of ci
		self.arr[int(ci / 2)] = temp
		# return deleted node
		return val

	def print(self):
		print("Heap: ", end='')
		for i in range(1, self.size+1):
			print(self.arr[i], end=", ")
		print()


# O(k log n)
def kthHighestElement(arr, k):
	h = MaxHeap(arr)
	h.makeHeap()
	val = 0
	for i in range(1, k+1):
		val = h.delMax()
	return val

# O(n log n)
def heapSort(arr):
	h = MaxHeap(arr)
	h.makeHeap()
	for i in range(1, len(arr)):
		val = h.delMax()
		arr[len(arr)-i] = val
	print()


def main():
	arr = [0, 20, 12, 35, 15, 10, 80, 30, 17, 2, 1]
	print(arr)
	heapSort(arr)
	print(arr)

	# h = MaxHeap(arr)
	# h.print()
	# h.makeHeap()
	# h.print()
	# val = h.delMax()
	# print("deleted element: " + str(val))
	# h.print()
	# val = h.delMax()
	# print("deleted element: " + str(val))
	# h.print()


if __name__ == "__main__":
	main()
