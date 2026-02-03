
class CircularQueue:

	def __init__(self, size):
		self.arr = [0] * size
		self.front = -1
		self.rear = -1

	def push(self, val):
		self.rear = (self.rear + 1) % len(self.arr)
		self.arr[self.rear] = val

	def pop(self):
		self.front = (self.front + 1) % len(self.arr)
		if self.front == self.rear:
			self.front = -1
			self.rear = -1

	def peek(self):
		index = (self.front + 1) % len(self.arr)
		return self.arr[self.front + 1]

	def isEmpty(self):
		return self.front == self.rear and self.front == -1

	def isFull(self):
		return (self.front == -1 and self.rear == len(self.arr)-1) or self.front == self.rear and self.front != -1


def main():
	q = CircularQueue(6)
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

