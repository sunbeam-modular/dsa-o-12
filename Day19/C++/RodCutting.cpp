#include <iostream>
using namespace std;

#define priceLength 11

int cnt;

int recMaxProfit(int len, int price[]) {
	cnt++;
	if(len == 0)
		return 0;
	int max = 0;
	for(int i=1; i<=len; i++) {
		int profitRight = recMaxProfit(len-i, price);
		if(price[i] + profitRight > max)
			max = price[i] + profitRight;
	}
	return max;
}
	
int memMaxProfit(int len, int price[], int profit[]) {
	cnt++;
	if(len == 0)
		return profit[0] = 0;
	if(profit[len] != 0)
		return profit[len];
	int max = 0;
	for(int i=1; i<=len; i++) {
		int profitRight = memMaxProfit(len-i, price, profit);
		if(price[i] + profitRight > max)
			max = price[i] + profitRight;
	}
	return profit[len] = max;
}
	
int dynMaxProfit(int length, int price[]) {
	cnt++;
	int profit[priceLength];
	profit[0] = 0;
	for(int len=1; len<=length; len++) {
		profit[len] = 0;
		for(int i=1; i<=len; i++) {
			if(price[i] + profit[len-i] > profit[len])
				profit[len] = price[i] + profit[len-i];
		}
	}
	int result = profit[length];
	return result;
}
	
int dynMaxProfitSoln(int length, int price[]) {
	cnt++;
	int profit[priceLength];
	int cut[priceLength];
	profit[0] = 0;
	for(int len=1; len<=length; len++) {
		profit[len] = 0;
		for(int i=1; i<=len; i++) {
			if(profit[len] < price[i] + profit[len-i]) {
				profit[len] = price[i] + profit[len-i];
				cut[len] = i;
			}
		}
	}
	
	cout << "Cuts = ";
	int remain = length;
	while(remain > 0) {
		cout << cut[remain] << " ";
		remain = remain - cut[remain];
	}
	cout << endl;
	int result = profit[length];
	return result;
}
	
int main() {
	int res, rodLen = 8;
	int price[priceLength] = {0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30};

	cnt = 0;
	res = recMaxProfit(rodLen, price);
	cout << "profit: " << res << " with number of calls: " << cnt << endl;

	int profit[priceLength] = {0};
	cnt = 0;
	res = memMaxProfit(rodLen, price, profit);
	cout << "profit: " << res << " with number of calls: " << cnt << endl;

	cnt = 0;
	res = dynMaxProfit(rodLen, price);
	cout << "profit: " << res << " with number of calls: " << cnt << endl;
	
	cnt = 0;
	res = dynMaxProfitSoln(rodLen, price);
	cout << "profit: " << res << " with number of calls: " << cnt << endl;

	return 0;
}
