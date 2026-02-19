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
	
	bool containsCycle(vector<Edge>& edges) {
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

	vector<Edge> kruskal() {
		// sort edges in asc order of weight
		sort(edges.begin(), edges.end(), Edge::compEdgeWeight);
		// create mst
		vector<Edge> mst;
		int i=0, mstEdgeCount = 0;

		// until V-1 edges are not added into mst
		while(mstEdgeCount < vertCount-1) {
			// get next edge of min weight
			Edge e = edges[i];
			i = i + 1;
			// add it into mst
			mst.push_back(e);
			mstEdgeCount++;
			// check if it is forming cycle & if so, discard it
			if(containsCycle(mst)) {
				mstEdgeCount--;
				mst.pop_back();
			}
		}
		
		// return mst
		return mst;
	}
};

int main() {
	AdjMatrixWeightedGraph g(9);
	g.accept();
	g.display();
	
		int sum = 0;
		vector<Edge> mst = g.kruskal();
		for (unsigned i=0; i<mst.size(); i++) {
			mst[i].display();
			sum = sum + mst[i].getWeight();
		}
		cout << "\nTotal weight of MST = " << sum << endl;
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
