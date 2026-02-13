#include <iostream>
#include <stack>
#include <queue>
using namespace std;

class bstree;

class node {
private:
	int data;
	node* left;
	node* right;
public:
	node() {
		data = 0;
		left = NULL;
		right = NULL;
	}
	node(int val) {
		data = val;
		left = NULL;
		right = NULL;
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
		// alloc & init new node
		node* newnode = new node(val);
		// if tree is empty (root is null), newnode is root node.
		if (root == NULL) {
			root = newnode;
			return;
		}
		// start traversing from the root
		node* trav = root;
		while (true) {
			// if val is less than trav data
			if (val < trav->data) {
				// if no node in trav left, add newnode there 
				if (trav->left == NULL) {
					trav->left = newnode;
					break;
				}
				//	go to left
				trav = trav->left;
			}
			else { // if val is greater than/equal trav data
				// if no node in trav right, add newnode there
				if (trav->right == NULL) {
					trav->right = newnode;
					break;
				}
				//	go to right
				trav = trav->right;
			}
		}
	}

	node* dfs(int key) {
		stack<node*> s;
		node* trav;
		s.push(root);
		while (!s.empty()) {
			trav = s.top(); s.pop();
			if (key == trav->data)
				return trav;
			if (trav->right != NULL)
				s.push(trav->right);
			if (trav->left != NULL)
				s.push(trav->left);
		}
		return NULL;
	}

	node* bfs(int key) {
		queue<node*> q;
		node* trav;
		q.push(root);
		while (!q.empty()) {
			trav = q.front(); q.pop();
			if (key == trav->data)
				return trav;
			if (trav->left != NULL)
				q.push(trav->left);
			if (trav->right != NULL)
				q.push(trav->right);
		}
		return NULL;
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

	void del(int val) {
		node* temp, * parent, * pred;
		// find the node with its parent
		temp = binsearch(val, &parent);
		// if node is not found, return.
		if (temp == NULL)
			return;
		// if node has both child
		if (temp->left != NULL && temp->right != NULL) {
			// find its pred with pred's parent
			parent = temp;
			pred = temp->left;
			while (pred->right != NULL) {
				parent = pred;
				pred = pred->right;
			}
			// replace temp's data with pred's data
			temp->data = pred->data;
			// consider pred node to be deleted
			temp = pred;
		}
		// if node do not have right child
		if (temp->right == NULL) {
			if (temp == root)
				root = temp->left;
			else if (temp == parent->left)
				parent->left = temp->left;
			else
				parent->right = temp->left;
			delete temp;
		}
		// if node do not have left child
		else if (temp->left == NULL) {
			if (temp == root)
				root = temp->right;
			else if (temp == parent->left)
				parent->left = temp->right;
			else
				parent->right = temp->right;
			delete temp;
		}
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
	t.inorder();
	int val;
	cout << "enter ele to find: ";
	cin >> val;
	
	node* temp = t.dfs(val);
	node* temp = t.bfs(val);
	if (temp == NULL)
		cout << "node not found" << endl;
	else
		cout << "node found: " << temp->get_data() << endl;
	
	node* temp, * parent;
	temp = t.binsearch(val, &parent);
	if(temp == NULL)
		cout << "node not found" << endl;
	else {
		cout << "node found: " << temp->get_data() << endl;
		if(parent != NULL)
			cout << "node parent: " << parent->get_data() << endl;
	}

	t.del(val);

	t.inorder();

	return 0;
}

