package leetcode;

public class Best_Time_to_Buy_and_Sell_Stock {
	public int maxProfit(int[] prices) {
		int len = prices.length;
		if (prices == null || len <= 1)
			return 0;
		int curr = prices[0];
		int profit = 0;
		for (int i = 0; i < len; i++) {
			int nex = prices[i];
			profit = nex - curr > profit ? nex - curr : profit;
			curr = curr < nex ? curr : nex;
		}
		return profit;
	}

}
