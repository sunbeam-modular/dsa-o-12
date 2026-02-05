class Cell:
    def __init__(self, r, c):
        self.r = r
        self.c = c

    def __eq__(self, other):
        if not isinstance(other, Cell):
            return False
        return self.c == other.c and self.r == other.r


def is_valid(cell, maze):
    if cell.r < 0 or cell.r >= len(maze):
        return False
    if cell.c < 0 or cell.c >= len(maze):
        return False
    if maze[cell.r][cell.c] == 1:  # obstacle
        return False
    return True  # valid


def is_marked(cell, marked):
    return marked[cell.r][cell.c]


def mark(cell, marked):
    marked[cell.r][cell.c] = True


def is_reachable(maze, rat, cheese):
    # 2-d array for marked
    marked = [[False for _ in range(len(maze))] for _ in range(len(maze))]
    # stack of cells
    s = []
    # push rat (src) on stack and mark it
    s.append(rat)
    mark(rat, marked)
    while s:
        # pop cell
        cur = s.pop()
        # reached cheese (dest)?
        if cur == cheese:
            return True
        # find its neighbors
        neighbors = [
            Cell(cur.r - 1, cur.c),
            Cell(cur.r + 1, cur.c),
            Cell(cur.r, cur.c + 1),
            Cell(cur.r, cur.c - 1),
        ]
        # push all valid & unmarked neighbors on stack
        for neighbor in neighbors:
            if is_valid(neighbor, maze) and not is_marked(neighbor, marked):
                s.append(neighbor)
                mark(neighbor, marked)  # also mark the neighbor
    # repeat until stack is empty
    return False


def main():
    maze = [
        [0, 1, 0, 1, 1],
        [0, 0, 0, 0, 0],
        [1, 0, 1, 0, 1],
        [0, 0, 1, 0, 0],
        [1, 0, 0, 1, 0]
    ]
    rat = Cell(0, 0)
    cheese = Cell(4, 4)
    success = is_reachable(maze, rat, cheese)
    print("Rat can get cheese:", success)


if __name__ == "__main__":
    main()
