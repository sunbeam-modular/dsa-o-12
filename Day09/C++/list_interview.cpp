#include <iostream>
using namespace std;

class SinglyList {
private:
    class Node {
    public:
        int data;
        Node* next;
        Node(int val) : data(val), next(nullptr) {}
    };

    Node* head;

public:
    SinglyList() : head(nullptr) {}

    void display() {
        cout << "List: ";
        Node* trav = head;
        while (trav != nullptr) {
            cout << trav->data << " -> ";
            trav = trav->next;
        }
        cout << endl;
    }

    void addLast(int val) {
        Node* newNode = new Node(val);
        if (head == nullptr) {
            head = newNode;
        } else {
            Node* trav = head;
            while (trav->next != nullptr) {
                trav = trav->next;
            }
            trav->next = newNode;
        }
    }

    void selectionSort() {
        for (Node* i = head; i != nullptr; i = i->next) {
            for (Node* j = i->next; j != nullptr; j = j->next) {
                if (i->data > j->data) {
                    int temp = i->data;
                    i->data = j->data;
                    j->data = temp;
                }
            }
        }
    }

    void revDisplay(Node* trav) {
        if (trav == nullptr) {
            return;
        }
        revDisplay(trav->next);
        cout << trav->data << ", ";
    }

    void revDisplay() {
        cout << "Rev : ";
        revDisplay(head);
        cout << endl;
    }

    ~SinglyList() {
        Node* trav = head;
        while (trav != nullptr) {
            Node* temp = trav;
            trav = trav->next;
            delete temp;
        }
    }
};

int main() {
    SinglyList list;
    list.addLast(30);
    list.addLast(50);
    list.addLast(10);
    list.addLast(40);
    list.addLast(20);
    list.display(); // 30 -> 50 -> 10 -> 40 -> 20 -> 
    list.selectionSort();
    list.display(); // 10 -> 20 -> 30 -> 40 -> 50 -> 
    list.revDisplay();
    return 0;
}