package com.sunbeam;

import java.util.Stack;

public class Tester {
	
	public static boolean isParenthesisBalanced(String expr) {
		String opening = "([{";
		String closing = ")]}";
		//0. create stack to push opening parenthesis
		Stack<Character> st = new Stack<Character>();
		//1. traverse expression from left to right
		for(int i = 0 ; i < expr.length() ; i++) {
			//2. extract character(element) from expression
			char ele = expr.charAt(i);
			//3. if element is opening parenthesis, push it on stack
			if(opening.indexOf(ele) != -1)
				st.push(ele);
			//4. if element is closing parenthsis or operand or operator
			else {
				int closingIndex = closing.indexOf(ele);
				if(closingIndex != -1) {
					if(st.isEmpty())
						return false;
					char open = st.pop();
					int openingIndex = opening.indexOf(open);
					if(closingIndex != openingIndex)
						return false;
				}
			}
		}
		if(!st.isEmpty())
			return false;
		//5. parenthesis are balanced
		return true;
	}

	public static void main(String[] args) {
		String expr = "5+([9-4]*(8-{6/2})";
		
		boolean result = isParenthesisBalanced(expr);
		if(result)
			System.out.println("Parenthesis are balanced");
		else
			System.out.println("Parenthesis are not balanced");

	}

}
