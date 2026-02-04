package com.sunbeam;

import java.util.Stack;

public class ExpressionConversionMain {
	
	public static int priority(char opr) {
		switch(opr) {
		case '$': return 10;
		case '/': return 9;
		case '*': return 9;
		case '%': return 9;
		case '+': return 8;
		case '-': return 8;
		}
		return 0;
	}
	
	public static String infixToPostfix(String infix) {
		//0. create stack to push operators
		Stack<Character> st = new Stack<Character>();
		//0. create postfix expression
		String postfix = "";
		//1. traverse infix expression from left to right
		for(int i = 0 ; i < infix.length() ; i++) {
			//2. extract element from string
			char ele = infix.charAt(i);
			//3. if element is operand then append into expression
			if(Character.isDigit(ele))
				postfix += ele;
			//4. if element is '(', push it on stack
			else if(ele == '(')
				st.push(ele);
			//5. if element is ')', pop from stack and append into expr until '(' bracket
			else if(ele == ')') {
				while(st.peek() != '(')
					postfix += st.pop();
				st.pop();		// to pop '(' and discard both
			}
			//6. if element is operator, check priority of topmost operator
			else {
				while(!st.isEmpty() && priority(st.peek()) >= priority(ele)) {
					postfix += st.pop();
				}
				// push current operator on stack
				st.push(ele);
			}
		}
		//7. pop remaining operators from stack and append into postfix expression
		while(!st.isEmpty())
			postfix += st.pop();
		//8. return postfix expression
		return postfix;
		
	}
	
	public static String infixToPrefix(String infix) {
		//0. create stack to push operators
		Stack<Character> st = new Stack<Character>();
		//0. create prefix expression
		String prefix = "";
		//1. traverse infix expression from right to left
		for(int i = infix.length() - 1 ; i >= 0 ; i--) {
			//2. extract element from string
			char ele = infix.charAt(i);
			//3. if element is operand then append into expression
			if(Character.isDigit(ele))
				prefix += ele;
			//4. if element is ')', push it on stack
			else if(ele == ')')
				st.push(ele);
			//5. if element is '(', pop from stack and append into expr until ')' bracket
			else if(ele == '(') {
				while(st.peek() != ')')
					prefix += st.pop();
				st.pop();		// to pop ')' and discard both
			}
			//6. if element is operator, check priority of topmost operator
			else {
				while(!st.isEmpty() && priority(st.peek()) > priority(ele)) {
					prefix += st.pop();
				}
				// push current operator on stack
				st.push(ele);
			}
		}
		//7. pop remaining operators from stack and append into prefix expression
		while(!st.isEmpty())
			prefix += st.pop();
		//8. reverse prefix expression
		StringBuilder temp = new StringBuilder(prefix);
		temp.reverse();
		
		//8. return prefix expression
		return temp.toString();
		
	}

	public static void main(String[] args) {
		String infix = "5+9-4*(8-6/2)+1$(7-3)";
		
		System.out.println("Infix : " + infix);
		String postfix = infixToPostfix(infix);
		System.out.println("Postfix : " + postfix);
		
		String prefix = infixToPrefix(infix);
		System.out.println("Prefix : " + prefix);
	}

}
