package com.sunbeam;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Demo03Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// HashMap<> is most efficient hash table implementation in Java collections
		HashMap<Integer, String> ht = new HashMap<>();
		// put() fn to add key-value in map
		//	internally calls key.hashCode() to decide the slot of the table
		ht.put(89, "v1");
		ht.put(18, "v2");
		ht.put(49, "v3");
		ht.put(58, "v4");
		ht.put(29, "v5");
		ht.put(36, "v6");
		// to traverse all entries in map
		Set<Entry<Integer, String>> entries = ht.entrySet();
		for (Entry<Integer, String> en : entries)
			System.out.println(en.getKey() + " -> " + en.getValue());
		// get() fn to find key from map
		System.out.print("Enter key to find: ");
		int key = sc.nextInt();
		String value = ht.get(key);
		if(value == null)
			System.out.println("Not Found");
		else
			System.out.println("Found: " + value);
	}
}
