#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Cell
{
public:
    int r, c;
    Cell(int r, int c) : r(r), c(c) {}
    bool operator==(const Cell &other) const
    {
        return c == other.c && r == other.r;
    }
};

bool isValid(const Cell &cell, const vector<vector<int>> &maze)
{
    if (cell.r < 0 || cell.r >= maze.size())
        return false;
    if (cell.c < 0 || cell.c >= maze.size())
        return false;
    if (maze[cell.r][cell.c] == 1) // obstacle
        return false;
    return true; // valid
}

bool isMarked(const Cell &cell, const vector<vector<bool>> &marked)
{
    return marked[cell.r][cell.c];
}

void mark(const Cell &cell, vector<vector<bool>> &marked)
{
    marked[cell.r][cell.c] = true;
}

bool isReachable(const vector<vector<int>> &maze, const Cell &rat, const Cell &cheese)
{
    // 2-d array for marked
    vector<vector<bool>> marked(maze.size(), vector<bool>(maze.size(), false));
    // stack of cells
    stack<Cell> s;
    // push rat (src) on stack and mark it
    s.push(rat);
    mark(rat, marked);
    while (!s.empty())
    {
        // pop cell
        Cell cur = s.top();
        s.pop();
        // reached cheese (dest)?
        if (cur == cheese)
            return true;
        // find its neighbors
        Cell neighbors[] = {
            Cell(cur.r - 1, cur.c),
            Cell(cur.r + 1, cur.c),
            Cell(cur.r, cur.c + 1),
            Cell(cur.r, cur.c - 1)};
        // push all valid & unmarked neighbors on stack
        for (const Cell &neighbor : neighbors)
        {
            if (isValid(neighbor, maze) && !isMarked(neighbor, marked))
            {
                s.push(neighbor);
                mark(neighbor, marked); // also mark the neighbor
            }
        }
    } // repeat until stack is empty
    return false;
}

int main()
{
    vector<vector<int>> maze = {
        {0, 1, 0, 1, 1},
        {0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1},
        {0, 0, 1, 0, 0},
        {1, 0, 0, 1, 0}};
    Cell rat(0, 0);
    Cell cheese(4, 4);
    bool success = isReachable(maze, rat, cheese);
    cout << "Rat can get cheese: " << boolalpha << success << endl;
    return 0;
}