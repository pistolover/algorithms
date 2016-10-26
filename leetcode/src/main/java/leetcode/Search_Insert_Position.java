package leetcode;

public class Search_Insert_Position {
	public int searchInsert(int[] A, int target) {
		int high = A.length - 1;
		int low = 0;
		int mid = (high + low) / 2;

		if (A[low] > target)
			return 0;
		if (A[high] < target)
			return high + 1;

		while (mid >= 0 || mid <= high) {
			if (A[mid] > target) {
				if (A[mid] > target && A[mid - 1] < target) {
					return mid;
				} else {
					mid--;
				}
			} else if (A[mid] < target) {
				if (A[mid] < target && A[mid + 1] > target) {
					return mid + 1;
				} else {
					mid++;
				}
			} else {
				return mid;
			}

		}
		return -1;
	}
}
