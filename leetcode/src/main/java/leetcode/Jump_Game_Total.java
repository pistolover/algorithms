package leetcode;

public class Jump_Game_Total {
	public boolean canJump(int[] A) {
		if (A.length == 1 || A.length == 0)
			return true;
		int len = A.length;
		int maxjump = A[0];
		for (int i = 1; i < len; i++) {
			if (maxjump == 0)
				return false;
			maxjump--;
			if (maxjump < A[i]) {
				maxjump = A[i];
			}
			if (maxjump + i > len - 1) {
				return true;
			}
		}
		return true;
	}
}
