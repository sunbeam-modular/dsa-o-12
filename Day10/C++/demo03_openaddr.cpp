#include <iostream>
#include <vector>
#include <list>
#include <unordered_map>
using namespace std;

class HashTableOA
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
    vector<Entry *> table;

public:
    HashTableOA() : table(NSLOTS, nullptr) {}

    ~HashTableOA()
    {
        for (auto entry : table)
        {
            delete entry;
        }
    }

    void put(int key, string value)
    {
        int probe = 1;
        // calc hash index
        int index = hash(key);
        while (true)
        {
            // if no elem on the index, create new entry on the index
            if (table[index] == nullptr)
            {
                table[index] = new Entry(key, value);
                return;
            }
            // if key is matched on index (key already present), overwrite value
            if (table[index]->key == key)
            {
                table[index]->value = value;
                return;
            }
            // if key not matched on index, find next index by rehash
            index = rehash(key, probe);
            probe++;
        }
    }

    string get(int key)
    {
        int probe = 1;
        // calc hash index
        int index = hash(key);
        while (true)
        {
            // if no elem on the index, return empty string (no value found)
            if (table[index] == nullptr)
            {
                return "";
            }
            // if key is matched on index, return value
            if (table[index]->key == key)
            {
                return table[index]->value;
            }
            // if key not matched on index, find next index by rehash
            index = rehash(key, probe);
            probe++;
        }
    }

    int hash(int key)
    {
        return key % NSLOTS;
    }

    int rehash(int key, int probe)
    {
        const int CONST = 1;
        return (hash(key) + probe * CONST) % NSLOTS;
    }
};

int main()
{
    int key;
    string value;
    HashTableOA htOA;
    htOA.put(89, "v1");
    htOA.put(18, "v2");
    htOA.put(49, "v3");
    htOA.put(58, "v4");
    htOA.put(29, "v5");
    htOA.put(36, "v6");
    htOA.put(58, "item4"); // if key duplicates, the value should overwrite

    cout << "Enter key to find: ";
    cin >> key;
    value = htOA.get(key);
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