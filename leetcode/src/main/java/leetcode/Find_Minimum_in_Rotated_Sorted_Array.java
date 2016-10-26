package leetcode;

public class Find_Minimum_in_Rotated_Sorted_Array {
	public int findMin(int[] num) {
		if (num == null || num.length == 0)
			return 0;
		if (num.length == 1)
			return num[0];

		int len = num.length;
		int min = num[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			if (min <= num[i]) {
				return min;
			} else {
				min = num[i];
				if (i == 0) {
					return min;
				}
			}
		}

		return -1;
	}
}
