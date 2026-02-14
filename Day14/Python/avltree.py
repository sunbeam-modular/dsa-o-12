class Node:
    def __init__(self, val):
        self.data = val
        self.left = None
        self.right = None
        self.height = 0


class AvlTree:
    def __init__(self):
        self.root = None

    def preorder_trav(self):
        print(" PRE: ", end="")
        self.preorder(self.root)
        print()

    def preorder(self, trav=None):
        if trav is None:
            return
        print(trav.data, end=", ")
        self.preorder(trav.left)
        self.preorder(trav.right)

    def get_height(self, trav):
        if trav is None:
            return -1
        return trav.height

    def get_balance_factor(self, trav):
        if trav is None:
            return 0
        diff = self.get_height(trav.left) - self.get_height(trav.right)
        return diff

    def get_tree_height(self):
        if self.root is None:
            return -1
        return self.root.height

    def update_height(self, trav):
        lh = self.get_height(trav.left)
        rh = self.get_height(trav.right)
        h = max(lh, rh) + 1
        trav.height = h

    def left_rotate(self, axis, parent):
        if axis is None or axis.right is None:
            return None
        new_axis = axis.right
        axis.right = new_axis.left
        new_axis.left = axis
        if axis == self.root:
            self.root = new_axis
        elif axis == parent.left:
            parent.left = new_axis
        else:  # axis == parent.right
            parent.right = new_axis
        self.update_height(axis)
        self.update_height(new_axis)
        return new_axis

    def right_rotate(self, axis, parent):
        if axis is None or axis.left is None:
            return None
        new_axis = axis.left
        axis.left = new_axis.right
        new_axis.right = axis
        if axis == self.root:
            self.root = new_axis
        elif axis == parent.left:
            parent.left = new_axis
        else:  # axis == parent.right
            parent.right = new_axis
        self.update_height(axis)
        self.update_height(new_axis)
        return new_axis

    def add_val(self, val):
        if self.root is None:
            self.root = Node(val)
        else:
            self.add(val, self.root, None)

    def add(self, val, trav, parent):
        if val < trav.data:
            if trav.left is None:
                trav.left = Node(val)
            else:
                self.add(val, trav.left, trav)
        else:  # val >= trav.data
            if trav.right is None:
                trav.right = Node(val)
            else:
                self.add(val, trav.right, trav)

        self.update_height(trav)
        bf = self.get_balance_factor(trav)

        # balance each ancestor ...
        if bf > 1 and val < trav.left.data:
            print("left-left case")
            self.right_rotate(trav, parent)
            return
        if bf < -1 and val > trav.right.data:
            print("right-right case")
            self.left_rotate(trav, parent)
            return
        if bf > 1 and val > trav.left.data:
            print("left-right case")
            self.left_rotate(trav.left, trav)
            self.right_rotate(trav, parent)
            return
        if bf < -1 and val < trav.right.data:
            print("right-left case")
            self.right_rotate(trav.right, trav)
            self.left_rotate(trav, parent)
            return


def main():
    t = AvlTree()
    t.add_val(1)
    t.add_val(2)
    t.add_val(3)    # RR
    t.add_val(0)
    t.add_val(-3)   # LL
    t.add_val(6)
    t.add_val(4)    # RL
    t.add_val(-5)
    t.add_val(-4)   # LR
    t.preorder_trav()
    print("Tree Height:", t.get_tree_height())


if __name__ == "__main__":
    main()
