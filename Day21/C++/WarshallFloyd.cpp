#include <iostream>
#include <stack>
#include <queue>
using namespace std;

#define vertCount 5
const int INF = 999;

class AdjMatrixWeightedGraph
{
private:
	int mat[vertCount][vertCount];
	int edgeCount;

public:
	AdjMatrixWeightedGraph()
	{
		edgeCount = 0;
		for (int i = 0; i < vertCount; i++)
		{
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = INF;
		}
	}
	~AdjMatrixWeightedGraph()
	{
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

	void warshallFloyd(int dist[][vertCount]) {
		// initialize dist matrix with direct dist/edge (adj matrix)
		for (int s = 0; s < vertCount; s++) {
			for (int d = 0; d < vertCount; d++)
				dist[s][d] = mat[s][d];
			// dist from a vertex to itself is 0
			dist[s][s] = 0;
		}
		// consider i as intermediate between each pair of s (source) and d (destination)
		for (int i = 0; i < vertCount; i++) {
			for (int s = 0; s < vertCount; s++) {
				for (int d = 0; d < vertCount; d++) {
					// update dist of s-d, if dist via intermediate is lesser than current dist of s-d.
					if(dist[s][i] != INF && dist[i][d] != INF && dist[s][i] + dist[i][d] < dist[s][d])
						dist[s][d] = dist[s][i] + dist[i][d];
				}
			}
		}
	}
};

void display(int mat[][vertCount])
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

int main() {
	AdjMatrixWeightedGraph g;
	g.accept();
	g.display();
	int dist[vertCount][vertCount];
	g.warshallFloyd(dist);
	cout << "From each vertex shortest distance to every other vertex:" << endl;
	display(dist);
	return 0;
}

/*
7
3 4 3
2 4 3
2 3 4
2 1 -2
1 3 -1
0 2 5
0 1 6 
*/