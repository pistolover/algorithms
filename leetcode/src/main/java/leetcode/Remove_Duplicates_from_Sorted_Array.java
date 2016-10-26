package leetcode;

public class Remove_Duplicates_from_Sorted_Array {
	public int removeDuplicates(int[] A) {
		int len = A.length;
		if (len == 0)
			return 0;
		int count = 1;
		for (int i = 1; i < len; i++) {
			if (A[i] == A[i - 1]) {
				continue;
			} else {
				A[count] = A[i];
				count++;
			}
		}
		return count;

	}
}
