#include <iostream>
using namespace std;

class avl_tree;

class node
{
private:
	int data;
	node *left, *right;
	int height;
public:
	node(int val=0)
	{
		this->data = val;
		this->left = NULL;
		this->right = NULL;
		this->height = 0;
	}
	int get_height()
	{
		if(this==NULL)
			return -1;
		return this->height;
	}
	int get_bf()
	{
		if(this==NULL)
			return 0;
		return this->left->get_height() - this->right->get_height();
	}
	friend class avl_tree;
};

class avl_tree
{
private:
	node *root;
public:
	avl_tree()
	{
		root = NULL;
	}
	~avl_tree()
	{
		clear(root);
		root = NULL;
	}
	void clear(node *trav)
	{
		if(trav==NULL)
			return;
		clear(trav->left);
		clear(trav->right);
		delete trav;
	}
	node* left_rotate(node *parent, node *axis)
	{
		if(axis==NULL || axis->right==NULL)
			return NULL;
		node *newaxis = axis->right;
		axis->right = newaxis->left;
		newaxis->left = axis;
		if(axis==root)
			root = newaxis;
		else if(axis==parent->left)
			parent->left = newaxis;
		else
			parent->right = newaxis;
		axis->height = max(axis->left->get_height(), axis->right->get_height())+1;
		newaxis->height = max(newaxis->left->get_height(), newaxis->right->get_height())+1;
		return newaxis;
	}
	node* right_rotate(node *parent, node *axis)
	{
		if(axis==NULL || axis->left==NULL)
			return NULL;
		node *newaxis = axis->left;
		axis->left = newaxis->right;
		newaxis->right = axis;
		if(axis==root)
			root = newaxis;
		else if(axis==parent->left)
			parent->left = newaxis;
		else
			parent->right = newaxis;
		axis->height = max(axis->left->get_height(), axis->right->get_height())+1;
		newaxis->height = max(newaxis->left->get_height(), newaxis->right->get_height())+1;
		return newaxis;
	}
	void add(node *parent, node *trav, int val)
	{
		// simple add node -- recursive
		if(val < trav->data)
		{
			if(trav->left == NULL)
				trav->left = new node(val);
			else
				add(trav, trav->left, val);
		}
		else
		{
			if(trav->right == NULL)
				trav->right = new node(val);
			else
				add(trav, trav->right, val);
		}
		// adjust height of node.
		trav->height = max(trav->left->get_height(), trav->right->get_height()) + 1;
		int bf = trav->get_bf();
		// left-left case
		if(bf > 1 && val < trav->left->data)
		{
			cout << "LL " << trav->data << endl;
			right_rotate(parent, trav);
			return;
		}
		// right-right case
		if(bf < -1 && val > trav->right->data)
		{
			cout << "RR " << trav->data << endl;
			left_rotate(parent, trav);
			return;
		}
		// left-right case
		if(bf > 1 && val > trav->left->data)
		{
			cout << "LR " << trav->data << endl;
			left_rotate(trav, trav->left);
			right_rotate(parent, trav);
			return;
		}
		// right-left case
		if(bf < -1 && val < trav->right->data)
		{
			cout << "RL " << trav->data << endl;
			right_rotate(trav, trav->right);
			left_rotate(parent, trav);
			return;
		}
	}
	void add(int val)
	{
		if(root==NULL)
			root = new node(val);
		else
			add(NULL, root, val);
	}
	void preorder(node *trav)
	{
		if(trav==NULL)
			return;
		cout << trav->data << "(" << trav->height << ") ";
		preorder(trav->left);
		preorder(trav->right);
	}
	void preorder()
	{
		cout << "PRE : ";
		preorder(root);
		cout << endl;
	}
	void del(node *parent, node *trav, int val)
	{
		// node not found.
		if(trav==NULL)
			return;
		// delete in left sub-tree
		if(val < trav->data)
			del(trav, trav->left, val);
		// delete in right sub-tree
		else if(val > trav->data)
			del(trav, trav->right, val);
		// if have right child
		else if(trav->left==NULL)
		{
			if(trav==root)
				root = trav->right;
			else if(trav==parent->left)
				parent->left = trav->right;
			else
				parent->right = trav->right;
			delete trav;
			trav = NULL;
		}
		// if have left child
		else if(trav->right==NULL)
		{
			if(trav==root)
				root = trav->left;
			else if(trav==parent->left)
				parent->left = trav->left;
			else
				parent->right = trav->left;
			delete trav;
			trav = NULL;
		}
		// if both child nodes
		else if(trav->left!=NULL && trav->right!=NULL)
		{
			// find inorder successsor
			node *temp = trav->right;
			while(temp->left != NULL)
				temp = temp->left;
			trav->data = temp->data;
			del(trav, trav->right, temp->data);
		}
		// if node is deleted or root is reached
		if(trav == NULL)
			return;
		// update heights
		trav->height = max(trav->left->get_height(), trav->right->get_height()) + 1;
		int bf = trav->get_bf();
		// left-left case
		if(bf > 1 && trav->left->get_bf() >= 0)
		{
			right_rotate(parent, trav);
			return;
		}
		// left-right case
		if(bf > 1 && trav->left->get_bf() < 0)
		{
			left_rotate(trav, trav->left);
			right_rotate(parent, trav);
			return;
		}
		// right-right case
		if(bf < -1 && trav->right->get_bf() <= 0)
		{
			left_rotate(parent, trav);
			return;
		}
		// right-left case
		if(bf < -1 && trav->right->get_bf() > 0)
		{
			right_rotate(trav, trav->right);
			left_rotate(parent, trav);
			return;
		}
	}
	void del(int val)
	{
		del(NULL, root, val);
	}
};

int main()
{
	avl_tree t;
	t.add(9);
	t.add(5);
	t.add(10);
	t.add(0);
	t.add(6);
	t.add(11);
	t.add(-1);
	t.add(1);
	t.add(2);
	t.preorder();
	t.del(10);
	t.preorder();
	return 0;
}
