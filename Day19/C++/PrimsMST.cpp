#include <iostream>
#include <vector>
using namespace std;

const int INF = 999;

class AdjMatrixWeightedGraph
{
private:
	int **mat;
	int vertCount, edgeCount;

public:
	AdjMatrixWeightedGraph(int vCount)
	{
		vertCount = vCount;
		edgeCount = 0;
		mat = new int *[vertCount];
		for (int i = 0; i < vertCount; i++)
		{
			mat[i] = new int[vertCount];
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = INF;
		}
	}
	~AdjMatrixWeightedGraph()
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
			int src, dest, wt;
			cin >> src >> dest >> wt;
			mat[src][dest] = wt;
			mat[dest][src] = wt; // for undirected graph src -> dest => dest -> src
		}
	}

	void display()
	{
		for (int i = 0; i < vertCount; i++)
		{
			cout << "v" << i;
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[i][j] == INF)
					cout << "\t#";
				else
					cout << "\t" << mat[i][j];
			}
			cout << endl;
		}
	}

	int getMin(vector<int> &key, vector<bool> &mst)
	{
		int min = INF, minKeyVertex = 0;
		for (int i = 0; i < vertCount; i++)
		{
			if (mst[i] == false && key[i] < min)
			{
				min = key[i];
				minKeyVertex = i;
			}
		}
		return minKeyVertex;
	}

	vector<int> prim(int start)
	{
		// mst array to check if vertex is in mst.
		vector<bool> mst;
		// key array to maintain key of each vertex.
		vector<int> key;
		// parent array to keep parent of each vertex
		vector<int> parent;
		// initially no vertex in mst, key of all vertices as INF and parent of each vertex = -1
		for (int i = 0; i < vertCount; i++)
		{
			mst.push_back(false);
			key.push_back(INF);
			parent.push_back(-1);
		}
		// key of start vertex should be 0
		key[start] = 0;

		int mstVertCount = 0;
		// repeat until all vertices are added into mst
		while (mstVertCount < vertCount)
		{
			// get vertex with min key, which is not in mst
			int u = getMin(key, mst);
			// add it into mst
			mst[u] = true;
			mstVertCount++;
			// update key & parent of its adjacent vertices (which are not in mst)
			for (int v = 0; v < vertCount; v++)
			{
				if (mat[u][v] != INF && mst[v] == false && mat[u][v] < key[v])
				{
					key[v] = mat[u][v];
					parent[v] = u;
				}
			}
		}

		// return min spanning tree edges
		return parent;
	}

	int getWeight(int src, int dest)
	{
		return mat[src][dest];
	}
};

int main()
{
	AdjMatrixWeightedGraph g(9);
	g.accept();
	g.display();
	int sum = 0;
	vector<int> parent = g.prim(0);
	for (unsigned i = 0; i < parent.size(); i++)
	{
		cout << parent[i] << " -> " << i << endl;
		if (parent[i] != -1)
			sum = sum + g.getWeight(parent[i], i);
	}
	cout << "\nTotal weight of MST " << sum << endl;
}

/*
14
7 6 1
8 2 2
6 5 2
0 1 4
2 5 4
8 6 6
2 3 7
7 8 7
0 7 8
1 2 8
3 4 9
5 4 10
1 7 11
3 5 14
*/
