
public class HouseRobbingMain {
	static final int[] hvals = {5, 4, 3, 11, 2};
	
	public static int recRob(int cur) {
		if(cur >= hvals.length)
			return 0;
		int lootIncCur = hvals[cur] + recRob(cur+2);
		int lootSkipCur = recRob(cur+1);
		int lootMax = Integer.max(lootIncCur, lootSkipCur);
		return lootMax;
	}
	
	public static int memRob(int cur, int[] maxLoots) {
		if(cur >= hvals.length) {
			maxLoots[cur] = 0;
			return 0;
		}
		if(maxLoots[cur] != 0)
			return maxLoots[cur];
		int lootIncCur = hvals[cur] + memRob(cur+2, maxLoots);
		int lootSkipCur = memRob(cur+1, maxLoots);
		int lootMax = Integer.max(lootIncCur, lootSkipCur);
		maxLoots[cur] = lootMax;
		return lootMax;
	}
	
	public static int memRobs(int cur) {
		int[] maxLoots = new int[hvals.length+2];
		int res = memRob(cur, maxLoots);
		return res;
	}
	
	public static int dpRobs() {
		int[] maxLoots = new int[hvals.length + 2];
		maxLoots[hvals.length] = maxLoots[hvals.length + 1] = 0;
		for(int cur = hvals.length-1 ; cur >= 0 ; cur--) {
			int lootIncCur = hvals[cur] + maxLoots[cur+2];
			int lootSkipCur = maxLoots[cur+1];
			maxLoots[cur] = Integer.max(lootIncCur, lootSkipCur);
		}
		return  maxLoots[0];
	}
	
	public static void main(String[] args) {
		
		int res1 = recRob(0); // start rec robbing from house 0
		System.out.println("Recursive Max Loot: " + res1);
	
		int res2 = memRobs(0); // start rec robbing from house 0
		System.out.println("Memoized Max Loot: " + res2);
		
		int loot = dpRobs();
		System.out.println("Max loot = " + loot);

	}

}
