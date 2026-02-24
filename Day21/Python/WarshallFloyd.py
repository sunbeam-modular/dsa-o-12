

class WarshallFloyd:


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


	def warshallFloyd(self):
		# initialize dist matrix with direct dist/edge (adj matrix)
		dist = []
		for s in range(self.vertCount):
			dist.append(self.mat[s].copy())
			# dist from a vertex to itself is 0
			dist[s][s] = 0

		# consider i as intermediate between each pair of s (source) and d (destination)
		for i in range(vertCount):
			for s in range(vertCount):
				for d in range(vertCount):
					# update dist of s-d, if dist via intermediate is lesser than current dist of s-d.
					if(dist[s][i] != self.INF and dist[i][d] != self.INF and dist[s][i] + dist[i][d] < dist[s][d]):
						dist[s][d] = dist[s][i] + dist[i][d]

		return dist


if __name__=="__main__":
	vertCount = 5
	g = WarshallFloyd(vertCount)
	g.accept()
	g.display()
	dist = g.warshallFloyd()
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

