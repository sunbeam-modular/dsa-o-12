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

    def printMid(self):
        if self.head is not None:
            slow = self.head
            fast = self.head
            while fast is not None and fast.next is not None:
                slow = slow.next
                fast = fast.next.next
            print("Middle Node:", slow.data)
        else:
            print("List is empty!")


def main():
    list = SinglyList()
    list.addLast(10)
    list.addLast(20)
    list.addLast(30)
    list.addLast(40)
    list.addLast(50)
    list.addLast(60)
    list.addLast(70)
    list.addLast(80)
    list.display()
    list.printMid()


if __name__ == "__main__":
    main()
