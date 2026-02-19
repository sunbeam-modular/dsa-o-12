
class UnionFind:


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
			self.mat[dest][src] = wt # for undirected graph src -> dest => dest -> src 
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

	def find(self, v, parent):
		while(parent[v] != -1):
			v = parent[v]
		return v
	

	def union(self, sr, dr, parent):
		parent[sr] = dr
	

	def containsCycle(self):
		parent = [-1] * self.vertCount
		for e in self.edges:
			sr = self.find(e[0], parent)
			dr = self.find(e[1], parent)
			if(sr == dr):
				return True
			self.union(sr, dr, parent)
		return False


if __name__=="__main__":
	g = UnionFind(9)
	g.accept()
	g.display()
	if g.containsCycle():
		print("graph contains cycle")
	else:
		print("graph doesn't contain cycle")


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
