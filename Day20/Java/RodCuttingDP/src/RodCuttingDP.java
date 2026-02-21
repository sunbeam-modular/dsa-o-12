
public class RodCuttingDP {
	static final int[] price= {0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30};
	static int count;
	
	public static int recRodCut(int len) {
		count++;
		if(len == 0)
			return 0;
		int maxProfit = 0;
		for(int cut = 1; cut <= len ; cut++) {
			int leftCutPrice = price[cut];
			int maxProfitRightCut = recRodCut(len-cut);
			int profit = leftCutPrice + maxProfitRightCut;
			if(profit > maxProfit)
				maxProfit = profit;
		}
		return maxProfit;		
	}
	
	public static int memRodCut(int len, int maxProfits[]) {
		count++;
		System.out.println("len = " + len);
		if(maxProfits[len] != 0)
			return maxProfits[len];
		
		if(len == 0) {
			maxProfits[len] = 0;
			return 0;
		}
		int maxProfit = 0;
		for(int cut = 1; cut <= len ; cut++) {
			int leftCutPrice = price[cut];
			int maxProfitRightCut = memRodCut(len-cut, maxProfits);
			int profit = leftCutPrice + maxProfitRightCut;
			if(profit > maxProfit)
				maxProfit = profit;
		}
		maxProfits[len] = maxProfit;
		return maxProfit;		
	}
	
	public static int memRodCut(int len) {
		int maxProfits[] = new int[len+1];
		int res = memRodCut(len, maxProfits);
		return res;
	}
	
	public static int dpRodCut(int length) {
		int maxProfits[] = new int[length + 1];
		maxProfits[0] = 0;
		for(int len = 1 ; len <= length ; len++) {
			int maxProfit = 0;
			for(int cut = 1; cut <= len ; cut++) {
				int leftCutPrice = price[cut];
				int maxProfitRightCut = maxProfits[len-cut];
				int profit = leftCutPrice + maxProfitRightCut;
				if(profit > maxProfit)
					maxProfit = profit;
			}
			maxProfits[len] = maxProfit;
		}
		return maxProfits[length];
	}
	
	public static void main(String[] args) {
		
		int N = 6;
		
		count = 0;
		int profit = recRodCut(N);
		System.out.println("Max profit = " + profit + " for rod of len = " + N + " Recursive calls = " + count);
		
		
		count = 0;
		profit = memRodCut(N);
		System.out.println("Max profit = " + profit + " for rod of len = " + N + " Recursive calls = " + count);
		
		
		count = 1;
		profit = dpRodCut(N);
		System.out.println("Max profit = " + profit + " for rod of len = " + N + " Function calls = " + count);
		
	}

}
