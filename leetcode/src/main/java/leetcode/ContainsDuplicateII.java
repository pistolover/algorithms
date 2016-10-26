package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {

	public static void main(String[] args) {
		System.err.println(containsNearbyDuplicate(new int[] { 99, 99 }, 3));

	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null)
			return false;
		int len = nums.length;
		if (len < 2)
			return false;
		
		int start = 0, end = 0;
		Map<Integer, Integer> tables = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			if (!tables.containsKey(nums[i])) {
				tables.put(nums[i], i);
				end++;
			} else {
				return true;
			}

			if (end - start > k) {
				tables.remove(nums[start]);
				start++;
			}

		}
		return false;
	}
}
