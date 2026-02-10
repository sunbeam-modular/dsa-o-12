#include <iostream>
#include <vector>
#include <list>
#include <unordered_map>
using namespace std;

// Que1HashTable.cpp
char maxRepeatedChar(const string &str)
{
    // count num of occurrences for each char
    unordered_map<char, int> map;
    for (char ch : str)
    {
        map[ch]++;
    }
    // find char with max occurrences
    int maxCount = 0;
    char maxChar = '\0';
    for (const auto &pair : map)
    {
        if (pair.second > maxCount)
        {
            maxCount = pair.second;
            maxChar = pair.first;
        }
    }
    return maxChar;
}

int main()
{
    // Test Que1HashTable
    string str = "ABCDZBXPYDS";
    char ch = maxRepeatedChar(str);
    cout << "Max Repeated Char: " << ch << endl;

    return 0;
}