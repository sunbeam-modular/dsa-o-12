#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

#define vertCount 5

const int INF = 999;

class Edge {
	public:
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

class Johnson {
private:
	int mat[vertCount][vertCount];
	int edgeCount;
	vector<Edge> edges;
public:
	Johnson(int vCount)
	{
        if(vCount != vertCount)
            throw "change vertCount constant to vertex count";
		edgeCount = 0;
		for (int i = 0; i < vertCount; i++)
		{
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = INF;
		}
	}
	~Johnson()
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

    void bellmanFord(vector<Edge>& edges, int start, int dist[]) {
        // init dist of each vertex to INF & dist of start vertex to 0.
        for (int i = 0; i <= vertCount; i++)
            dist[i] = INF;
        dist[start] = 0;
        // repeat V-1 times
        for (int i = 1; i <= vertCount; i++) {
            for (Edge e : edges) {
                // update dist of dest vertex (if applicable)
                if (dist[e.src] != INF && (dist[e.src] + e.weight) < dist[e.dest])
                    dist[e.dest] = dist[e.src] + e.weight;
            }
        }
        // check if -ve cycle
        for (unsigned i=0; i<edges.size(); i++) {
            Edge e = edges[i];
           if (dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
               throw "Graph contains -ve weight cycle.";
        }
    }
    
    int getMin(int dist[], bool spt[]) {
		int min = INF, minKeyVertex = 0;
		for (int i = 0; i < vertCount; i++) {
			if(spt[i] == false && dist[i] < min) {
				min = dist[i];
				minKeyVertex = i;
			}
		}
		return minKeyVertex;
	}

    void dijkstra(int mat[][vertCount], int start, int dist[]) {
		// mst array to check if vertex is in mst.
		bool spt[vertCount];
		// parent array to keep parent of each vertex
		int parent[vertCount];
		// initially no vertex in mst, key of all vertices as INF and parent of each vertex = -1
		for(int i=0; i<vertCount; i++) {
			spt[i] = false;
			dist[i] = INF;
			parent[i] = -1;
		}
		// key of start vertex should be 0
		dist[start] = 0;
		
		int sptVertCount = 0;
		// repeat until all vertices are added into mst
		while(sptVertCount < vertCount) {
			// get vertex with min key, which is not in mst
			int u = getMin(dist, spt);
			// add it into mst
			spt[u] = true;
			sptVertCount++;
			// update key & parent of its adjacent vertices (which are not in mst)
			for (int v = 0; v < vertCount; v++) {
				if(mat[u][v] != INF && spt[v] == false && dist[u] + mat[u][v] < dist[v]) {
					dist[v] = dist[u] + mat[u][v];
					parent[v] = u;
				}
			}
		}
	}
    
    void johnson(int dist[][vertCount]) {
    	int distFromNewVertex[vertCount+1];
    	// copy existing edges from graph
    	vector<Edge> edges;
    	for (int i = 0; i < edgeCount; i++)
			edges.push_back(this->edges[i]);

    	int newVertex = vertCount;
    	// add edges to all vertices from new vertex
    	for (int i = 0; i < vertCount; i++)
			edges.push_back(Edge(newVertex, i, 0));

    	bellmanFord(edges, newVertex, distFromNewVertex);
    
        // prepare new adj matrix for dijkstra
    	int mat[vertCount][vertCount];
    	for (int i = 0; i < vertCount; i++)
            for (int j = 0; j < vertCount; j++)
                mat[i][j] = INF;

        // re-weight all edges in the graph and copy adj matrix
    	for (unsigned i=0; i<edges.size(); i++) {
            Edge e = edges[i];
			e.weight += distFromNewVertex[e.src] - distFromNewVertex[e.dest]; 
            if(e.src < vertCount && e.dest < vertCount)
			    mat[e.src][e.dest] = e.weight;
    	}

		// apply Dijkstra on all vertices
    	for(int s=0; s<vertCount; s++) {
    		dijkstra(mat, s, dist[s]);
    		// re-weight distances
    		for (int d = 0; d < vertCount; d++) {
    			if(dist[s][d] != INF)
    				dist[s][d] += distFromNewVertex[d] - distFromNewVertex[s];
    		}
    	}
    }
};

int main() {
    Johnson g(vertCount);
    g.accept();
    g.display();
    int dist[vertCount][vertCount] = {0};
    g.johnson(dist);
    for (int i = 0; i < vertCount; i++) {
        cout << "dist from v" << i << ": ";
        for (int j = 0; j < vertCount; j++) {
            if(dist[i][j] != INF)
                cout << "\t" << dist[i][j];
            else
                cout << "\t#";
        }
        cout << endl;
    }
	return 0;
}


/*
8
1 4 2
3 1 1
1 3 2
0 1 -1
0 2 4
3 2 5
1 2 3
4 3 -3
*/

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

