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

    void revList()
    {
        Node *oldhead = head;
        head = nullptr;
        while (oldhead != nullptr)
        {
            Node *temp = oldhead;
            oldhead = oldhead->next;
            temp->next = head;
            head = temp;
        }
    }

    Node *recRevList(Node *trav)
    {
        if (trav->next == nullptr)
        {
            head = trav;
            return trav;
        }
        recRevList(trav->next)->next = trav;
        trav->next = nullptr;
        return trav;
    }

    void recRevList()
    {
        if (head != nullptr)
        {
            recRevList(head);
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
    list.display();
    // list.revList();    // list reverse without recursion
    list.recRevList(); // list reverse using recursion
    list.display();
    return 0;
}