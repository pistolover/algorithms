package leetcode;

public class Palindrome_Number {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int flag = 1;
		// 确定为10的多少幂次方
		while (x / flag > 9) {
			flag = flag * 10;
		}

		while (x > 0) {
			// 判断最高位和最低位是否相同
			if (x / flag != x % 10) {
				return false;
			} else {
				// 得到最高位
				int left = x / flag;
				// 去掉最高位
				x = x - left * flag;
				// 去掉最低位
				x = x / 10;
				// 位数少了两位，标志位减少10的2次方100
				flag = flag / 100;
			}
		}

		return true;
	}
}
