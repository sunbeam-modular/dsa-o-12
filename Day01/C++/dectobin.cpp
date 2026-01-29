#include <stdio.h>

int main() {
	int n, i;
	printf("enter a number: ");
	scanf("%d", &n);
	while (n > 0) {
		printf("%d", n % 2);
		n = n / 2;
	}
	return 0;
}

