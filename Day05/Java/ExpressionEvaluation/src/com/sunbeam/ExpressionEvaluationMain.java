package com.sunbeam;

import java.util.Stack;

public class ExpressionEvaluationMain {
	
	public static int calculate(int op1, char opr, int op2) {
		switch(opr) {
		case '+':	return op1 + op2;
		case '-':	return op1 - op2;
		case '/':	return op1 / op2;
		case '*':	return op1 * op2;
		case '%':	return op1 % op2;
		case '$':	return (int)Math.pow(op1, op2);
		}
		return 0;
	}
	
	public static int postfixEvaluation(String postfix) {
		//0. create stack to push operands
		Stack<Integer> st = new Stack<Integer>();
		//1. traverse expression from left to right
		for(int i = 0 ; i < postfix.length() ; i++) {
			//2. extract element of ith index
			char ele = postfix.charAt(i);
			//3. if element is operand, push it on stack
			if(Character.isDigit(ele))
				st.push(ele - '0');  // to convert into number
			//4. if element is operator
			else {
				//5. pop two operands from stack
				int op2 = st.pop();
				int op1 = st.pop();
				int res = calculate(op1, ele, op2);
				//6. push result on stack
				st.push(res);
			}
		}
		//7. return result 
		return st.peek();
	}

	public static void main(String[] args) {
		String postfix = "59+4862/-*-173-$+";
		
		int result = postfixEvaluation(postfix);
		System.out.println("Postfix : " + postfix);
		System.out.println("Result : " + result);
	}

}
