class Node:
	def __init__(self, val=0):
		self.data = val
		self.next = None


class SinglyCircularList:

	def __init__(self):
		self.head = None
		self.count = 0

	# time complexity - O(1)
	def addFirst(self, val):
		# create new node
		newNode = Node(val)
		# spl: list is empty
		if self.head is None:
			# add node at the start of list
			self.head = newNode
			# make it circular
			newNode.next = self.head
		else:
			# traverse till last node
			trav = self.head
			while trav.next != self.head:
				trav = trav.next
			# newnode's next to head
			newNode.next = self.head
			# last node's next to new node
			trav.next = newNode
		self.head = newNode
		self.count = self.count + 1

	# time complexity - O(n)
	def addLast(self, val):
		# create new node
		newNode = Node(val)
		# spl: list is empty
		if self.head is None:
			# add node at the start of list
			self.head = newNode
			# make it circular
			newNode.next = self.head
		else:
			# traverse till last node
			trav = self.head
			while trav.next != self.head:
				trav = trav.next
			# newnode's next to head
			newNode.next = self.head
			# last node's next to new node
			trav.next = newNode
		self.count = self.count + 1

	# time complexity - O(n)
	def display(self):
		print("List: ", end='')
		if self.head is not None:
			trav = self.head
			while True:
				print(trav.data, end=", ")
				trav = trav.next
				if(trav == self.head):
					break
		print()

	# time complexity - O(n)
	def addAtPos(self, val, pos):
		# spl 4. invalid pos ( < 1 or > length)
		if pos < 1 or pos > (self.count+1):
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
			# add node at that pos
			newNode.next = trav.next
			trav.next = newNode
			self.count = self.count + 1

	# time complexity - O(1)
	def delFirst(self):
		# spl1: list is empty
		if self.head is None:
			raise Exception("List is empty.")
		# spl2: list contains only one element
		if self.head == self.head.next:
			self.head = None
		else:
			# traverse till last node
			trav = self.head
			while trav.next != self.head:
				trav = trav.next
			# take head to the next node (2nd)
			self.head = self.head.next
			# last node's next to the new head
			trav.next = self.head
		self.count = self.count - 1

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
				if trav == self.head:
					raise Exception("Invalid position " + str(pos))
			temp = trav.next
			# spl 5: pos == length, if temp is null, throw exception
			if temp is None:
				raise Exception("Invalid position " + str(pos))
			trav.next = temp.next

	# time complexity - O(1)
	def delAll(self):
		self.head = None


def main():
	l1 = SinglyCircularList()
	l1.addLast(11)
	l1.addLast(22)
	l1.addLast(33)
	l1.addFirst(44)
	l1.display()
	l1.addAtPos(55, 5)
	l1.display()
	l1.delFirst()
	l1.display()
	# l1.delLast()
	# l1.display()
	# l1.delAtPos(3)
	# l1.display()



if __name__ == "__main__":
	main()

