#include <iostream>
#include <vector>
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
	void display() {
		cout << "Edge [src=" << src << ", dest=" << dest << ", weight=" << weight << "]";
	}
	friend class AdjMatrixWeightedGraph;
};

class AdjMatrixWeightedGraph {
private:
	int **mat;
	int vertCount, edgeCount;
	vector<Edge> edges;
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
				mat[i][j] = 0;
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

	int find(int v, int parent[]) {
		while(parent[v] != -1)
			v = parent[v];
		return v;
	}
	
	void unian(int sr, int dr, int parent[]) {
		parent[sr] = dr;
	}
	
	bool containsCycle() {
		bool cycle = false;
		int* parent = new int[vertCount];
		for(int i=0; i<vertCount; i++)
			parent[i] = -1;
		for (unsigned i=0; i<edges.size(); i++) {
			Edge e = edges[i];
			int sr = find(e.src, parent);
			int dr = find(e.dest, parent);
			if(sr == dr) {
				cycle = true;
				break;
			}
			unian(sr, dr, parent);
		}
		return cycle;
	}
};

int main() {
	AdjMatrixWeightedGraph g(9);
	g.accept();
	g.display();
	if(g.containsCycle())
		cout << "Graph contains cycle." << endl;
	else
		cout << "Graph doesn't contain cycle." << endl;
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
