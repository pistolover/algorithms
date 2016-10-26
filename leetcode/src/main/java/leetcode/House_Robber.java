package leetcode;

/**
 * 
 * @author liqq
 *
 */
public class House_Robber {

	public static int rob(int[] nums) {

		if (nums == null || nums.length == 0)
			return 0;

		int len = nums.length;
		int[] rt = new int[len];

		if (len == 1)
			return nums[0];

		if (len == 2) {
			return nums[0] > nums[1] ? nums[0] : nums[1];
		}

		for (int i = 0; i < len; i++) {
			if (i == 0) {
				rt[i] = nums[i];
			} else if (i == 1) {
				rt[i] = Math.max(rt[i - 1], nums[i]);
			} else {
				rt[i] = Math.max(rt[i - 1], rt[i - 2] + nums[i]);
			}
		}
		return rt[len - 1] > rt[len - 2] ? rt[len - 1] : rt[len - 2];
	}
}
