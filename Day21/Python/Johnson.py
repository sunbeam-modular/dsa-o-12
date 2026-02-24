

class Johnson:


	def __init__(self, vCount):
		self.INF = 999
		self.vertCount = vCount
		self.edgeCount = 0
		self.mat = []
		self.edges = []
		for i in range(self.vertCount):
			self.mat.append([self.INF] * self.vertCount)
	

	def accept(self):
		self.edgeCount = int(input("Enter number of edges: "))
		print(f"Enter {self.edgeCount} edges -- src dest weight:")
		for i in range(self.edgeCount):
			(src_str, dest_str, wt_str) = input().split()
			(src,dest,wt) = (int(src_str), int(dest_str), int(wt_str))
			self.mat[src][dest] = wt
			self.edges.append((src,dest,wt))


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end='')
			for j in range(self.vertCount):
				if(self.mat[i][j] == self.INF):
					print("\t#", end='')
				else:
					print("\t{}".format(self.mat[i][j]), end='')
			print()


	def bellmanFord(self, edges, start, dist):
		# init dist of start as 0 and others as INF
		for i in range(len(dist)):
			dist[i] = self.INF
		dist[start] = 0

		# repeat for V-1 times
		for i in range(1, self.vertCount):
			# for all edges
			for (src,dest,weight) in edges:
				# update weights of all dest
				if(dist[src] != self.INF and dist[src] + weight < dist[dest]):
					dist[dest] = dist[src] + weight
		
		#  check for -ve cycle
		for (src,dest,weight) in edges:
			# update weights of all dest
			if(dist[src] != self.INF and dist[src] + weight < dist[dest]):
				raise Exception("Graph contains -ve cycle.")
	

	def getMin(self, dist, spt):
		min = self.INF
		minKeyVertex = 0
		for i in range(self.vertCount):
			if(spt[i] == False and dist[i] < min):
				min = dist[i]
				minKeyVertex = i
		return minKeyVertex

	
	def dijkstra(self, mat, start, dist):
		# spt array to check if vertex is in spt (initially no vertex in spt)
		spt = [False] * self.vertCount
		# dist array to maintain dist of each vertex (initially dist of each vertex as INF)
		for i in range(self.vertCount):
			dist[i] = self.INF
		# parent array to keep parent of each vertex (initially parent of each vertex as -1)
		parent = [-1] * self.vertCount
		# dist of start vertex should be 0
		dist[start] = 0
		
		sptVertCount = 0
		# repeat until all vertices are added into spt
		while(sptVertCount < self.vertCount):
			# get vertex with min dist, which is not in spt
			u = self.getMin(dist, spt)
			# add it into spt
			spt[u] = True
			sptVertCount = sptVertCount + 1
			# update dist & parent of its adjacent vertices (which are not in spt)
			for v in range(self.vertCount):
				if(mat[u][v] != self.INF and spt[v] == False and dist[u] + mat[u][v] < dist[v]):
					dist[v] = dist[u] + mat[u][v]
					parent[v] = u
		
		# return min spanning tree edges
		return parent


	def johnson(self):
		distFromNewVertex = [0] * (self.vertCount+1)
		# copy existing edges from graph
		edges = self.edges.copy()
		newVertex = self.vertCount
		# add edges to all vertices from new vertex
		for i in range(self.vertCount):
			edges.append((newVertex, i, 0))
		self.bellmanFord(edges, newVertex, distFromNewVertex)
		# print(distFromNewVertex)

		# re-weight all edges in the graph and copy adj matrix
		mat = []
		for i in range(self.vertCount):
			mat.append([self.INF] * self.vertCount)

		for i in range(len(edges)):
			(src, dest, weight) = edges[i]
			edges[i] = (src, dest, weight + distFromNewVertex[src] - distFromNewVertex[dest]) 
			if src < self.vertCount and dest < self.vertCount:
				mat[src][dest] = edges[i][2]
    	
		# apply Dijkstra on all vertices
		dist = []
		for i in range(self.vertCount):
			dist.append([self.INF] * self.vertCount)

		for s in range(vertCount):
			self.dijkstra(mat, s, dist[s])
			# re-weight distances
			for d in range(self.vertCount):
				if(dist[s][d] != self.INF):
					dist[s][d] += distFromNewVertex[d] - distFromNewVertex[s]
		
		return dist


if __name__=="__main__":
	vertCount = 5
	g = Johnson(vertCount)
	g.accept()
	g.display()
	dist = g.johnson()
	print("distance matrix: ")
	for i in range(vertCount):
		print(f"v{i}", end='')
		for j in range(vertCount):
			if(dist[i][j] == g.INF):
				print("\t#", end='')
			else:
				print("\t{}".format(dist[i][j]), end='')
		print()


"""
7
3 4 3
2 4 3
2 3 4
2 1 -2
1 3 -1
0 2 5
0 1 6 
"""

