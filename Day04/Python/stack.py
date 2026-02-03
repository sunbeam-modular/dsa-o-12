
class Stack:

	def __init__(self, size):
		self.arr = [0] * size
		self.top = -1

	def push(self, val):
		self.top = self.top + 1
		self.arr[self.top] = val

	def pop(self):
		self.top = self.top - 1

	def peek(self):
		return self.arr[self.top]

	def isEmpty(self):
		return self.top == -1

	def isFull(self):
		return self.top == len(self.arr)-1


def main():
	s = Stack(6)
	s.push(11)
	s.push(22)
	s.push(33)
	s.push(44)

	val = s.peek()
	print("Element on top = " + str(val) + "\n")

	while not s.isEmpty():
		val = s.peek()
		s.pop()
		print("Popped : " + str(val))


if __name__ == "__main__":
	main()

