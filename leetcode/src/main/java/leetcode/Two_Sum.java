package leetcode;

import java.util.Arrays;

public class Two_Sum {
	public int[] twoSum(int[] A, int target) {
		if (A == null || A.length == 0)
			return null;

		int len = A.length;
		int[] res = new int[2];

		int[] copy = new int[len];
		System.arraycopy(A, 0, copy, 0, len);
		Arrays.sort(copy);

		int i = 0;
		int j = len - 1;
		while (i < j) {
			if (copy[i] + copy[j] == target) {
				// 找到了
				int f1 = 0;
				int f2 = 0;
				for (int m = 0; m < len; m++) {
					if (copy[i] == A[m] && f1 == 0) {
						f1 = m + 1;
					}

					else if (copy[j] == A[m] && f2 == 0) {
						f2 = m + 1;
					}
					if (f1 != 0 && f2 != 0) {
						break;
					}
				}

				res[0] = f1 > f2 ? f2 : f1;
				res[1] = f1 > f2 ? f1 : f2;
				return res;
			} else if (copy[i] + copy[j] > target) {
				j--;
			} else if (copy[i] + copy[j] < target) {
				i++;
			}

		}

		return res;
	}
}
