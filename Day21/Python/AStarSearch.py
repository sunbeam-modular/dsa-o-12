
import math
import heapq

INF = 999.99

class Cell:
    
	def __init__(self, r, c):
		self.row = r
		self.col = c
		self.f = self.g = self.h = INF
		self.parent = [-1, -1]


	def __eq__(self, other):
		return self.row == other.row and self.col == other.col


class PriorityQueue:

	def __init__(self, compLessFn):
		self.lessFn = compLessFn
		self.values = list()
	

	def empty(self):
		return len(self.values) == 0


	def pop(self):
		return self.values.pop(0)


	def push(self, val):
		index = 0
		for index in range(len(self.values)):
			ele = self.values[index]
			if self.lessFn(val, ele):
				break
		if len(self.values) > 0 and not self.lessFn(val, self.values[index]):
			index = index + 1
		self.values.insert(index, val)


	def remove(self, val):
		if val in self.values:
			self.values.remove(val)
	

	def find(self, val):
		if val in self.values:
			return self.values.index(val)
		return -1


class AStarSearch:

	def __init__(self, grid):
		self.vertCount = len(grid)
		self.mat = []
		for i in range(self.vertCount):
			self.mat.append(grid[i].copy())
	
	def isValid(self, r, c):
		return (r >= 0 and r < self.vertCount) and (c >= 0 and c < self.vertCount)

	
	def isBlocked(self, r, c):
		return self.mat[r][c] == 0
	

	def isDest(self, r, c, dest):
		return r == dest[0] and c == dest[1]


	def buildPath(self, cells, dest):
		path = list()
		curCell = cells[dest[0]][dest[1]]
		path.append(dest)
		while (curCell.parent[0] != -1 and curCell.parent[1] != -1):
			path.insert(0, curCell.parent)
			curCell = cells[curCell.parent[0]][curCell.parent[1]]
		return path

	
	def calcHeuristic(self, row, col, dest):
		# euclidean
		dx = row-dest[0]
		dy = col-dest[1]
		return math.sqrt(dx*dx + dy*dy)


	def aStarSearch(self, src, dest):
		srcrow = src[0]
		srccol = src[1]
		destrow = dest[0]
		destcol = dest[1]
		if(not self.isValid(srcrow, srccol) or not self.isValid(destrow, destcol)):
			raise Exception("Source or Destination is not valid.")
		if(self.isBlocked(srcrow, srccol) or self.isBlocked(destrow, destcol)):
			raise Exception("Source or Destination is blocked.")
		
		# track if vertex is in path
		onPath = []
		for i in range(self.vertCount):
			onPath.append([False] * self.vertCount)
		
		# initialize cell details
		cells = []
		for i in range(self.vertCount):
			cells.append([])
			for j in range(self.vertCount):
				cells[i].append(Cell(i, j))
		
		# start node f, g & h = 0.0
		startCell = cells[srcrow][srccol]
		startCell.f = startCell.g = startCell.h = 0.0

		# check if src is dest
		if(self.isDest(srcrow, srccol, dest)):
			return self.buildPath(cells, dest)

		# queue for traversal
		q = PriorityQueue(lambda c1,c2: c1.f < c2.f)
		# push start point in queue
		q.push(startCell)
		# repeat while q is not empty
		while not q.empty():
			# pop a point from queue
			curCell = q.pop()
			# add point into path
			onPath[curCell.row][curCell.col] = True
			# list all neighbors points
			neighbors = [
				[curCell.row-1, curCell.col],	# UP
				[curCell.row+1, curCell.col],	# DOWN
				[curCell.row, curCell.col-1],	# LEFT
				[curCell.row, curCell.col+1],	# RIGHT
				[curCell.row-1, curCell.col-1],	# UP-LEFT
				[curCell.row-1, curCell.col+1],	# UP-RIGHT
				[curCell.row+1, curCell.col-1],	# DOWN-LEFT
				[curCell.row+1, curCell.col+1]	# DOWN-RIGHT
			]
			# add possible neighbors in queue
			for adj in neighbors:
				adjrow = adj[0]
				adjcol = adj[1]
				# check if neighbor is valid
				if(self.isValid(adjrow, adjcol)):
					# check if neighbor is dest
					if(self.isDest(adjrow, adjcol, dest)):
						# set parent as current point
						cells[adjrow][adjcol].parent = [curCell.row, curCell.col]
						return self.buildPath(cells, dest)

					# check if not on path & not blocked
					elif(not onPath[adjrow][adjcol] and not self.isBlocked(adjrow, adjcol)):
						# get current & adjacent cell
						adjCell = cells[adjrow][adjcol]
						# calculate new values of f, g & h
						newg = curCell.g + 1.0
						newh = self.calcHeuristic(adjrow, adjcol, dest)
						newf = newg + newh
						# if new f is less than current f of adjacent cell
						if(newf < adjCell.f):
							q.remove(adjCell)
							# update f, g & h of adjacent
							adjCell.g = newg
							adjCell.h = newh
							adjCell.f = newf
							# add adjacent into q
							q.push(adjCell)
							# update parent as current
							adjCell.parent[0] = curCell.row
							adjCell.parent[1] = curCell.col

		return list()

	
def printPath(grid, path):
	for i in range(len(grid)):
		for j in range(len(grid[i])):
			pt = [i, j]
			if pt in path:
				print("*", end=' ')
			else:
				print(grid[i][j], end=' ')
		print()


if __name__=="__main__":
	grid = [ 
		[ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ], 
		[ 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 ], 
		[ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 ], 
		[ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 ], 
		[ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 ], 
		[ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 ], 
		[ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 ], 
		[ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ], 
		[ 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 ],
		[ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 ]
	]
	g = AStarSearch(grid)
	src = [0, 0]
	dest = [9, 9]
	path = g.aStarSearch(src, dest)
	if(len(path) == 0):
		print("No path exists.")
	else:
		print(path)
		print("path visualization")
		printPath(grid, path)



