#include <iostream>
using namespace std;

class circular_list; // forward declaration class

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
	friend class circular_list;
};

class circular_list {
private:
	node* head;
public:
	circular_list() {
		head = NULL;
	}
	void display() {
		cout << "LIST: ";
		if (head != NULL) {
			node* trav = head;
			do {
				cout << trav->data << ", ";
				trav = trav->next;
			} while (trav != head);
		}
		cout << endl;
	}
	void add_last(int val) {
		node* newnode, * trav;
		// alloc new node and init it
		newnode = new node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == NULL) {
			head = newnode;
			newnode->next = head;
		} else {
			// traverse till last node
			trav = head;
			while (trav->next != head)
				trav = trav->next;
			// newnode's next to head
			newnode->next = head;
			// add new node to the next of last node
			trav->next = newnode;
		}
	}
	void add_first(int val) {
		node* newnode, * trav;
		// alloc new node and init it
		newnode = new node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == NULL) {
			head = newnode;
			newnode->next = head;
		}
		else {
			// traverse till last node
			trav = head;
			while (trav->next != head)
				trav = trav->next;
			// newnode's next to head
			newnode->next = head;
			// add new node to the next of last node
			trav->next = newnode;
		}
		// head point to newnode
		head = newnode;
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
			// special3: if pos is beyond size of list
			if (trav->next == head)
				break;
			trav = trav->next;
		}
		// newnode'next to trav's next
		newnode->next = trav->next; // head
		// previous (trav) 'next to newnode
		trav->next = newnode;
	}
	void del_first() {
		node* trav, * temp;
		// special1: if list empty, return
		if (head == NULL)
			return;
		// special2: if single node, delete it.
		if (head->next == head) {
			delete head;
			head = NULL;
			return;
		}
		// traverse till last node (trav)
		trav = head;
		while (trav->next != head)
			trav = trav->next;
		// take first node addr in temp
		temp = head;
		// take head to next node
		head = head->next;
		// last node (trav) point to new head
		trav->next = head;
		// delete the temp
		delete temp;
	}
	void del_at_pos(int pos) {
		node* temp, * trav;
		int i;
		//special 1 & 2: if list is empty or pos == 1, delete the first node
		if (head == NULL || pos == 1)
			del_first();
		else {
			//1. trav till pos-1
			trav = head;
			for (i = 1; i < pos - 1; i++) {
				//special 3: if pos is beyond size of list
				if (trav->next == head)
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
	~circular_list() {
		del_all();
	}
	void del_all() {
		// convert circular list to linear list
		if (head != NULL) {
			node* temp = head;
			head = head->next;
			temp->next = NULL;
		}
		// delete singly linear list
		while (head != NULL) {
			node* temp = head;
			head = head->next;
			delete temp;
		}
	}
};

int main() {
	circular_list l1;
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
	l1.del_all();
	l1.display();
	return 0;
}




