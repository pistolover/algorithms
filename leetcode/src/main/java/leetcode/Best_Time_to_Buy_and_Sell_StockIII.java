package leetcode;

public class Best_Time_to_Buy_and_Sell_StockIII {
	public int maxProfit(int[] x) {
		if (x == null || x.length <= 1)
			return 0;

		int[] right = new int[x.length];
		int[] left = new int[x.length];

		int rmin = x[0];

		for (int i = 1; i < x.length; i++) {
			rmin = Math.min(rmin, x[i]);
			right[i] = Math.max(right[i - 1], x[i] - rmin);
		}

		int lmax = x[x.length - 1];
		left[x.length - 1] = 0;
		for (int i = x.length - 2; i >= 0; i--) {
			lmax = Math.max(lmax, x[i]);
			left[i] = Math.max(left[i + 1], lmax - x[i]);
		}

		int sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum = Math.max(sum, right[i] + left[i]);
		}
		return sum;
	}
}
