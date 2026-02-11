
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


class Node:
	def __init__(self, val):
		self.data = val
		self.left = None
		self.right = None
		self.visited = False

	def toString(self):
		return "Node [data=" + str(self.data) + "]"


class BinSearchTree:
	def __init__(self):
		self.root = None

	def add(self, val):
		newNode = Node(val)
		if self.root is None:
			self.root = newNode
		else:
			trav = self.root
			while True:
				if val < trav.data:
					if trav.left is None:
						trav.left = newNode
						break
					else:
						trav = trav.left
				else: # if(val >= trav.data)
					if trav.right == None:
						trav.right = newNode
						break
					else:
						trav = trav.right

	def recAdd(self, trav, val):
		if val < trav.data:
			if trav.left is None:
				trav.left = Node(val)
			else:
				self.recAdd(trav.left, val)
		else:
			if trav.right is None:
				trav.right = Node(val)
			else:
				self.recAdd(trav.right, val)

	def recurAdd(self, val):
		if self.root is None:
			self.root = Node(val)
		else:
			self.recAdd(self.root, val)

	def recPreOrder(self, trav):
		if trav is None:
			return
		print(str(trav.data) + ", ", end='')
		self.recPreOrder(trav.left)
		self.recPreOrder(trav.right)

	def recurPreOrder(self):
		print("Pre: ", end='')
		self.recPreOrder(self.root)
		print()

	def recInOrder(self, trav):
		if trav is None:
			return
		self.recInOrder(trav.left)
		print(str(trav.data) + ", ", end='')
		self.recInOrder(trav.right)
	
	def recurInOrder(self):
		print("In : ", end='')
		self.recInOrder(self.root)
		print()

	def recPostOrder(self, trav):
		if trav is None:
			return
		self.recPostOrder(trav.left)
		self.recPostOrder(trav.right)
		print(str(trav.data) + ", ", end='')

	def recurPostOrder(self):
		print("Post:", end='')
		self.recPostOrder(self.root)
		print()
	
	def recFind(self, trav, val):
		if trav is None:
			return None
		if val == trav.data:
			return trav
		if val < trav.data:
			return self.recFind(trav.left, val)
		else:
			return self.recFind(trav.right, val)

	def recurFind(self, val):
		return self.recFind(self.root, val)
	
	def find(self, val):
		trav = self.root
		while trav is not None:
			if val == trav.data:
				return trav
			if val < trav.data:
				trav = trav.left
			else: # if(val < trav.data)
				trav = trav.right
		return None

	def preOrder(self):
		s = Stack()
		# 1. start traversing from self.root
		trav = self.root
		print("PRE : ", end='')
		while trav is not None or not s.isEmpty():
			while trav is not None:
				# 2. visit trav
				print(trav.data, end=", ")
				# 3. if trav has right, push trav.right on stack
				if trav.right is not None:
					s.push(trav.right)
				# 4. go to left of trav
				trav = trav.left
			# 5. repeat 2-5 until trav is None
			if not s.isEmpty():
				trav = s.pop() # 6. pop Node from stack into trav
		# 7. repeat 2-6, until trav is None or stack is isEmpty
		print()

	def inOrder(self):
		s = Stack()
		trav = self.root
		print("IN  : ", end='')
		while trav is not None or not s.isEmpty():
			while trav is not None:
				s.push(trav)
				trav = trav.left
			if not s.isEmpty():
				trav = s.pop()
				print(trav.data, end=", ")
				trav = trav.right
		print()

	def postOrder(self):
		s = Stack()
		# start trav from self.root
		trav = self.root
		print("POST: ", end='')
		# while trav is not None or stack is not isEmpty
		while trav is not None or not s.isEmpty():
			# until None is reached
			while trav is not None:
				# push trav on stack
				s.push(trav)
				# go to trav's left
				trav = trav.left
			# if stack is not isEmpty
			if not s.isEmpty():
				# pop Node from stack into trav
				trav = s.pop()
				# if trav's right is present & visited
				if trav.right is None or trav.right.visited == True:
					# visit trav & mark it as visited
					print(trav.data, end=", ")
					trav.visited = True
					# make trav None (so that next Node will be popped from stack)
					trav = None
				# otherwise
				else:
					# push Node on stack
					s.push(trav)
					# go to its right
					trav = trav.right
		print()

	def findWithParent(self, val):
		parent = None
		trav = self.root
		while trav is not None:
			if val == trav.data:
				return (trav, parent)
			parent = trav
			if val < trav.data:
				trav = trav.left
			else: # if(val < trav.data)
				trav = trav.right
		return (None, None)


def main():
	bst = BinSearchTree()
	bst.add(50)
	bst.add(30)
	bst.add(10)
	bst.add(90)
	bst.add(100)
	bst.add(40)
	bst.add(70)
	bst.add(80)
	bst.add(20)
	bst.add(60)
	bst.recurDelTree()

	bst.recurAdd(50)
	bst.recurAdd(30)
	bst.recurAdd(10)
	bst.recurAdd(90)
	bst.recurAdd(100)
	bst.recurAdd(40)
	bst.recurAdd(70)
	bst.recurAdd(80)
	bst.recurAdd(20)
	bst.recurAdd(60)

	bst.recurPreOrder()
	bst.preOrder()
	bst.recurInOrder()
	bst.inOrder()
	bst.recurPostOrder()
	bst.postOrder()

	print("Height: " + str(bst.recurHeight()))

	temp = bst.recurFind(75)
	# temp = bst.find(80)
	if temp is None:
		print("Node not found.")
	else:
		print("Node found: " + temp.toString())

	bst.recurDelTree()
	print("Height: " + str(bst.recurHeight()))


if __name__ == "__main__":
	main()

