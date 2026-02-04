
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


def calc(a, b, op):
	if op == '$':
		return a ** b
	elif op == '*':
		return a * b
	elif op == '/':
		return a / b
	elif op == '%':
		return a % b
	elif op == '+':
		return a + b
	elif op == '-':
		return a - b
	return 0


def postfixEvaluation(postfix):
	s = Stack()
	#1. access symbols from postfix from left to right.
	for i in range(0, len(postfix)):
		sym = postfix[i]
		#2. if symbol is operand, push it on the stack.
		if sym.isdigit():
			s.push(int(sym))
		#3. if symbol is operator, pop two operands from stack, calculate result & push on stack.
		else:
			b = s.pop()
			a = s.pop()
			res = calc(a, b, sym)
			s.push(res)
	#4. repeat until all symbols from postfix are finished.
	return s.pop() #5. pop the final result from stack and return it.


def prefixEvaluation(prefix):
	s = Stack()
	#1. access symbols from prefix from right to left.
	for i in range(len(prefix)-1, -1, -1):
		sym = prefix[i]
		#2. if symbol is operand, push it on the stack.
		if sym.isdigit():
			s.push(int(sym))
		#3. if symbol is operator, pop two operands from stack, calculate result & push on stack.
		else:
			a = s.pop()
			b = s.pop()
			res = calc(a, b, sym)
			s.push(res)
	#4. repeat until all symbols from prefix are finished.
	return s.pop() #5. pop the final result from stack and return it.


def postfixToInfix(postfix):
	s = Stack()
	for i in range(0, len(postfix)):
		sym = postfix[i]
		if sym.isdigit():
			s.push(str(sym))
		else:
			b = s.pop()
			a = s.pop()
			s.push('(' + a + sym + b + ')')
	return s.pop()


def prefixToPostfix(prefix):
	s = Stack()
	for i in range(len(prefix)-1, -1, -1):
		sym = prefix[i]
		if sym.isdigit():
			s.push(str(sym))
		else:
			a = s.pop()
			b = s.pop()
			s.push(a + b + sym)
	return s.pop()


def main():
	postfix = "59+4862/-*-173-$+"
	res = postfixEvaluation(postfix)
	print("Postfix  Result: " + str(res))
	prefix = "+-+59*4-8/62$1-73"
	res = prefixEvaluation(prefix)
	print("Prefix Result: " + str(res))
	infix = postfixToInfix(postfix)
	print("Infix Expr: " + infix)
	convertedPostfix = prefixToPostfix(prefix)
	print("Postfix Expr: " + convertedPostfix)


if __name__ == "__main__":
	main()

