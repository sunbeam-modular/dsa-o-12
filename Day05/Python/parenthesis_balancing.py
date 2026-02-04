
class Stack:
	def __init__(self):
		self.arr = list()

	def push(self, val):
		self.arr.append(val)

	def pop(self):
		return self.arr.pop()

	def peek(self):
		return self.arr[len(self.arr)-1]

	def isEmpty(self):
		return len(self.arr) == 0

def isParenthesisBalanced(expr):
	s = Stack()
	open = "([{<"
	close = ")]}>"
	for i in range(len(expr)):
		sym = expr[i]
		index = open.find(sym)
		if index != -1: # if opening parenthesis
			s.push(sym)
		else:
			index = close.find(sym)
			if index != -1:# if closing parenthesis
				if s.isEmpty(): # not balanced -- extra closing parenthesis
					return False
				top = s.pop()
				if open.find(top) != index: # not balanced -- opening != closing
					return False
	if not s.isEmpty(): # not balanced -- extra opening parenthesis
		return False
	return True # balanced


def main():
	expr = "5+([9-4]*(8-{6/2}))"
	if isParenthesisBalanced(expr):
		print("Parenthesis are balanced.")
	else:
		print("Parenthesis are not balanced.")


if __name__ == "__main__":
	main()

