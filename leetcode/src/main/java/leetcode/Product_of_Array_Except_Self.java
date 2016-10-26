package leetcode;

public class Product_of_Array_Except_Self {

	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;

		int sum = 1;
		int sum0 = 1;

		int zorecut = 0;
		for (int i : nums) {
			if (i == 0) {
				zorecut++;
			}
			if (i != 0) {
				sum0 = sum0 * i;
			}
			sum = sum * i;
		}

		if (zorecut >= 2) {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = 0;
			}
			return nums;
		}

		if (sum == 0) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != 0) {
					nums[i] = 0;
				} else {
					nums[i] = sum0;
				}
			}
			return nums;

		} else {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = sum / nums[i];
			}
			return nums;
		}

	}

}
