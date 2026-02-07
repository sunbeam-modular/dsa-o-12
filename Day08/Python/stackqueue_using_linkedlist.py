# Singly Linear Linked List with head and tail pointer
class SinglyLinearList:
    # static node class
    class Node:
        def __init__(self, val):
            self.data = val
            self.next = None

    # list methods
    def __init__(self):
        self.head = None
        self.tail = None

    # time: O(1) -- same as singly linear list
    def display(self):
        print("List: ", end="")
        # traversing begins with head node
        trav = self.head
        while trav is not None:
            # for each node print data and go to next node
            print(trav.data, "-> ", end="")
            trav = trav.next
        # repeat until null (end of list) is reached
        print()

    # time: O(1)
    def addFirst(self, val):
        # create new node
        newNode = self.Node(val)
        # if list is empty, new node will be the first & last node
        if self.head is None:
            self.head = newNode
            self.tail = newNode
        # newnode next will point to first node (head)
        # and mark new node as first node (head)
        else:
            newNode.next = self.head
            self.head = newNode

    # time: O(1)
    def addLast(self, val):
        # create new node
        newNode = self.Node(val)
        # if list is empty, new node will be the first & last node
        if self.head is None:
            self.head = newNode
            self.tail = newNode
        # newnode added to next of last node (tail)
        # and mark new node as last node (tail)
        else:
            self.tail.next = newNode
            self.tail = newNode

    # time: O(1)
    def delFirst(self):
        val = 0
        if self.head is not None:
            val = self.head.data  # get data of node to be deleted (first node)
            self.head = self.head.next
            if self.head is None:  # the deleted node was last node
                self.tail = None
        return val

    def isEmpty(self):
        return self.head is None


def main():
    # Queue using LinkedList -- add last and del first
    #      addition and deletion are done from different ends
    q = SinglyLinearList()
    q.addLast(10)
    q.addLast(20)
    q.addLast(30)
    q.addLast(40)
    q.display()  # 10 -> 20 -> 30 -> 40
    while not q.isEmpty():
        val = q.delFirst()
        print("Deleted Elem:", val)  # 10, 20, 30, 40

    # Stack using LinkedList -- add first and del first
    #      addition and deletion are done from same ends
    s = SinglyLinearList()
    s.addFirst(10)
    s.addFirst(20)
    s.addFirst(30)
    s.addFirst(40)
    s.display()  # 40 -> 30 -> 20 -> 10
    while not s.isEmpty():
        val = s.delFirst()
        print("Deleted Elem:", val)  # 40, 30, 20, 10


if __name__ == "__main__":
    main()