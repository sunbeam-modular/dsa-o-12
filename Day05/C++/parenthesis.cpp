#include <iostream>
#include <stack>
using namespace std;

bool check_parenthesis(char in[]) {
	char opening[] = "([{<", closing[] = ")]}>";
	stack<char> s;
	int i, open_index, close_index;
	//1. proces each sym from left to right
	for (i = 0; in[i] != '\0'; i++) {
		//2. if it is opening par then push on stack
		open_index = strchr(opening, in[i]) - opening;
		if (open_index >= 0)
			s.push(in[i]);
		else {
			//3. if it is closing par then check if it corresponds to topmost par on stack
			close_index = strchr(closing, in[i]) - closing;
			if (close_index >= 0) {
				if (s.empty()) // extra closing paranthesis
					return false;
				open_index = strchr(opening, s.top()) - opening;
				if (open_index == close_index)
					s.pop();
				else
					return false; // mismaptch of parenthesis
			}
		}
	}
	//4. if still some par on stack, invalid expr
	if (!s.empty()) // extra opening paranthesis
		return false;
	return true;
}

int main() {
	char expr[] = "5 + ( [ 9 - 4 ] * ( 8 - { 6 / 2 } ) )";
	bool success = check_parenthesis(expr);
	if (success)
		cout << "Valid expression!" << endl;
	else
		cout << "Invalid expression!" << endl;
	cout << "expr : " << expr << endl;
	return 0;
}

