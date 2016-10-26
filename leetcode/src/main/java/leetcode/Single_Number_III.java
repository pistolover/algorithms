package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Single_Number_III {

	public static void main(String[] args) {
		singleNumber(new int[] { 0, 0, 1, 2 });
	}

	public static int[] singleNumber(int[] nums) {

		if (nums == null || nums.length == 0)
			return nums;

		int len = nums.length;
		int p = 0;
		Map<Integer, Integer> maps = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			if (maps.get(nums[i]) == null) {
				maps.put(nums[i], 1);
			} else {
				maps.put(nums[i], maps.get(nums[i]) + 1);
				p = p + maps.get(nums[i]);
			}
		}

		int[] arr = new int[len - p];
		int t = 0;
		Set<Integer> keySet = maps.keySet();
		for (Integer integer : keySet) {
			if (maps.get(integer) == 1) {
				arr[t++] = integer;
			}
		}

		return arr;
	}
}
