package leetcode;

public class Find_Peak_Element {
	public int findPeakElement(int[] num) {
		int len = num.length;
		if (num == null || len < 2) {
			return 0;
		}

		if (len == 2 && num[0] < num[1]) {
			return 1;
		}

		for (int i = 1; i < len; i++) {
			if (i + 1 < len && num[i] > num[i - 1] && num[i] > num[i + 1]) {
				return i;
			}
		}

		if (num[0] > num[1]) {
			return 0;
		}

		if (num[len - 1] > num[len - 2]) {
			return len - 1;
		}

		return 0;
	}
}
