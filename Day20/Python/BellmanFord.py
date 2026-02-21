

class BellmanFord:


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


	def bellmanFord(self, start):
		# init dist of start as 0 and others as INF
		dist = [self.INF] * self.vertCount
		dist[start] = 0

		# repeat for V-1 times
		for i in range(1, self.vertCount):
			# for all edges
			for (src,dest,weight) in self.edges:
				# update weights of all dest
				if(dist[src] != self.INF and dist[src] + weight < dist[dest]):
					dist[dest] = dist[src] + weight
		
		#  check for -ve cycle
		for (src,dest,weight) in self.edges:
			# update weights of all dest
			if(dist[src] != self.INF and dist[src] + weight < dist[dest]):
				raise Exception("Graph contains -ve cycle.")
	
		return dist


if __name__=="__main__":
	g = BellmanFord(5)
	g.accept()
	g.display()

	dist = g.bellmanFord(0)
	print("dist from 0 - " + str(dist))


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

