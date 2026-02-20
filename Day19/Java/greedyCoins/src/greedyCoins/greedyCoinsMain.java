package greedyCoins;

public class greedyCoinsMain {
	public static int findMinimumNumOfCoins(int amount, int coins[]) {
		int count = 0;
		for(int i = 0 ; i < coins.length ; i++) {
			if(amount == 0)
				break;
			if(coins[i] <= amount) {
				count++;				// increment count
				amount = amount - coins[i];	// reduce amount
				i--; 		// to reconsider coin again
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int coins[] = {50, 20, 10, 5, 2, 1};
		int amount = 10;
		int count = findMinimumNumOfCoins(amount, coins);
		System.out.println("Count = " + count);
		
	}

}
