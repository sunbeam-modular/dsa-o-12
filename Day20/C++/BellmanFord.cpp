#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 999;

class Edge {
	private:
		int src, dest, weight;
	public:
	Edge(int src, int dest, int weight) {
		this->src = src;
		this->dest = dest;
		this->weight = weight;
	}
	int getWeight() {
		return weight;
	}
	void display() {
		cout << "Edge [src=" << src << ", dest=" << dest << ", weight=" << weight << "]" << endl;
	}
	static bool compEdgeWeight(const Edge& e1, const Edge& e2) {
		return e1.weight < e2.weight;
	}
	friend class BellmanFord;
};

class BellmanFord {
private:
	int **mat;
	int vertCount, edgeCount;
	vector<Edge> edges;
public:
	BellmanFord(int vCount)
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
	~BellmanFord()
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
			Edge e(src, dest, wt);
			edges.push_back(e);
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

	int* bellmanFord(int start) {
		int* dist = new int[vertCount];
		// init dist of start as 0 and others as INF
		for (int i = 0; i < vertCount; i++)
			dist[i] = INF;
		dist[start] = 0;

		// repeat for V-1 times
		for(int i=1; i<vertCount; i++) {
			// for all edges
			for(int j=0; j<edgeCount; j++) {
				Edge e = edges[j];
				// update weights of all dest
				if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
					dist[e.dest] = dist[e.src] + e.weight;
			}
		}
		
		// check for -ve cycle
		for(int j=0; j<edgeCount; j++) {
			Edge e = edges[j];
			// update weights of all dest
			if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
				throw "Graph contains -ve cycle.";
		}
		
		return dist;
	}
};

int main() {
	int vertCount = 5;
	BellmanFord g(vertCount);
	g.accept();
	g.display();
	
	int* dist;
	dist = g.bellmanFord(0);
	for (int i = 0; i < vertCount; i++)
		cout << "distance of vertex " << i << " from vertex 0 = " << dist[i] << endl;
	
	delete[] dist;
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

