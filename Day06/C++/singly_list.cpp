#include <iostream>
using namespace std;

class singly_list; // forward declaration class

class node {
private:
	int data;
	node* next;
public:
	node() {
		data = 0;
		next = NULL;
	}
	node(int val) {
		data = val;
		next = NULL;
	}
	friend class singly_list;
};

class singly_list {
private:
	node* head;
public:
	singly_list() {
		head = NULL;
	}
	~singly_list() {
		del_all();
	}
	void add_first(int val) {
		node* newnode = new node(val);
		newnode->next = head;
		head = newnode;
	}
	void add_last(int val) {
		node* newnode, * trav;
		// alloc new node and init it
		newnode = new node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == NULL)
			head = newnode;
		else {
			// traverse till last node
			trav = head;
			while (trav->next != NULL)
				trav = trav->next;
			// add new node to the next of last node
			trav->next = newnode;
		}
	}
	void add_at_pos(int pos, int val) {
		node* newnode, * trav;
		// special 1 & 2: if list is empty, new node is first node | user give pos = 1 or less than that, new node should be first
		if (head == NULL || pos <= 1) {
			add_first(val);
			return;
		}
		// alloc node and init it
		newnode = new node(val);
		// trav till pos-1
		trav = head;
		for (int i = 1; i < pos - 1; i++) {
			// special3: if pos is beyond size of list, add node at the end.
			if (trav->next == NULL)
				break;
			trav = trav->next;
		}
		// newnode'next to trav's next
		newnode->next = trav->next;
		// previous (trav) 'next to newnode
		trav->next = newnode;
	}
	void display() {
		cout << "LIST: ";
		node* trav = head;
		while (trav != NULL) {
			cout << trav->data << ", ";
			trav = trav->next;
		}
		cout << endl;
	}
	void del_first() {
		node* temp;
		if (head != NULL) {
			temp = head;
			head = head->next;
			delete temp;
		}
	}
	void del_all() {
		while (head != NULL)
			del_first();
	}
	void del_at_pos(int pos) {
		node* temp, *trav;
		int i;
		//special 1 & 2: if list is empty or pos == 1, delete the first node
		if (head == NULL || pos == 1)
			del_first();
		else {
			//1. trav till pos-1
			trav = head;
			for (i = 1; i < pos - 1; i++) {
				//special 3: if pos is beyond size of list, don't do anything.
				if (trav->next == NULL)
					return;
				trav = trav->next;
			}
			//2. get pointer temp to the next node (to be deleted)
			temp = trav->next;
			//3. trav's next to temp's next
			trav->next = temp->next;
			//4. delete temp node
			delete temp;
		}
	}
};

int main() {
	singly_list l1;
	l1.add_last(10);
	l1.add_last(20);
	l1.add_last(30);
	l1.add_first(40);
	l1.add_at_pos(3, 50);
	l1.add_at_pos(1, 60);
	l1.add_at_pos(8, 70);
	l1.display();
	l1.del_first();
	l1.display();
	l1.del_first();
	l1.display();
	l1.del_at_pos(3);
	l1.display();
	l1.del_at_pos(4);
	l1.display();
	l1.del_at_pos(5);
	l1.display();
	//l1.del_all();
	//l1.display();
	return 0;
}




