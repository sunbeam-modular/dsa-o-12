
class LinearQueue:

	def __init__(self, size):
		self.arr = [0] * size
		self.front = -1
		self.rear = -1

	def push(self, val):
		self.rear = self.rear + 1
		self.arr[self.rear] = val

	def pop(self):
		self.front = self.front + 1

	def peek(self):
		return self.arr[self.front + 1]

	def isEmpty(self):
		return self.front == self.rear

	def isFull(self):
		return self.rear == len(self.arr)-1


def main():
	q = LinearQueue(6)
	q.push(11)
	q.push(22)
	q.push(33)
	q.push(44)

	val = q.peek()
	print("Element at front = " + str(val) + "\n")

	while not q.isEmpty():
		val = q.peek()
		q.pop()
		print("Popped : " + str(val))

if __name__ == "__main__":
	main()

