#include <iostream>
#include <stack>
using namespace std;

class bstree;

class node {
private:
	int data;
	node* left;
	node* right;
	bool visited;
public:
	node() {
		data = 0;
		left = NULL;
		right = NULL;
		visited = false;
	}
	node(int val) {
		data = val;
		left = NULL;
		right = NULL;
		visited = false;
	}
	int get_data() {
		return data;
	}
	friend class bstree;
};

class bstree {
private:
	node* root;
public:
	bstree() {
		root = NULL;
	}
	void add(int val) {
		// if tree is empty, root is new node
		if (root == NULL)
			root = new node(val);
		// else add node in tree (root)
		else
			add(root, val);
	}
	void add(node* trav, int val) {
		// if val < trav->data, 
		if (val < trav->data) {
			// if no left sub tree present, add new node to left
			if (trav->left == NULL) {
				trav->left = new node(val);
				return;
			}
			// add to left subtree
			add(trav->left, val);
		}
		// if val >= trav->data
		else {
			// if no left sub tree present, add new node to right
			if (trav->right == NULL) {
				trav->right = new node(val);
				return;
			}
			// add to right subtree
			add(trav->right, val);
		}
	}

	node* binsearch(node* trav, int key) {
		// if sub-tree is empty, return
		if (trav == NULL)
			return NULL;
		// compare key with current root (trav). if equal, return address of node
		if (key == trav->data)
			return trav;
		// if key is smaller than current root, search in left sub-tree
		if (key < trav->data)
			return binsearch(trav->left, key);
		// else search in right sub-tree
		else
			return binsearch(trav->right, key);
		// using ternary operator
		// return binsearch(key < trav->data ? trav->left : trav->right, key);
	}

	node* binsearch(int val) {
		return binsearch(root, val);
	}

	void preorder(node* trav) {
		if (trav == NULL)
			return;
		cout << trav->data << ", ";
		preorder(trav->left);
		preorder(trav->right);
	}
	void preorder() {
		cout << "PRE: ";
		preorder(root);
		cout << endl;
	}

	void inorder(node* trav) {
		if (trav == NULL)
			return;
		inorder(trav->left);
		cout << trav->data << ", ";
		inorder(trav->right);
	}
	void inorder() {
		cout << "IN : ";
		inorder(root);
		cout << endl;
	}

	void postorder(node* trav) {
		if (trav == NULL)
			return;
		postorder(trav->left);
		postorder(trav->right);
		cout << trav->data << ", ";
	}
	void postorder() {
		cout << "POST: ";
		postorder(root);
		cout << endl;
	}

	void delall(node* trav) {
		if (trav == NULL)
			return;
		delall(trav->left);
		delall(trav->right);
		delete trav;
	}
	void delall() {
		delall(root);
		root = NULL;
	}
	~bstree() {
		delall();
	}

	void preorder_nonrec() {
		stack<node*> s;
		//1. start traversing from root
		node* trav = root;
		cout << "PRE : ";
		while (trav != NULL || !s.empty()) {
			while (trav != NULL) {
				//2. visit trav
				cout << trav->data << ", ";
				//3. if trav has right, push trav->right on stack
				if (trav->right != NULL)
					s.push(trav->right);
				//4. go to left of trav
				trav = trav->left;
			} //5. repeat 2-5 until trav is null
			//6. pop node from stack into trav
			if (!s.empty()) {
				trav = s.top();
				s.pop();
			}
		} //7. repeat 2-6, until trav is null or stack is empty
		cout << endl;
	}

	void inorder_nonrec() {
		stack<node*> s;
		node* trav = root;
		cout << "IN  : ";
		while (trav != NULL || !s.empty()) {
			while (trav != NULL) {
				s.push(trav);
				trav = trav->left;
			}
			if (!s.empty()) {
				trav = s.top(); s.pop();
				cout << trav->data << ", ";
				trav = trav->right;
			}
		}
		cout << endl;
	}

	void postorder_nonrec() {
		stack<node*> s;
		// start trav from root
		node* trav = root;
		cout << "POST: ";
		// while trav is not null or stack is not empty
		while (trav != NULL || !s.empty()) {
			// until null is reached
			while (trav != NULL) {
				// push trav on stack
				s.push(trav);
				// go to trav's left
				trav = trav->left;
			}
			// if stack is not empty
			if (!s.empty()) {
				// pop node from stack into trav
				trav = s.top();	s.pop();
				// if trav's right is present & visited
				if (trav->right == NULL || trav->right->visited==true) {
					// visit trav & mark it as visited
					cout << trav->data << ", ";
					trav->visited = true;
					// make trav null (so that next node will be popped from stack)
					trav = NULL;
				}
				// otherwise
				else {
					// push node on stack
					s.push(trav);
					// go to its right
					trav = trav->right;
				}
			}
		}
		cout << endl;
	}

	node* binsearch(int key, node **pparent) {
		node* trav = root;
		*pparent = NULL;
		while (trav != NULL) {
			if (key == trav->data)
				return trav;
			*pparent = trav;
			if (key < trav->data)
				trav = trav->left;
			else
				trav = trav->right;
		}
		*pparent = NULL;
		return NULL;
	}

};


int main() {
	bstree t;
	t.add(50);
	t.add(30);
	t.add(90);
	t.add(10);
	t.add(40);
	t.add(70);
	t.add(100);
	t.add(20);
	t.add(80);
	t.add(60);
	
	/*
	int val;
	cout << "enter num to search: ";
	cin >> val;
	node* trav = t.binsearch(val);
	if (trav == NULL)
		cout << "Key not found." << endl;
	else
		cout << "Key found: " << trav->get_data() << endl;
	*/
	t.preorder();
	t.inorder();
	t.postorder();
	return 0;
}

