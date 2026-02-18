#include <iostream>
#include <stack>
#include <queue>
#include <cstring>
using namespace std;

class AdjMatrixNonWeightedGraph
{
private:
	int **mat;
	int vertCount, edgeCount;

public:
	AdjMatrixNonWeightedGraph(int vCount)
	{
		vertCount = vCount;
		edgeCount = 0;
		mat = new int *[vertCount];
		for (int i = 0; i < vertCount; i++)
		{
			mat[i] = new int[vertCount];
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = 0;
		}
	}
	~AdjMatrixNonWeightedGraph()
	{
		for (int i = 0; i < vertCount; i++)
			delete[] mat[i];
		delete[] mat;
		mat = NULL;
		vertCount = 0;
		edgeCount = 0;
	}

	void accept()
	{
		cout << "Enter number of edges: ";
		cin >> edgeCount;
		cout << "Enter " << edgeCount << " edges -- src dest weight:" << endl;
		for (int i = 0; i < edgeCount; i++)
		{
			int src, dest;
			cin >> src >> dest;
			mat[src][dest] = 1;
			mat[dest][src] = 1; // for undirected graph src -> dest => dest -> src
		}
	}

	void display()
	{
		for (int i = 0; i < vertCount; i++)
		{
			cout << "v" << i;
			for (int j = 0; j < vertCount; j++)
				cout << "\t" << mat[i][j];
			cout << endl;
		}
	}

	void dfsTrav(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		stack<int> s;
		cout << "DFS: ";
		// push start vertex on stack and mark it
		s.push(start);
		marked[start] = true;
		// while stack is not empty
		while (!s.empty())
		{
			// pop vertex from stack and visit it
			int trav = s.top();
			s.pop();
			cout << "\t" << trav << ", ";
			// put all its non-marked adjacent vertices on the stack & mark them
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] != 0 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}

	bool dfsIsConnected()
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		stack<int> s;
		int count = 0;

		int start = 0;
		s.push(start);
		marked[start] = true;
		count++;

		while (!s.empty())
		{
			int trav = s.top();
			s.pop();

			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] == 1 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
					count++;
					if (count == vertCount)
					{
						delete[] marked;
						return true;
					}
				}
			}
		}
		delete[] marked;
		return false;
	}

	void dfsSpanningTree(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		stack<int> s;
		cout << "DFS Spanning Tree: " << endl;

		s.push(start);
		marked[start] = true;

		while (!s.empty())
		{
			int trav = s.top();
			s.pop();

			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] == 1 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
					cout << trav << " -> " << j << endl;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}

	void bfsTrav(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		queue<int> s;
		cout << "BFS: ";
		// push start vertex on queue and mark it
		s.push(start);
		marked[start] = true;
		// while queue is not empty
		while (!s.empty())
		{
			// pop vertex from queue and visit it
			int trav = s.front();
			s.pop();
			cout << "\t" << trav << ", ";
			// put all its non-marked adjacent vertices on the queue & mark them
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] != 0 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}

	void bfsSpanningTree(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		queue<int> s;
		cout << "BFS Spanning Tree: " << endl;

		s.push(start);
		marked[start] = true;

		while (!s.empty())
		{
			int trav = s.front();
			s.pop();

			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] == 1 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
					cout << trav << " -> " << j << endl;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}

	void bfsPathLength(int start)
	{
		int *dist = new int[vertCount];
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		queue<int> s;
		s.push(start);
		marked[start] = true;
		dist[start] = 0;
		while (!s.empty())
		{
			int trav = s.front();
			s.pop();
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] == 1 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
					dist[j] = dist[trav] + 1;
				}
			}
		}
		cout << "Single Source Path Length: " << endl;
		for (int i = 0; i < vertCount; i++)
			cout << "Distance of " << i << " from " << start << " = " << dist[i] << endl;
		cout << endl;
		delete[] dist;
		delete[] marked;
	}

	bool bfsIsBipartite()
	{
		const int NOCOLOR = 0, YELLOW = 1, GREEN = -1;
		int start = 0;
		int *color = new int[vertCount];
		memset(color, 0, vertCount * sizeof(int));
		for (int i = 0; i < vertCount; i++)
			color[i] = NOCOLOR;
		queue<int> s;
		s.push(start);
		color[start] = YELLOW;

		while (!s.empty())
		{
			int trav = s.front();
			s.pop();

			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] == 1)
				{
					if (color[j] == NOCOLOR)
					{
						s.push(j);
						color[j] = -1 * color[trav];
					}
					else
					{
						if (color[j] == color[trav])
						{
							delete[] color;
							return false; // not bipartite graph
						}
					}
				}
			}
		}
		delete[] color;
		return true;
	}
};

int main()
{
	AdjMatrixNonWeightedGraph g(6);
	g.accept();
	g.display();
	g.dfsTrav(0);
	g.bfsTrav(0);
	if (g.dfsIsConnected())
		cout << "Graph is connected." << endl;
	else
		cout << "Graph is not connected." << endl;
	g.dfsSpanningTree(0);
	g.bfsSpanningTree(0);
	g.bfsPathLength(0);
	if (g.bfsIsBipartite())
		cout << "Graph is bi-partite." << endl;
	else
		cout << "Graph is not bi-partite." << endl;
	return 0;
}

/*
7
0 1
0 2
0 3
1 2
1 4 
3 4
3 5 
*/

