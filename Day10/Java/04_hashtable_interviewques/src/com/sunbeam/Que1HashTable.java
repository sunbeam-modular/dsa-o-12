package com.sunbeam;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

// from given string return the char repeated max number of times
public class Que1HashTable {
	public static char maxRepeatedChar(String str) {
		// count num of occurrences for each char
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			Integer cnt = map.get(ch);
			if(cnt == null)
				cnt = 1;
			else
				cnt = cnt + 1;
			map.put(ch, cnt);
		}
		System.out.println(map);
		// find char with max occurrences
		int maxCount = 0;
		char maxChar = '\0';
		for (Entry<Character, Integer> en : map.entrySet()) {
			if(en.getValue() > maxCount) {
				maxCount = en.getValue();
				maxChar = en.getKey();
			}
		}
		return maxChar;
	}
	public static void main(String[] args) {
		String str = "ABCDZBXPYDS";
		char ch = maxRepeatedChar(str);
		System.out.println("Max Repeated Char: " + ch);
	}
}
