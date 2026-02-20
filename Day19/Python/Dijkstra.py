
class Dijkstra:


	def __init__(self, vCount):
		self.INF = 999
		self.vertCount = vCount
		self.edgeCount = 0
		self.mat = []
		for i in range(self.vertCount):
			self.mat.append([self.INF] * self.vertCount)
	

	def accept(self):
		self.edgeCount = int(input("Enter number of edges: "))
		print(f"Enter {self.edgeCount} edges -- src dest weight:")
		for i in range(self.edgeCount):
			(src_str, dest_str, wt_str) = input().split()
			(src,dest,wt) = (int(src_str), int(dest_str), int(wt_str))
			self.mat[src][dest] = wt
			self.mat[dest][src] = wt # for undirected graph src -> dest => dest -> src 


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end='')
			for j in range(self.vertCount):
				if(self.mat[i][j] == self.INF):
					print("\t#", end='')
				else:
					print("\t{}".format(self.mat[i][j]), end='')
			print()


	def getMin(self, dist, spt):
		min = self.INF
		minKeyVertex = 0
		for i in range(self.vertCount):
			if(spt[i] == False and dist[i] < min):
				min = dist[i]
				minKeyVertex = i
		return minKeyVertex

	
	def dijkstra(self, start, dist):
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
				if(self.mat[u][v] != self.INF and spt[v] == False and dist[u] + self.mat[u][v] < dist[v]):
					dist[v] = dist[u] + self.mat[u][v]
					parent[v] = u
		
		# return min spanning tree edges
		return parent


	def getWeight(self, src, dest):
		return self.mat[src][dest]


if __name__=="__main__":
	vertCount = 9
	g = Dijkstra(vertCount)
	g.accept()
	g.display()

	dist = [0] * vertCount
	src = 0
	parent = g.dijkstra(src, dist)
	print("Dijkstra Shortest Distance: ")
	for i in range(vertCount):
		print(f"dist of {i} from {src} = {dist[i]}")

	print("Dijkstra SPT: ")
	for i in range(vertCount):
		print(f"{parent[i]} -> {i}")
	print()
	
	dest=4
	print(f"Path from {dest} to {src}: ", end='')
	v = dest
	while(v != src):
		print(v, end=' -> ')
		v = parent[v]
	print(src)


"""
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
"""
