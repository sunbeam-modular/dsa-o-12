#include <iostream>
#include <vector>
#include <cmath>
#include <list>
#include <set>
#include <algorithm>
using namespace std;

#define INF 99999.99
#define vertCount 10

class Point {
public:
	int row, col;
public:
	Point(int row=0, int col=0) {
		this->row = row;
		this->col = col;
	}
	bool operator==(const Point& other) const {
		return row==other.row && col==other.col;
	}
	void display() {
		cout << "Point [row=" << row << ", col=" << col << "]" << endl;
	}
};

class Cell {
public:
	int row, col;
	double f, g, h;
	Point parent;
public:
	Cell(int row=0, int col=0, double f=INF, double g=INF, double h=INF) {
		this->row = row;
		this->col = col;
		this->f = f;
		this->g = g;
		this->h = h;
		this->parent = Point(-1, -1);
	}
	bool operator==(const Cell& other) const {
		return row==other.row && col==other.col;
	}
	bool operator<(const Cell& other) const {
		return f < other.f;
	}
};

class AStarSearch {	
private:
	int mat[vertCount][vertCount]; // maze -- 1 open & 0 blocked
public:
	AStarSearch(int grid[][vertCount]) {
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = grid[i][j];
		}
	}

	bool isValid(int r, int c) {
		return (r >= 0 && r < vertCount) && (c >= 0 && c < vertCount);
	}
	
	bool isBlocked(int r, int c) {
		return mat[r][c] == 0;
	}
	
	bool isDest(int r, int c, Point dest) {
		return r == dest.row && c == dest.col;
	}
	
	vector<Point> buildPath(Cell cells[][vertCount], Point dest) {
		vector<Point> path;
		Cell pt = cells[dest.row][dest.col];
		path.push_back(dest);
		while (pt.parent.row != -1 && pt.parent.col != -1) {
			path.insert(path.begin(), pt.parent);
			pt = cells[pt.parent.row][pt.parent.col];
		}
		return path;
	}
	
	double calcHeuristic(int row, int col, Point dest) {
		// euclidean
		int dx = row-dest.row, dy = col-dest.col;
		return sqrt(dx*dx + dy*dy);
	}

	vector<Point> aStarSearch(Point src, Point dest) {
		if(!isValid(src.row, src.col) || !isValid(dest.row, dest.col))
			throw "Source or Destination is not valid.";
		if(isBlocked(src.row, src.col) || isBlocked(dest.row, dest.col))
			throw "Source or Destination is blocked.";
		
		// track if vertex is in path
		bool onPath[vertCount][vertCount];
		
		// initialize cell details
		Cell cells[vertCount][vertCount];
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++) {
				cells[i][j] = Cell(i, j);
				onPath[i][j] = false;
			}
		}
		
		// start node f, g & h = 0.0
		Cell startCell = cells[src.row][src.col];
		startCell.f = startCell.g = startCell.h = 0.0;
		
		// check if src is dest
		if(isDest(src.row, src.col, dest))
			return buildPath(cells, dest);

		// queue for traversal
		set<Cell> q;
		// push start point in queue
		q.insert(startCell);
		// repeat while q is not empty
		while(!q.empty()) {
			// pop a point from queue
			Cell pt = *q.begin();
			cells[pt.row][pt.col] = pt; // update into cells matrix
			q.erase(q.begin());
			// add point into path
			onPath[pt.row][pt.col] = true;
			// list all neighbors points
			Point neighbors[] = {
				Point(pt.row-1, pt.col),	// UP
				Point(pt.row+1, pt.col),	// DOWN
				Point(pt.row, pt.col-1),	// LEFT
				Point(pt.row, pt.col+1),	// RIGHT
				Point(pt.row-1, pt.col-1),	// UP-LEFT
				Point(pt.row-1, pt.col+1),	// UP-RIGHT
				Point(pt.row+1, pt.col-1),	// DOWN-LEFT
				Point(pt.row+1, pt.col+1)	// DOWN-RIGHT
			};
			int neighborsLength = sizeof(neighbors) / sizeof(neighbors[0]);
			// add possible neighbors in queue
			for(int i=0; i<neighborsLength; i++) {
				Point adj = neighbors[i];
				// check if neighbor is valid
				if(isValid(adj.row, adj.col)) {
					// check if neighbor is dest
					if(isDest(adj.row, adj.col, dest)) {
						// set parent as current point
						cells[adj.row][adj.col].parent = Point(pt.row, pt.col);
						return buildPath(cells, dest);
					}
					// check if not on path & not blocked
					else if(!onPath[adj.row][adj.col] && !isBlocked(adj.row, adj.col)) {
						// get current & adjacent cell
						Cell adjCell = cells[adj.row][adj.col];
						// calculate new values of f, g & h
						double newg = pt.g + 1.0;
						double newh = calcHeuristic(adj.row, adj.col, dest);
						double newf = newg + newh;
						// if new f is less than current f of adjacent cell
						if(adjCell.f > newf) {
							// update f, g & h of adjacent
							adjCell.g = newg;
							adjCell.h = newh;
							adjCell.f = newf;

							set<Cell>::iterator itr = find(q.begin(), q.end(), adjCell);
							if (itr != q.end())
								q.erase(itr);
							// update parent as current
							adjCell.parent = Point(pt.row, pt.col);
							// add adjacent into q
							q.insert(adjCell);
						}
					}
				}
			}
		}
		throw "No path exists";
	}
	void print_list(set<Cell>& l) {
		set<Cell>::iterator itr = l.begin();
		while(itr!=l.end()) {
			cout << itr->f << ",";
			itr++;
		}
		cout << endl;
	}
	void printPath(int grid[][vertCount], vector<Point> path) {
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++) {
				Point pt(i, j);
				if(find(path.begin(), path.end(), pt) != path.end())
					cout << "* ";
				else
					cout << grid[i][j] << " ";
			}
			cout << endl;
		}
	}
};

int main() {
	int grid[vertCount][vertCount] =  { 
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
		{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, 
		{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
		{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, 
		{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
		{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
		{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
		{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
		{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 },
		{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 }
	};
	AStarSearch g(grid);
	Point src(0, 0);
	Point dest(9, 9);
	try {
		vector<Point> path = g.aStarSearch(src, dest);
		g.printPath(grid, path);
	} catch(...) {
		cout << "No path exists." << endl;
	}
	return 0;
}
