#include <stdio.h>

// factorial: n! = 1 * 2 * 3 * 4 * ... * n
int main() {
	int res, n, i;
	printf("enter a number: ");
	scanf("%d", &n);
	res = 1;
	for (i = 1; i <= n; i++) {
		res = res * i;
	}
	printf("result = %d\n", res);
	return 0;
}

