#include <iostream>
#include <stack>
using namespace std;

int pri(char op) {
	switch (op)
	{
	case '$':	return 10;
	case '*':	return 7;
	case '/':	return 7;
	case '+':	return 2;
	case '-':	return 2;
	}
	return 0;
}

int calc(int a, int b, char op) {
	switch (op)
	{
	case '$':	return (int)pow(a, b);
	case '*':	return a * b;
	case '/':	return a / b;
	case '+':	return a + b;
	case '-':	return a - b;
	}
	return 0;
}

void infix_to_postfix(char in[], char post[]) {
	int i, k = 0;
	//0. create stack of operator
	stack<char> s;
	//1. process each sym in infix from left to right
	for (i = 0; in[i] != '\0'; i++) {
		//2. if sym in operand, append to postfix string
		if (isdigit(in[i]))
			post[k++] = in[i];
		//4. if opening '(', push on stack.
		else if (in[i] == '(')
			s.push(in[i]);
		//5. if closing ')', pop all ops from stack and append to postfix until '(' is found on stack. Also pop & discard '(' from stack.
		else if (in[i] == ')') {
			while (s.top() != '(') {
				post[k++] = s.top();
				s.pop();
			}
			s.pop();
		}
		//3. if sym is operator, push on the stack.
		else { // operator
			//3a. can push current op only if its priority is higher than topmost op in stack.
			//3b. if priority of topmost op is greater or equal, pop it and append to postfix.
			while (!s.empty() && pri(s.top()) >= pri(in[i])) {
				post[k++] = s.top();
				s.pop();
			}
			s.push(in[i]);
		}
	}
	//6. at the end pop all ops from stack and append to postfix.
	while (!s.empty()) {
		post[k++] = s.top();
		s.pop();
	}
	post[k] = '\0';
}

void infix_to_prefix(char in[], char pre[]) {
	int i, k = 0;
	//0. create stack of operator
	stack<char> s;
	//1. process each sym in infix from right to left
	for (i = strlen(in)-1; i>=0; i--) {
		//2. if sym in operand, append to prefix string
		if (isdigit(in[i]))
			pre[k++] = in[i];
		//4. if closing ')', push on stack.
		else if (in[i] == ')')
			s.push(in[i]);
		//5. if opening '(', pop all ops from stack and append to prefix until ')' is found on stack. Also pop & discard ')' from stack.
		else if (in[i] == '(') {
			while (s.top() != ')') {
				pre[k++] = s.top();
				s.pop();
			}
			s.pop();
		}
		//3. if sym is operator, push on the stack.
		else { // operator
			//3a. can push current op only if its priority is higher or equal than topmost op in stack.
			//3b. if priority of topmost op is greater, pop it and append to prefix.
			while (!s.empty() && pri(s.top()) > pri(in[i])) {
				pre[k++] = s.top();
				s.pop();
			}
			s.push(in[i]);
		}
	}
	//6. at the end pop all ops from stack and append to prefix.
	while (!s.empty()) {
		pre[k++] = s.top();
		s.pop();
	}
	pre[k] = '\0';
	//7. reverse the prefix string
	_strrev(pre);
}

int postfix_eval(char post[]) {
	//0. create stack of operands
	stack<int> s;
	//1. process sym in postfix from left to right
	for (int i = 0; post[i] != '\0'; i++) {
		//2. if operand is found, push on stack
		if (isdigit(post[i]))
			s.push(post[i] - '0'); // '5' != 5
		//3. if operator is found, pop two values from stack, calc result and push it on stack
		else {
			int b = s.top();	s.pop();
			int a = s.top();	s.pop();
			int r = calc(a, b, post[i]);
			s.push(r);
		}
	}
	//4. at the end, pop last value from stack & return (result)
	int r = s.top();	s.pop();
	return r;
}

int prefix_eval(char pre[]) {
	//0. create stack of operands
	stack<int> s;
	//1. process sym in prefix from right to left
	for (int i = strlen(pre) - 1; i >= 0; i--) {
		//2. if operand is found, push on stack
		if (isdigit(pre[i]))
			s.push(pre[i] - '0'); // '5' != 5
		//3. if operator is found, pop two values from stack, calc result and push it on stack
		else {
			int a = s.top();	s.pop();
			int b = s.top();	s.pop();
			int r = calc(a, b, pre[i]);
			s.push(r);
		}
	}
	//4. at the end, pop last value from stack & return (result)
	int r = s.top();	s.pop();
	return r;
}

void postfix_to_infix(char post[], char in[]) {
	stack<string> s;
	int i;
	for (i = 0; post[i] != '\0'; i++) {
		if (isdigit(post[i]))
			s.push(string(1, post[i]));
		else {
			string r = "(";
			string b = s.top(); s.pop();
			string a = s.top(); s.pop();
			r += a;
			r.push_back(post[i]);
			r += b;
			r += ")";
			s.push(r);
		}
	}
	string r = s.top(); s.pop();
	strcpy(in, r.c_str());
}

void postfix_to_prefix(char post[], char pre[]) {
	stack<string> s;
	int i;
	for (i = 0; post[i] != '\0'; i++) {
		if (isdigit(post[i]))
			s.push(string(1, post[i]));
		else {
			string r(1, post[i]);
			string b = s.top(); s.pop();
			string a = s.top(); s.pop();
			r += a;
			r += b;
			s.push(r);
		}
	}
	string r = s.top(); s.pop();
	strcpy(pre, r.c_str());
}


int main() {
	char infix[32] = "5+9-4*(8-6/2)+1$(7-3)";
	char postfix[32] = "", prefix[32] = "";
	int res;
	cout << "infix : " << infix << endl;
	infix_to_postfix(infix, postfix);
	cout << "postfix : " << postfix << endl;
	res = postfix_eval(postfix);
	cout << "postfix result: " << res << endl;
	infix_to_prefix(infix, prefix);
	cout << "prefix : " << prefix << endl;
	res = prefix_eval(prefix);
	cout << "prefix result: " << res << endl << endl;

	char postfix2[32] = "234*+", infix2[32] = "", prefix2[32] = "";
	postfix_to_infix(postfix2, infix2);
	cout << "postfix : " << postfix2 << endl;
	cout << "infix : " << infix2 << endl;
	postfix_to_prefix(postfix2, prefix2);
	cout << "prefix : " << prefix2 << endl;
	return 0;
}