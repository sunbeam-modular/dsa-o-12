#include <iostream>
#include <vector>
#include <list>
#include <unordered_map>
using namespace std;

class HashTable
{
private:
    class Entry
    {
    public:
        int key;
        string value;
        Entry(int k, string v) : key(k), value(v) {}
    };

    static const int NSLOTS = 10;
    vector<list<Entry>> table;

public:
    HashTable() : table(NSLOTS) {}

    void put(int key, string value)
    {
        // find slot of the table where key is to be added
        int index = hash(key);
        // get the bucket in the slot and search key in all entries in that bucket
        for (auto &en : table[index])
        {
            // if key is found, overwrite the value
            if (en.key == key)
            {
                en.value = value;
                return;
            }
        }
        // if key not found (in bucket), create a new entry and add in that bucket
        table[index].emplace_back(key, value);
    }

    string get(int key)
    {
        // find slot of the table where key might be present
        int index = hash(key);
        // get the bucket in the slot and search key in all entries in that bucket
        for (auto &en : table[index])
        {
            // if key is found, return the value
            if (en.key == key)
            {
                return en.value;
            }
        }
        // if key not found (in bucket), return empty string
        return "";
    }

    int hash(int key)
    {
        return key % NSLOTS;
    }
};

int main()
{
    // Test Demo02Main (Chaining)
    HashTable ht;
    ht.put(89, "v1");
    ht.put(18, "v2");
    ht.put(49, "v3");
    ht.put(58, "v4");
    ht.put(29, "v5");
    ht.put(36, "v6");
    ht.put(58, "item4"); // if key duplicates, the value should overwrite

    int key;
    cout << "Enter key to find: ";
    cin >> key;
    string value = ht.get(key);
    if (value.empty())
    {
        cout << "Not Found" << endl;
    }
    else
    {
        cout << "Found: " << value << endl;
    }
    return 0;
}