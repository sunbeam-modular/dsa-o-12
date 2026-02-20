
class Prim:


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


	def getMin(self, key, mst):
		min = self.INF
		minKeyVertex = 0
		for i in range(self.vertCount):
			if(mst[i] == False and key[i] < min):
				min = key[i]
				minKeyVertex = i
		return minKeyVertex

	
	def prim(self, start):
		# mst array to check if vertex is in mst (initially no vertex in mst)
		mst = [False] * self.vertCount
		# key array to maintain key of each vertex (initially key of each vertex as INF)
		key = [self.INF] * self.vertCount
		# parent array to keep parent of each vertex (initially parent of each vertex as -1)
		parent = [-1] * self.vertCount
		# key of start vertex should be 0
		key[start] = 0
		
		mstVertCount = 0
		# repeat until all vertices are added into mst
		while(mstVertCount < self.vertCount):
			# get vertex with min key, which is not in mst
			u = self.getMin(key, mst)
			# add it into mst
			mst[u] = True
			mstVertCount = mstVertCount + 1
			# update key & parent of its adjacent vertices (which are not in mst)
			for v in range(self.vertCount):
				if(self.mat[u][v] != self.INF and mst[v] == False and self.mat[u][v] < key[v]):
					key[v] = self.mat[u][v]
					parent[v] = u
		
		# return min spanning tree edges
		return parent


	def getWeight(self, src, dest):
		return self.mat[src][dest]


if __name__=="__main__":
	g = Prim(9)
	g.accept()
	g.display()
	parent = g.prim(0)
	print("Prim MST:")
	sum = 0
	for i in range(len(parent)):
		print(f"{parent[i]} -> {i}")
		if(parent[i] != -1):
			sum = sum + g.getWeight(parent[i], i)
	print(f"\nTotal weight of MST {sum}");



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
