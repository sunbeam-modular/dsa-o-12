
class AdjMatrixNonWeightedGraph:


	def __init__(self, vCount):
		self.vertCount = vCount
		self.edgeCount = 0
		self.mat = []
		for i in range(self.vertCount):
			self.mat.append([0] * self.vertCount)
	

	def accept(self):
		self.edgeCount = int(input("Enter number of edges: "))
		print(f"Enter {self.edgeCount} edges -- src dest:")
		for i in range(self.edgeCount):
			(src_str, dest_str) = input().split()
			(src,dest) = (int(src_str), int(dest_str))
			self.mat[src][dest] = 1
			self.mat[dest][src] = 1 # for undirected graph src -> dest => dest -> src 


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end='')
			for j in range(self.vertCount):
				print("\t{}".format(self.mat[i][j]), end='')
			print()


	def dfsTrav(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("DFS: ", end='')
		# push start vertex on stack and mark it
		s.append(start)
		marked[start] = True
		# while stack is not empty
		while s:
			# pop vertex from stack and visit it
			trav = s.pop()
			print(f"{trav}, ", end='')
			# put all its non-marked adjacent vertices on the stack & mark them
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
		print()

	
	def dfsIsConnected(self):
		start = 0
		marked = [False] * self.vertCount
		count = 0
		s = list()
		s.append(start)
		marked[start] = True
		count = count + 1
		while s:
			trav = s.pop()
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
					count = count + 1
					if count == self.vertCount:
						return True
		return False


	def dfsSpanningTree(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("DFS Spanning Tree:")
		s.append(start)
		marked[start] = True
		while s:
			trav = s.pop()
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
					print(f"{trav} -> {j}")
		print()


	def bfsTrav(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("BFS: ", end='')
		# push start vertex on queue and mark it
		s.append(start)
		marked[start] = True
		# while queue is not empty
		while s:
			# pop vertex from queue and visit it
			trav = s.pop(0)
			print(f"{trav}, ", end='')
			# put all its non-marked adjacent vertices on the queue & mark them
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
		print()


	def bfsSpanningTree(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("BFS Spanning Tree:")
		s.append(start)
		marked[start] = True
		while s:
			trav = s.pop(0)
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
					print(f"{trav} -> {j}")
		print()


	def bfsPathLength(self, start):
		dist = [0] * self.vertCount
		marked = [False] * self.vertCount
		s = list()
		s.append(start)
		marked[start] = True
		dist[start] = 0
		while s:
			trav = s.pop(0)
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
					dist[j] = dist[trav] + 1
		print("Single Source Path Length: ")
		for i in range(self.vertCount):
			print(f"Distance of v{i} from v{start} = {dist[i]}")
		print()


	def bfsIsBipartite(self):
		NOCOLOR = 0
		YELLOW = 1
		GREEN = -1
		start = 0
		color = [NOCOLOR] * self.vertCount
		s = list()
		s.append(start)
		color[start] = YELLOW
		while s:
			trav = s.pop(0)
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0:
					if color[j]==NOCOLOR:
						s.append(j)
						color[j] = -1 * color[trav]
					elif color[j]==color[trav]:
						return False
		return True


if __name__=="__main__":
	g = AdjMatrixNonWeightedGraph(6)
	g.accept()
	g.display()
	g.dfsTrav(0)
	g.bfsTrav(0)
	if g.dfsIsConnected():
		print("graph is connected.")
	else:
		print("graph is not connected.")
	g.dfsSpanningTree(0)
	g.bfsSpanningTree(0)
	if g.bfsIsBipartite():
		print("graph is bipartite.")
	else:
		print("graph is not bipartite.")
	g.bfsPathLength(0)


"""
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5
"""
