class TopologicalSort:

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


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end='')
			for j in range(self.vertCount):
				print("\t{}".format(self.mat[i][j]), end='')
			print()


	def dfsTrav(self, trav, marked, order):
		if(marked[trav] == True):
			return
		marked[trav] = True
		order.append(trav)
		for j in range(self.vertCount):
			if(self.mat[trav][j]==1):
				self.dfsTrav(j, marked, order)


	def dfsTraversal(self, start):
		marked = [False] * self.vertCount
		order = list()
		self.dfsTrav(start, marked, order)
		return order


	def dfsTopological(self, trav, marked, stacked, order):
		if(stacked[trav] == True):
			raise Exception("No topological order")	
		if(marked[trav] == True):
			return
		stacked[trav] = True
		marked[trav] = True
		for j in range(self.vertCount):
			if(self.mat[trav][j]==1):
				self.dfsTopological(j, marked, stacked, order)
		order.insert(0, trav)
		stacked[trav] = False


	def dfsTopologicalOrder(self):
		for start in range(self.vertCount):
			marked = [False] * self.vertCount
			stacked = [False] * self.vertCount
			order = list()
			self.dfsTopological(start, marked, stacked, order)
			if(len(order) == self.vertCount):
				return order
		raise Exception("No topological order")


	def kanhTopologicalOrder(self):
		order = list()
		
		q = list()
		indeg = [0] * self.vertCount
		# count in-degrees of all vertices -- O(V*V) or O(E)
		for j in range(self.vertCount):
			for i in range(self.vertCount):
				if(self.mat[i][j] != 0):
					indeg[j] = indeg[j] + 1
			# if indeg of vertex is 0, add it to queue
			if(indeg[j] == 0):
				q.append(j)
		
		# while queue is not empty O(V*V) -- O(V+E)
		while q:
			# visit a vertex
			trav = q.pop(0)
			order.append(trav)
			# reduce in-degree of neighbors
			for j in range(self.vertCount):
				if(self.mat[trav][j] == 1):
					indeg[j] = indeg[j] - 1
					# indeg of vertex become 0, add it to queue
					if(indeg[j] == 0):
						q.append(j)
		
		if(len(order) != vertCount):
			raise Exception("No topological order.")
		
		return order


if __name__=="__main__":
	vertCount = int(input("Enter number of vertices: "))
	g = TopologicalSort(vertCount)
	g.accept()
	g.display()
	res = g.dfsTraversal(0)
	print(f"DFS: {res}")
	res = g.dfsTopologicalOrder()
	print(f"TOP: {res}")
	res = g.kanhTopologicalOrder()
	print(f"TOP: {res}")


"""
5
7
3 4
2 4
2 3
2 1
1 3
0 2
0 1 
"""
