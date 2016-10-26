package leetcode;

public class Best_Time_to_Buy_and_Sell_StockII {
	public int maxProfit(int[] x) {
		if (x == null || x.length <= 1)
			return 0;

		int min = x[0];
		int profit = 0;

		for (int i = 0; i < x.length; i++) {
			if (min > x[i]) {
				min = x[i];
			} else {
				profit += x[i] - min;
				min = x[i];
			}
		}
		return profit;
	}
}
