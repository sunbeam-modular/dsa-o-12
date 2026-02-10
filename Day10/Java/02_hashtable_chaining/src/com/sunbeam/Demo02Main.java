package com.sunbeam;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.HashTable.Entry;

class HashTable {
	static class Entry {
		private int key; // roll
		private String value; // name
		public Entry(int key, String value) {
			this.key = key;
			this.value = value;
		}
		// ...
	}
	
	// HashTable fields
	private static final int NSLOTS = 10;
	private List<Entry>[] table;
	// HashTable methods
	public HashTable() {
		table = new List[NSLOTS];
		// initially all buckets are empty
		for (int i = 0; i < table.length; i++)
			table[i] = new LinkedList<>();
	}
	// add key-value pair in table
	public void put(int key, String value) {
		// find slot of the table where key is to be added
		int index = hash(key);
		// get the bucket in the slot and search key in all entries in that bucket
		List<Entry> bucket = table[index];
		for (Entry en : bucket) {
			// if key is found, overwrite the value
			if(en.key == key) {
				en.value = value;
				return;
			}
		}
		// if key not found (in bucket), create a new entry and add in that bucket
		Entry en = new Entry(key, value);
		bucket.add(en);
	}
	// find value for given key from the table
	public String get(int key) {
		// find slot of the table where key might be present
		int index = hash(key);
		// get the bucket in the slot and search key in all entries in that bucket
		List<Entry> bucket = table[index];
		for (Entry en : bucket) {
			// if key is found, return the value
			if(en.key == key)
				return en.value;
		}
		// if key not found (in bucket), return the null
		return null;
	}
	// hash fn
	public int hash(int key) {
		int index = key % NSLOTS;
		return index;
	}
}

public class Demo02Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashTable ht = new HashTable();
		ht.put(89, "v1");
		ht.put(18, "v2");
		ht.put(49, "v3");
		ht.put(58, "v4");
		ht.put(29, "v5");
		ht.put(36, "v6");
		ht.put(58, "item4"); // if key duplicates, the value should overwrite
		
		System.out.print("Enter key to find: ");
		int key = sc.nextInt();
		String value = ht.get(key);
		if(value == null)
			System.out.println("Not Found");
		else
			System.out.println("Found: " + value);
	}
}


