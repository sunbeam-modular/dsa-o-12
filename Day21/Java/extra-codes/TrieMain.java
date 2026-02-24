package sunbeam;

import java.util.Scanner;

class Trie {
	static final int MAX = 26;
	static class Node {
		Node[] child; // array of child ref
		boolean end; // end of word		
		//String meaning; // to store meaning of the word
		public Node() {
			end = false;
			child = new Node[MAX]; // all ref are by null.
			for(int i=0; i < MAX; i++)
				child[i] = null;
		}
	}
	
	private Node root;
	public Trie() {
		root = new Node();
	}
	
	public void add(String word) {
		Node trav = root;
		for(int level = 0; level < word.length(); level++) {
			int index = word.charAt(level) - 'a'; // 'a' = ascii 97
			if(trav.child[index] == null)
				trav.child[index] = new Node();
			trav = trav.child[index];
		}
		trav.end = true;
	}
	
	public boolean search(String word) {
		Node trav = root;
		for(int level = 0; level < word.length(); level++) {
			int index = word.charAt(level) - 'a'; // 'a' = ascii 97
			if(trav.child[index] == null)
				return false;
			trav = trav.child[index];
		}
		return trav != null && trav.end;
	}
}

public class TrieMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Trie t = new Trie();
		String[] words = { "sun", "sunbeam", "super", "tree", "trie" };
		for (String word: words)
			t.add(word);
		
		System.out.print("Enter word to find: ");
		String word = sc.next();
		if(t.search(word))
			System.out.println("Word Found");
		else
			System.out.println("Word Not Found");
	
		sc.close();
	}
}
