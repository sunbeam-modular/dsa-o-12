package sunbeam;

public class LongestPalindromeMain {
	public static int max(int x, int y, int z) {
		if(x > y)
			return (x > z) ? x : z;
		else
			return (y > z) ? y : z;
	}
	
	public static void printString(String str, int start, int end) {
		System.out.println(str.substring(start, end+1));
	}

	public static int fnCallCount;
	
	public static int recLongPalindrome(String str, int start, int end, int count) {
		fnCallCount++;
		printString(str, start, end);
		if(start == end)
			return count+1;
		if(start > end)
			return count;
		if(str.charAt(start) == str.charAt(end))
			count = recLongPalindrome(str, start+1, end-1, count+2);
		int count1 = recLongPalindrome(str, start+1, end, 0);
		int count2 = recLongPalindrome(str, start, end-1, 0);
		return max(count, count1, count2);
	}
	
	public static int recLongPalindrome(String str) {
		return recLongPalindrome(str, 0, str.length()-1, 0);
	}
	
	public static int dpLongPalindrome(String str) {
		int len = str.length(), max = 0;
		boolean[][] mat = new boolean[len][len];
		// dp init-- palindrome on length 1
		for(int i = 0; i < len; i++)
			mat[i][i] = true;
		// dp init-- palindrome on length 2
		for(int i = 0; i < len-1; i++)
			mat[i][i+1] = true;
		// check if l=3 onwards string is palindrome or not
		for(int l = 3; l <= len; l++) {
			for(int s = 0; s < len - l + 1; s++) {
				int e = s + l - 1;
				mat[s][e] = (str.charAt(s) == str.charAt(e)) && mat[s+1][e-1];
				if(mat[s][e] && l > max)
					max = l;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String str = "tit for tat radar";
		int result;
		
		fnCallCount = 0;
		result = recLongPalindrome(str);
		System.out.println("Longest Palindrome = " + result + " with fn calls " + fnCallCount);
		
		fnCallCount = 1;
		result = dpLongPalindrome(str);
		System.out.println("Longest Palindrome = " + result + " with fn calls " + fnCallCount);
	}
}

// input: "tit for tat radar"
// palindrome:
	// "tit" = 3
	// "tat" = 3
	// "radar" = 5
	// "r tat r" = 7
// output: 7

//input: saas
	// start       end
	// s ---------- s (2)
	// start+1 end-1
	//    a-----a (2)
	//	end-1 start+1 --> count

// input: madam
	// start                                        end 
	//	m ------------------------------------------ m (2)
	//      start+1                         end-1
	//	       a ---------------------------- a (2)
	//                 start+1=end-1
	//                       d  (1)      --> count
	
// input: aradar
	// start                                        end
	//	a ------------------------------------------- r ---> not palindrome
	//		start+1									end
	//		   r ------------------------------------ r

//input: madamx
	// start                                        end
	//	m ------------------------------------------- x ---> not palindrome
	// start                                   end-1
	//	m -------------------------------------- m

//input: titfortat
	// start                                       end
	//   t ---------------------------------------- t
	//       start+1                         end-1
	//           i --------------------------- a        ---> not palindrome
	// start ------------------------------- end-1
	// 	      start+1 -----------------------------end




























