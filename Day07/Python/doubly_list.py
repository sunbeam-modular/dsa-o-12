class Node:
	def __init__(self, val=0):
		self.data = val
		self.next = None
		self.prev = None


class DoublyList:

	def __init__(self):
		self.head = None
		self.count = 0

	# time complexity - O(1)
	def addFirst(self, val):
		newNode = Node(val)
		if self.head is None:
			self.head = newNode
		else:
			newNode.next = self.head
			self.head.prev = newNode
			self.head = newNode
		self.count = self.count + 1

	# time complexity - O(n)
	def addLast(self, val):
		newNode = Node(val)
		if self.head is None:
			self.head = newNode
		else:
			trav = self.head
			while trav.next is not None:
				trav = trav.next
			trav.next = newNode
			newNode.prev = trav
		self.count = self.count + 1

	# time complexity - O(n)
	def display(self):
		temp = None
		# display forward
		print("Fwd List: ", end='')
		trav = self.head
		while trav is not None:
			print(trav.data, end=", ")
			temp = trav
			trav = trav.next
		print()
		# display reverse
		print("Rev List: ", end='')
		trav = temp
		while trav is not None:
			print(trav.data, end=", ")
			trav = trav.prev
		print()

	# time complexity - O(n)
	def addAtPos(self, val, pos):
		# // spl3: add at last pos
		if pos == self.count+1:
			self.addLast(val)
		# // spl4: invalid pos
		elif pos < 1 or pos > self.count+1:
			raise Exception("Invalid position " + str(pos))
		# // spl1: list is empty or spl2: pos is 1
		elif self.head is None or pos == 1:
			self.addFirst(val)
		else:
			newNode = Node(val)
			# // traverse till pos-1
			trav = self.head
			for i in range(1, pos-1):
				trav = trav.next
			# // mark its next node as temp
			temp = trav.next
			# // add newNode between trav & temp
			newNode.next = temp
			newNode.prev = trav
			trav.next = newNode
			temp.prev = newNode
			self.count = self.count + 1

	# time complexity - O(1)
	def delFirst(self):
		# // spl1: list is empty
		if self.head is None:
			raise Exception("List is empty.")
		# // spl2: if list have only one element
		if self.head.next is None:
			self.head = None
		else:
			self.head = self.head.next
			self.head.prev = None
		self.count = self.count - 1

	def delAtPos(self, pos):
		# // spl1: list is empty or spl2: pos = 1
		if self.head is None or pos == 1:
			self.delFirst()
		else:
			# // spl3: invalid pos
			if pos < 1 or pos > self.count:
				raise Exception("Invalid position " + str(pos))
			# // traverse till pos
			trav = self.head
			for i in range(1, pos):
				trav = trav.next
			# // delete trav node
			trav.prev.next = trav.next
			if(trav.next is not None): # // spl4: if not last node to delete.
				trav.next.prev = trav.prev
			self.count = self.count - 1

	# time complexity - O(n)
	def delAll(self):
		while self.head is not None:
			self.delFirst()
		# head = None

	def countNodes(self):
		cnt = 0
		trav = self.head
		while trav is not None:
			cnt = cnt + 1
			trav = trav.next
		return cnt


def main():
	l1 = DoublyList()
	l1.addLast(11)
	l1.addLast(22)
	l1.addLast(33)
	l1.addFirst(44)
	l1.display()
	l1.addAtPos(55, 5)
	l1.display()
	l1.delFirst()
	l1.display()
	l1.delAtPos(2)
	l1.display()


if __name__ == "__main__":
	main()

