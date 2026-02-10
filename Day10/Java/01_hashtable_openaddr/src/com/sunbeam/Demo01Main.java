package com.sunbeam;

import java.util.Scanner;

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
	private Entry[] table;
	// HashTable methods
	public HashTable() {
		table = new Entry[NSLOTS];
	}
	// add key-value pair in table
	public void put(int key, String value) {
		int probe = 1;
		// calc hash index
		int index = hash(key);
		while(true) {
			// if no elem on the index, create new entry on the index
			if(table[index] == null) {
				Entry en = new Entry(key, value);
				table[index] = en;
				return;
			}
			// if key is matched on index (key already present), overwrite value
			Entry en = table[index];
			if(key == en.key) {
				en.value = value;
				return;
			}
			// if key not matched on index, find next index by rehash
			index = rehash(key, probe);
			probe++;
		} // repeat process		
	}
	// find value for given key from the table
	public String get(int key) {
		int probe = 1;
		// calc hash index
		int index = hash(key);
		while(true) {
			// if no elem on the index, return null (no value found)
			if(table[index] == null)
				return null;
			// if key is matched on index, return value
			Entry en = table[index];
			if(key == en.key)
				return en.value;
			// if key not matched on index, find next index by rehash
			index = rehash(key, probe);
			probe++;
		} // repeat process
	}
	// hash fn
	public int hash(int key) {
		int index = key % NSLOTS;
		return index;
	}
	// rehash fn
	public int rehash(int key, int probe) {
		int CONST = 1;
		int index = (hash(key) + probe * CONST) % NSLOTS;
		return index;
	}
}

public class Demo01Main {
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

