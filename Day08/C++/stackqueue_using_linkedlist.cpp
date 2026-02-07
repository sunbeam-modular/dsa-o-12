#include <iostream>
using namespace std;

// Singly Linear Linked List with head and tail pointer
class SinglyLinearList {
private:
    // static node class
    class Node {
    public:
        int data;
        Node* next;
        Node(int val) : data(val), next(nullptr) {}
    };

    // list fields
    Node* head;
    Node* tail;

public:
    // list methods
    SinglyLinearList() : head(nullptr), tail(nullptr) {}

    // time: O(1) -- same as singly linear list
    void display() {
        cout << "List: ";
        // traversing begins with head node
        Node* trav = head;
        while (trav != nullptr) {
            // for each node print data and go to next node
            cout << trav->data << " -> ";
            trav = trav->next;
        } // repeat until null (end of list) is reached
        cout << endl;
    }

    // time: O(1)
    void addFirst(int val) {
        // create new node
        Node* newNode = new Node(val);
        // if list is empty, new node will be the first & last node
        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        // newnode next will point to first node (head)
        // and mark new node as first node (head)
        else {
            newNode->next = head;
            head = newNode;
        }
    }

    // time: O(1)
    void addLast(int val) {
        // create new node
        Node* newNode = new Node(val);
        // if list is empty, new node will be the first & last node
        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        }
        // newnode added to next of last node (tail)
        // and mark new node as last node (tail)
        else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    // time: O(1)
    int delFirst() {
        int val = 0;
        if (head != nullptr) {
            val = head->data; // get data of node to be deleted (first node)
            Node* temp = head;
            head = head->next;
            if (head == nullptr) // the deleted node was last node
                tail = nullptr;
            delete temp;
        }
        return val;
    }

    bool isEmpty() {
        return head == nullptr;
    }

    ~SinglyLinearList() {
        while (!isEmpty()) {
            delFirst();
        }
    }
};

int main() {
    // Queue using LinkedList -- add last and del first
    //      addition and deletion are done from different ends
    SinglyLinearList q;
    q.addLast(10);
    q.addLast(20);
    q.addLast(30);
    q.addLast(40);
    q.display(); // 10 -> 20 -> 30 -> 40
    while (!q.isEmpty()) {
        int val = q.delFirst();
        cout << "Deleted Elem: " << val << endl; // 10, 20, 30, 40
    }

    // Stack using LinkedList -- add first and del first
    //      addition and deletion are done from same ends
    SinglyLinearList s;
    s.addFirst(10);
    s.addFirst(20);
    s.addFirst(30);
    s.addFirst(40);
    s.display(); // 40 -> 30 -> 20 -> 10
    while (!s.isEmpty()) {
        int val = s.delFirst();
        cout << "Deleted Elem: " << val << endl; // 40, 30, 20, 10
    }

    return 0;
}