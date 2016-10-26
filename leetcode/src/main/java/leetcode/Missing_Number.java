package leetcode;

public class Missing_Number {
	
	
	
	public static void main(String[] args) {
	    System.err.println(missingNumber(new int[]{1,2}));
	}
	
	
	
	public static int missingNumber(int[] nums) {

		if (nums == null)
			return -1;

		int len = nums.length;
		if (len == 1) {
			if (nums[0] == 0) {
				return 1;
			} else {
				return nums[0] - 1;
			}
		}

		int max = nums[0];
		for (int i = 1; i < len; i++) {
			if (nums[i] >= max) {
				max = nums[i];
			}
		}

		int[] miss = new int[max+1];

		
		for (int i = 0; i < max+1; i++) {
				miss[i] = -1;;
		}
		
		for (int i = 0; i < max+1; i++) {
			if (i < len) {
				miss[nums[i]] = nums[i];
			}			
		}

		for (int i = 0; i < max; i++) {
			if (miss[i] == -1) {
				return i;
			}
		}

		return miss[miss.length - 1] + 1;
	}

}
