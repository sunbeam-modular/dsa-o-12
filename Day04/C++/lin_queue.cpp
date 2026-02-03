#include <iostream>
using namespace std;

#define SIZE	6

class linear_queue {
private:
	int arr[SIZE];
	int front, rear;
public:
	linear_queue() {
		front = -1;
		rear = -1;
		for (int i = 0; i < SIZE; i++)
			arr[i] = 0;
	}
	void push(int ele) {
		rear++;
		arr[rear] = ele;
	}
	void pop() {
		front++;
	}
	int peek() {
		return arr[front + 1];
	}
	bool is_empty() {
		return front == rear;
	}
	bool is_full() {
		return rear == (SIZE - 1);
	}
};

int main() {
	linear_queue q;
	int option, val;
	do {
		cout << "0. exit\n1. push\n2. peek\n3. pop\nenter option: ";
		cin >> option;
		switch (option) {
		case 1: // push
			if (q.is_full())
				cout << "queue full." << endl;
			else {
				cout << "enter ele: ";
				cin >> val;
				q.push(val);
			}
			break;
		case 2: // peek
			if (q.is_empty())
				cout << "queue empty." << endl;
			else {
				val = q.peek();
				cout << "next ele : " << val << endl;
			}
			break;
		case 3: // pop
			if (q.is_empty())
				cout << "queue empty." << endl;
			else {
				val = q.peek();
				q.pop();
				cout << "popped ele : " << val << endl;
			}
			break;
		}
	} while (option != 0);
	return 0;
}

/*
int main() {
	linear_queue q;
	q.push(11);
	q.push(22);
	q.push(33);
	q.push(44);
	while (!q.is_empty()) {
		int ele = q.peek();
		cout << "ele = " << ele << endl;
		q.pop();
	}
	return 0;
}
*/
 