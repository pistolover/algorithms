package leetcode;

public class Maximum_Subarray {
	public int maxSubArray(int[] x) {
		if (x == null || x.length == 0)
			return 0;
		int sum = 0;
		int max = x[0];

		for (int i = 0; i < x.length; i++) {
			if (sum >= 0) {
				sum = sum + x[i];
			} else {
				sum = x[i];
			}

			if (sum > max) {
				max = sum;
			}

		}

		// for (int i = 0; i < x.length; i++) {
		// for (int j = i; j < x.length; j++) {
		// for (int k = i; k <= j; k++) {
		// sum = sum + x[k];
		// }
		// if(MaxSum<sum){
		// MaxSum = sum;
		// }
		// sum=0;
		// }
		// }

		return max;
	}
}
