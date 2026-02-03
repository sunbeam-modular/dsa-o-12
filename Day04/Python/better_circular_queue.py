
class BetterCircularQueue:

	def __init__(self, size):
		self.arr = [0] * size
		self.front = -1
		self.rear = -1
		self.count = 0

	def push(self, val):
		self.rear = (self.rear + 1) % len(self.arr)
		self.arr[self.rear] = val
		self.count = self.count + 1

	def pop(self):
		self.front = (self.front + 1) % len(self.arr)
		if self.front == self.rear:
			self.front = -1
			self.rear = -1
		self.count = self.count - 1

	def peek(self):
		index = (self.front + 1) % len(self.arr)
		return self.arr[self.front + 1]

	def isEmpty(self):
		return self.count == 0

	def isFull(self):
		return self.count == len(self.arr)


def main():
	q = BetterCircularQueue(6)
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

