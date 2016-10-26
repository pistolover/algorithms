package leetcode;

/**
 * @author liqqc
 */
import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {
	public boolean containsDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (maps.get(nums[i]) == null) {
				maps.put(nums[i], nums[i]);
			} else if (maps.get(nums[i]) != null) {
				return true;
			}
		}
		return false;
	}
}
