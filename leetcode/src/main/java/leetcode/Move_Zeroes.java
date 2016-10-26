package leetcode;

public class Move_Zeroes {

	public static void main(String[] args) {
		moveZeroes(new int[] { 0, 1, 0, 3, 12 });
	}

	public static void moveZeroes(int[] nums) {
		if (nums == null)
			return;

		int i = 0, j = 0;

		while (j < nums.length) {
			if (i != j) {
				if (nums[j] != 0) {
					nums[i] = nums[j];
					nums[j] = 0;
					i++;
					j++;
				} else {
					j++;
				}
			} else {
				if (nums[i] == 0) {
					j++;
				} else {
					i++;
					j++;
				}
			}
		}

		System.err.println(nums);
	}
}
