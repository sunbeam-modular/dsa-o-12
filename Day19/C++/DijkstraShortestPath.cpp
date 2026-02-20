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

	int getMin(vector<int> &dist, vector<bool> &spt)
	{
		int min = INF, minDistVertex = 0;
		for (int i = 0; i < vertCount; i++)
		{
			if (spt[i] == false && dist[i] < min)
			{
				min = dist[i];
				minDistVertex = i;
			}
		}
		return minDistVertex;
	}

	vector<int> dijkstra(int start, vector<int> &dist)
	{
		// spt array to check if vertex is in spt.
		vector<bool> spt;
		// dist array to maintain dist of each vertex.
		dist.clear();
		// parent array to keep parent of each vertex
		vector<int> parent;
		// initially no vertex in spt, dist of all vertices as INF and parent of each vertex = -1
		for (int i = 0; i < vertCount; i++)
		{
			spt.push_back(false);
			dist.push_back(INF);
			parent.push_back(-1);
		}
		// dist of start vertex should be 0
		dist[start] = 0;

		int sptVertCount = 0;
		// repeat until all vertices are added into spt
		while (sptVertCount < vertCount)
		{
			// get vertex with min dist, which is not in spt
			int u = getMin(dist, spt);
			// add it into spt
			spt[u] = true;
			sptVertCount++;
			// update dist & parent of its adjacent vertices (which are not in spt)
			for (int v = 0; v < vertCount; v++)
			{
				if (mat[u][v] != INF && spt[v] == false && dist[u] + mat[u][v] < dist[v])
				{
					dist[v] = dist[u] + mat[u][v];
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
	int src = 0;
	vector<int> dist;
	vector<int> parent = g.dijkstra(src, dist);

	for (unsigned i = 0; i < dist.size(); i++)
		cout << "dist of " << i << " from " << src << " = " << dist[i] << endl;

	cout << "Shorest Path Tree from " << src << ":" << endl;
	for (unsigned i = 0; i < dist.size(); i++)
		cout << parent[i] << " -> " << i << endl;
	cout << endl;

	int dest = 4;
	cout << "Path from " << dest << " to " << src << " : ";
	int v = dest;
	while (v != src)
	{
		cout << v << " -> ";
		v = parent[v];
	}
	cout << src << endl;
	return 0;
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
