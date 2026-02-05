#include <iostream>
#include <queue>
using namespace std;

class StackUsingQueue {
    queue<int> main;
    queue<int> temp;

public:
    void push(int val) {
        while (!main.empty()) {
            temp.push(main.front());
            main.pop();
        }
        main.push(val);
        while (!temp.empty()) {
            main.push(temp.front());
            temp.pop();
        }
    }

    int pop() {
        int val = main.front();
        main.pop();
        return val;
    }

    bool isEmpty() {
        return main.empty();
    }
};

int main() {
    StackUsingQueue s;
    s.push(10);
    s.push(20);
    s.push(30);
    s.push(40);
    s.push(50);
    while (!s.isEmpty()) {
        cout << "Popped: " << s.pop() << endl; // 50, 40, 30, 20, 10
    }
    return 0;
}
