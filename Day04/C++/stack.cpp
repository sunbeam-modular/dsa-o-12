#include <iostream>
using namespace std;

#define SIZE	6

class my_stack {
private:
	int arr[SIZE];
	int top;
public:
	my_stack() {
		top = -1;
		for (int i = 0; i < SIZE; i++)
			arr[i] = 0;
	}
	void push(int ele) {
		top++;
		arr[top] = ele;
	}
	void pop() {
		top--;
	}
	int peek() {
		return arr[top];
	}
	bool is_empty() {
		return top == -1;
	}
	bool is_full() {
		return top == (SIZE - 1);
	}
};

int main() {
	my_stack s;
	int option, val;
	do {
		cout << "0. exit\n1. push\n2. peek\n3. pop\nenter option: ";
		cin >> option;
		switch (option) {
		case 1: // push
			if (s.is_full())
				cout << "stack full." << endl;
			else {
				cout << "enter ele: ";
				cin >> val;
				s.push(val);
			}
			break;
		case 2: // peek
			if (s.is_empty())
				cout << "stack empty." << endl;
			else {
				val = s.peek();
				cout << "next ele : " << val << endl;
			}
			break;
		case 3: // pop
			if (s.is_empty())
				cout << "stack empty." << endl;
			else {
				val = s.peek();
				s.pop();
				cout << "popped ele : " << val << endl;
			}
			break;
		}
	} while (option != 0);
	return 0;
}

