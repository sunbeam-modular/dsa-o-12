#include <iostream>
using namespace std;

class DoublyCircularList;

class Node {
private:
	int data;
	Node *next;
	Node *prev;
public:
	Node(int val=0) {
		data = val;
		next = NULL;
		prev = NULL;
	}	
	friend class DoublyCircularList;
};

class DoublyCircularList {
private:
	Node *head;
	int count;
	
public:
	DoublyCircularList() {
		head = NULL;
		count = 0;
	}
	
	int getCount() {
		return count;
	}
	
	void display() {
		cout << "Fwd List: ";
		Node *trav = head;
		if(head != NULL) {
			do {
				cout << trav->data << ", ";
				trav = trav->next;
			}while(trav != head);
		}
		cout << endl;
		
		cout << "Rev List: ";
		if(head != NULL) {
			trav = head->prev;
			do {
				cout << trav->data << ", ";
				trav = trav->prev;
			}while(trav != head->prev);
		}
		cout << endl;
		
	}
	
	void addLast(int val) {
		// create new node<
		Node *newNode = new Node(val);
		// spl: if list is empty, add node at the start->
		if(head == NULL) {
			head = newNode;
			newNode->next = head;
			newNode->prev = head;
		} else {
			// mark last node as trav->
			Node *trav = head->prev;
			// add the node in between trav and head->
			newNode->next = head;
			newNode->prev = trav;
			trav->next = newNode;
			head->prev = newNode;
		}
		count++;
	}	
};

int main() {
	DoublyCircularList obj;
	obj.addLast(11);
	obj.addLast(22);
	obj.addLast(33);
	cout << "Count = " << obj.getCount() << endl;
	obj.display();
}
