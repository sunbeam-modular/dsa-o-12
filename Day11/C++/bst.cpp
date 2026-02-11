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

