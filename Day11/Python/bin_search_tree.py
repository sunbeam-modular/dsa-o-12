

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
	
	def recHeight(self, trav):
		if trav == None:
			return -1
		hl = self.recHeight(trav.left)
		hr = self.recHeight(trav.right)
		m = max(hl, hr)
		return m + 1

	def recurHeight(self):
		return self.recHeight(self.root)


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

	bst.recurPreOrder()
	bst.recurInOrder()
	bst.recurPostOrder()

	print("Height: " + str(bst.recurHeight()))


if __name__ == "__main__":
	main()

