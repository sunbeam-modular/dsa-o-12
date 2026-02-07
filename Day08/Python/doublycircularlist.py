class DoublyCircularList:
    class Node:
        def __init__(self, val=0):
            self.data = val
            self.next = None
            self.prev = None

    def __init__(self):
        self.head = None

    def displayFwd(self):
        print("FWD LIST : ", end="")
        if self.head is not None:
            trav = self.head
            while True:
                print(trav.data, end=", ")
                trav = trav.next
                if trav == self.head:
                    break
        print()

    def displayRev(self):
        print("REV LIST : ", end="")
        if self.head is not None:
            trav = self.head.prev
            while True:
                print(trav.data, end=", ")
                trav = trav.prev
                if trav == self.head.prev:
                    break
        print()

    def addLast(self, val):
        # create and init new node
        newNode = self.Node(val)
        # if list is empty, make new node as first node and circular
        if self.head is None:
            self.head = newNode
            newNode.next = self.head
            newNode.prev = self.head
        else:
            # traverse till last node of the list
            trav = self.head.prev
            # new node prev to last node and new node next to head
            newNode.prev = trav
            newNode.next = self.head
            # after last node add new node.
            trav.next = newNode
            # head node prev to new node
            self.head.prev = newNode

    def addFirst(self, val):
        # create and init new node
        newNode = self.Node(val)
        # if list is empty, make new node as first node and circular
        if self.head is None:
            self.head = newNode
            newNode.next = self.head
            newNode.prev = self.head
        else:
            # traverse till last node of the list
            trav = self.head.prev
            # new node prev to last node and new node next to head
            newNode.prev = trav
            newNode.next = self.head
            # after last node add new node.
            trav.next = newNode
            # head node prev to new node
            self.head.prev = newNode
            # head to new node
            self.head = newNode

    def addAtPos(self, val, pos):
        # homework
        pass

    def delFirst(self):
        # homework
        pass

    def delAtPos(self, pos):
        # homework
        pass

    def delLast(self):
        # homework
        pass

    def delAll(self):
        self.head = None


def main():
    list = DoublyCircularList()
    list.addLast(11)
    list.addLast(22)
    list.addLast(33)
    list.addLast(44)
    list.displayFwd()
    list.displayRev()
    list.addFirst(55)
    list.displayFwd()
    list.displayRev()


if __name__ == "__main__":
    main()