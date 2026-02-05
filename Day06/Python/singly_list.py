class Node:
	def __init__(self, val=0):
		self.data = val
		self.next = None


class SinglyList:

	def __init__(self):
		self.head = None

	# time complexity - O(1)
	def addFirst(self, val):
		newNode = Node(val)
		newNode.next = self.head
		self.head = newNode


	# time complexity - O(n)
	def addLast(self, val):
		newNode = Node(val)
		# special case: if list is empty
		if self.head is None:
			self.head = newNode # newNode itself is first node of list
		else: # general case: if list already have few nodes
			# traverse till last node
			trav = self.head
			while trav.next is not None:
				trav = trav.next
			# add address of newNode into last node's next
			trav.next = newNode


	# time complexity - O(n)
	def display(self):
		print("List: ", end='')
		trav = self.head
		while trav is not None:
			print(trav.data, end=", ")
			trav = trav.next
		print()

	# time complexity - O(n)
	def addAtPos(self, val, pos):
		# spl 4. invalid pos( < 1)
		if pos < 1:
			raise Exception("Invalid position " + str(pos))
		# spl 1. if list is empty OR spl 2. add at pos = 1
		if self.head is None or pos == 1:
			self.addFirst(val)
		else:
			# create a new node
			newNode = Node(val)
			# traverse till pos - 1
			trav = self.head
			for i in range(1, pos-1):
				# spl 3. if pos to add is beyond length of list, throw error
				trav = trav.next
				if trav is None:
					raise Exception("Invalid position " + str(pos))
			# add node at that pos
			newNode.next = trav.next
			trav.next = newNode


	# time complexity - O(1)
	def delFirst(self):
		if self.head is None:
			raise Exception("List is empty.")
		self.head = self.head.next

	def delAtPos(self, pos):
		# spl 3: if pos < 1, throw exception
		if pos < 1:
			raise Exception("Invalid position " + str(pos))
		# spl 1: list is empty & 2: pos == 1
		if self.head is None or pos == 1:
			self.delFirst()
		else:
			trav = self.head
			for i in range(1, pos-1):
				trav = trav.next
				# spl 4: if pos > length, throw exception
				if trav is None:
					raise Exception("Invalid position " + str(pos))
			temp = trav.next
			# spl 5: pos == length, if temp is null, throw exception
			if temp is None:
				raise Exception("Invalid position " + str(pos))
			trav.next = temp.next

	# time complexity - O(n)
	def delAll(self):
		while self.head is not None:
			self.delFirst()
		# head = null;

	# time complexity - O(n)
	def delLast(self):
		# spl1: list is empty, spl2: list have only one node.
		if self.head is None or self.head.next is None:
			self.delFirst()
		else:
			temp = None
			trav = self.head
			while trav.next is not None:
				temp = trav
				trav = trav.next
			temp.next = None


def main():
	l1 = SinglyList()
	l1.addLast(11)
	l1.addLast(22)
	l1.addLast(33)
	l1.addFirst(44)
	l1.display()
	l1.addAtPos(55, 2)
	l1.display()
	l1.delFirst()
	l1.display()
	l1.delLast()
	l1.display()
	l1.delAtPos(3)
	l1.display()



if __name__ == "__main__":
	main()

