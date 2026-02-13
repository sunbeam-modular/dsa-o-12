
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


class Queue:
	def __init__(self):
		self.arr = list()

	def push(self, val):
		self.arr.append(val)

	def pop(self):
		return self.arr.pop(0)

	def peek(self):
		return self.arr[0]

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

	def find(self, val):
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

	def delNode(self, val):
		# find the node to be deleted with its parent
		(temp, parent) = self.find(val)
		if temp is None:
			raise Exception("Node Not Found.")
		# if node has parent & child node
		if temp.left is not None and temp.right is not None:
			parent = temp
			succ = temp.right
			while succ.left is not None:
				parent = succ
				succ = succ.left
			temp.data = succ.data
			temp = succ
		# if node doesn't have left child
		if temp.left is None:
			if temp == self.root:
				self.root = temp.right
			elif temp == parent.left:
				parent.left = temp.right
			else: #if(temp == parent.right)
				parent.right = temp.right
			temp = None
		# else if node doesn't have right child
		elif temp.right is None:
			if temp == self.root:
				self.root = temp.left
			elif temp == parent.left:
				parent.left = temp.left
			else: #if(temp == parent.right)
				parent.right = temp.left
			temp = None			

	def depthFirstSearch(self):
		print("DFS: ", end='')
		s = Stack()
		s.push(self.root)
		while not s.isEmpty():
			trav = s.pop()
			print(trav.data, end=", ")
			if trav.right is not None:
				s.push(trav.right)
			if trav.left is not None:
				s.push(trav.left)
		print()

	def breadthFirstSearch(self):
		print("BFS: ", end='')
		q = Queue()
		q.push(self.root)
		while not q.isEmpty():
			trav = q.pop()
			print(trav.data, end=", ")
			if trav.left is not None:
				q.push(trav.left)
			if trav.right is not None:
				q.push(trav.right)
		print()


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

	bst.breadthFirstSearch()
	bst.depthFirstSearch()

	bst.recurInOrder()
	bst.delNode(90)
	bst.recurInOrder()


if __name__ == "__main__":
	main()

