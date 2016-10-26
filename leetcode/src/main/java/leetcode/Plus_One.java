package leetcode;

public class Plus_One {
	public int[] plusOne(int[] digits) {
		if (digits.length == 0)
			return null;
		int len = digits.length;

		int flag = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (i == len - 1) {
				if (digits[i] + 1 > 9) {
					digits[i] = digits[i] + 1 - 10;
					flag = 1;
				} else {
					digits[i] = digits[i] + 1;
					break;
				}
				continue;
			}

			// 从倒数第二个开始
			if (digits[i] + flag > 9) {
				digits[i] = digits[i] + 1 - 10;
				flag = 1;
			} else {
				digits[i] = digits[i] + 1;
				flag = 0;
				break;
			}
		}

		if (flag == 1) {
			int[] result = new int[len + 1];
			result[0] = 1;
			for (int i = 0; i < len; i++) {
				result[i + 1] = digits[i];
			}
			return result;
		}

		return digits;
	}
}
