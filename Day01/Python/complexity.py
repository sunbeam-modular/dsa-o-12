
def factorial(n):
	res = 1
	for i in range(1,n+1):
		res = res * i
	return res


def print_matrix(mat):
	for i in range(len(mat)):
		for j in range(len(mat)):
			print(mat[i][j], end=' ')
		print()


def decimalToBinary(num):
	while(num > 0):
		print(num % 2, end='')
		num = int(num / 2)
	print()


def printTable(n):
	for i in range(1,10+1):
		print(n * i)


def linearSearch(a, key):
	for i in range(len(a)):
		if(a[i] == key):
			return i
	return -1


def main():
	# result = factorial(5);
	# print(f"5! = {result}")
	# print()

	mat = [[1,2,3], [4,5,6], [7,8,9]]
	print_matrix(mat)
	print()

	# decimalToBinary(10)
	# print()

	# printTable(28)
	# print()
	
	# arr = [ 88, 33, 66, 99, 11, 77, 22, 55, 11 ]
	# num = int(input("Enter number to be searched: "))
	# index = linearSearch(arr, num)
	# if(index == -1):
	# 	print(f"Element {num} is not found.")
	# else:
	# 	print(f"Element {num} found at {index}");


if __name__ == "__main__":
	main()
