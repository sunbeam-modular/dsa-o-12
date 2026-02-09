#include <iostream>
using namespace std;

class SinglyList
{
private:
    class Node
    {
    public:
        int data;
        Node *next;
        Node(int val) : data(val), next(nullptr) {}
    };

    Node *head;

public:
    SinglyList() : head(nullptr) {}

    void display()
    {
        cout << "List: ";
        Node *trav = head;
        while (trav != nullptr)
        {
            cout << trav->data << " -> ";
            trav = trav->next;
        }
        cout << endl;
    }

    void addLast(int val)
    {
        Node *newNode = new Node(val);
        if (head == nullptr)
        {
            head = newNode;
        }
        else
        {
            Node *trav = head;
            while (trav->next != nullptr)
            {
                trav = trav->next;
            }
            trav->next = newNode;
        }
    }

    void printMid()
    {
        if (head != nullptr)
        {
            Node *slow = head;
            Node *fast = head;
            while (fast != nullptr && fast->next != nullptr)
            {
                slow = slow->next;
                fast = fast->next->next;
            }
            cout << "Middle Node: " << slow->data << endl;
        }
        else
        {
            cout << "List is empty!" << endl;
        }
    }

    ~SinglyList()
    {
        Node *trav = head;
        while (trav != nullptr)
        {
            Node *temp = trav;
            trav = trav->next;
            delete temp;
        }
    }
};

int main()
{
    SinglyList list;
    list.addLast(10);
    list.addLast(20);
    list.addLast(30);
    list.addLast(40);
    list.addLast(50);
    list.addLast(60);
    list.addLast(70);
    list.addLast(80);
    list.display();
    list.printMid();
    return 0;
}