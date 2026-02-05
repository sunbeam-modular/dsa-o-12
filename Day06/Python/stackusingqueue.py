from collections import deque

class StackUsingQueue:
    def __init__(self):
        self.main = deque()
        self.temp = deque()

    def push(self, val):
        while self.main:
            self.temp.append(self.main.popleft())
        self.main.append(val)
        while self.temp:
            self.main.append(self.temp.popleft())

    def pop(self):
        return self.main.popleft()

    def isEmpty(self):
        return len(self.main) == 0

if __name__ == "__main__":
    s = StackUsingQueue()
    s.push(10)
    s.push(20)
    s.push(30)
    s.push(40)
    s.push(50)
    while not s.isEmpty():
        print("Popped:", s.pop())  # 50, 40, 30, 20, 10
