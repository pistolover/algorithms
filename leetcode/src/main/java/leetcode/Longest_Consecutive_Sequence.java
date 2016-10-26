package leetcode;

import java.util.Arrays;

public class Longest_Consecutive_Sequence {
	public int longestConsecutive(int[] num) {
		if (num == null)
			return -1;
		if (num.length == 1)
			return 1;

		Arrays.sort(num);

		int count = 1;
		int max = 1;
		for (int i = 0; i < num.length - 1; i++) {
			if (num[i] + 1 == num[i + 1]) {
				count = count + 1;
				if (i == num.length - 2) {
					return count > max ? count : max;
				}
			} else if (num[i] == num[i + 1]) {
				max = count > max ? count : max;
				continue;
			} else {
				max = max > count ? max : count;
				count = 1;
			}

		}

		return max;
	}
}
