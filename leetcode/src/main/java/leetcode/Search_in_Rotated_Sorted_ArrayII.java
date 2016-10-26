package leetcode;

import java.util.Arrays;

public class Search_in_Rotated_Sorted_ArrayII {
	public boolean search(int[] A, int target) {

		Arrays.sort(A);

		if (A.length == 1) {
			if (A[0] == target) {
				return true;
			} else {
				return false;
			}
		}

		int fis = 0;
		int end = A.length - 1;
		while (fis <= end) {
			int mid = fis + ((end - fis) >> 1);
			if (A[mid] > target) {
				end = mid - 1;
			} else if (A[mid] < target) {
				fis = mid + 1;
			} else if (A[mid] == target) {
				return true;
			}
		}
		return false;
	}
}
