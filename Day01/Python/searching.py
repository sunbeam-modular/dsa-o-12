

def linearSearch(a, key):
    for i in range(len(a)):
        if (a[i] == key):
            return i
    return -1


def binarySearch(arr, key):
    left = 0
    right = len(arr)-1
    while (left <= right):
        mid = int((left + right) / 2)
        if (key == arr[mid]):
            return mid
        if (key < arr[mid]):
            right = mid - 1
        else:  # if(key > arr[mid])
            left = mid + 1
    return -1  # element not found


def main():
    arr = [11, 22, 33, 44, 55, 66, 77, 88, 99]
    num = int(input("Enter number to be searched: "))
    # index = linearSearch(arr, num)
    index = binarySearch(arr, num)
    if (index == -1):
        print(f"Element {num} is not found.")
    else:
        print(f"Element {num} found at {index}")


if __name__ == "__main__":
    main()
