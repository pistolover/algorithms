package leetcode;

public class Single_Number {
	public int singleNumber(int[] A) {
		if (A.length == 0)
			return A[0];
		int x = A[0];
		for (int i = 1; i < A.length; i++) {
			x = x ^ A[i];
		}
		return x;
	}
}
