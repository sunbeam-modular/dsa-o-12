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

    def selectionSort(self):
        i = self.head
        while i is not None:
            j = i.next
            while j is not None:
                if i.data > j.data:
                    i.data, j.data = j.data, i.data
                j = j.next
            i = i.next

    def revDisplay(self, trav=None):
        if trav is None:
            print("Rev : ", end="")
            self.revDisplay(self.head)
            print()
            return
        if trav is None:
            return
        self.revDisplay(trav.next)
        print(trav.data, end=", ")


def main():
    list = SinglyList()
    list.addLast(30)
    list.addLast(50)
    list.addLast(10)
    list.addLast(40)
    list.addLast(20)
    list.display()  # 30 -> 50 -> 10 -> 40 -> 20 -> 
    list.selectionSort()
    list.display()  # 10 -> 20 -> 30 -> 40 -> 50 -> 
    list.revDisplay()


if __name__ == "__main__":
    main()