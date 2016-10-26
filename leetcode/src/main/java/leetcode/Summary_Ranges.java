package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Summary_Ranges {

	public static void main(String[] args) {

		int[] arr = { 1, 3 };
		summaryRanges(arr);
	}

	public static List<String> summaryRanges(int[] nums) {
		int len = nums.length;
		List<String> result = new ArrayList<String>();
		int m = 0, n = 0;
		StringBuffer buffer = null;

		if (len == 1) {
			buffer = new StringBuffer();
			buffer.append(nums[0]);
			result.add(buffer.toString());
			return result;
		}

		for (int i = 0; i < len - 1; i++) {
			buffer = new StringBuffer();
			if (nums[i] + 1 == nums[i + 1]) {
				n++;
				if (i + 1 == len - 1) {
					buffer.append(nums[m]);
					buffer.append("->");
					buffer.append(nums[n]);
					result.add(buffer.toString());
				}
			} else {
				// 不连续了
				if(m==n){
					buffer.append(nums[m]);
				}else{
					buffer.append(nums[m]);
					buffer.append("->");
					buffer.append(nums[n]);
				}
				result.add(buffer.toString());

				m = i + 1;
				n = i + 1;
				if (i == len - 2) {
					buffer = new StringBuffer();
					buffer.append(nums[len - 1]);
					result.add(buffer.toString());
				}
			}
		}
		return result;
	}
}
