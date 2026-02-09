class SinglyList:
    class Node:
        def __init__(self, val):
            self.data = val
            self.next = None

    def __init__(self):
        self.head = None

    def display(self):
        print("List: ", end="")
        trav = self.head
        while trav is not None:
            print(trav.data, "-> ", end="")
            trav = trav.next
        print()

    def addLast(self, val):
        newNode = self.Node(val)
        if self.head is None:
            self.head = newNode
        else:
            trav = self.head
            while trav.next is not None:
                trav = trav.next
            trav.next = newNode

    def revList(self):
        oldhead = self.head
        self.head = None
        while oldhead is not None:
            temp = oldhead
            oldhead = oldhead.next
            temp.next = self.head
            self.head = temp

    def recRevList(self, trav=None):
        if trav is None:
            if self.head is not None:
                self.recRevList(self.head)
            return
        if trav.next is None:
            self.head = trav
            return trav
        self.recRevList(trav.next).next = trav
        trav.next = None
        return trav


def main():
    list = SinglyList()
    list.addLast(10)
    list.addLast(20)
    list.addLast(30)
    list.addLast(40)
    list.addLast(50)
    list.display()
    # list.revList()    # list reverse without recursion
    list.recRevList()  # list reverse using recursion
    list.display()


if __name__ == "__main__":
    main()
