package leetcode;

public class Find_Minimum_in_Rotated_Sorted_ArrayII {
	public int findMin(int[] num) {
		if (num == null || num.length == 0)
			return 0;

		int len = num.length;
		int leftHalfMin = num[0];
		for (int i = 1; i < len; i++) {
			if (leftHalfMin >= num[i]) {
				leftHalfMin = num[i];
			}
		}
		return leftHalfMin;
	}
}
