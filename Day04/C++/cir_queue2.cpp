#include <iostream>
using namespace std;

#define SIZE	6

class circular_queue {
private:
	int arr[SIZE];
	int front, rear;
public:
	circular_queue() {
		front = -1;
		rear = -1;
		for (int i = 0; i < SIZE; i++)
			arr[i] = 0;
	}
	void push(int ele) {
		rear = (rear + 1) % SIZE;
		arr[rear] = ele;
	}
	void pop() {
		front = (front + 1) % SIZE;
		if (front == rear)
			front = rear = -1;
	}
	int peek() {
		int i = (front + 1) % SIZE;
		return arr[i];
	}
	bool is_empty() {
		return (rear == front && rear == -1);
	}
	bool is_full() {
		return (front == -1 && rear == (SIZE - 1)) || (rear == front && rear != -1);
	}
};

int main() {
	circular_queue q;
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
