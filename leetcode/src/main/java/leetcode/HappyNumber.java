package leetcode;

/**
 * @author liqq
 */
public class HappyNumber {
	public static boolean isHappy(int n) {
		if (n == 0)
			return false;
		return testhappy(0, n);
	}

	private static boolean testhappy(int number, int n) {
		int sum = n;
		int add = 0;
		boolean hasone = false;
		while (sum != 0) {
			int square = (sum % 10) * (sum % 10);
			add += square;
			sum = sum / 10;
			if (sum % 10 == 1) {
				hasone = true;
			}
		}
		number++;

		int test = add;
		while (test != 0) {
			if (test % 10 == 1) {
				hasone = true;
			}
			test = test / 10;
		}

		if (hasone == false && number > 4)
			return false;

		if (add != 1 || add % 10 != 1) {
			boolean istrue = testhappy(number, add);
			if (istrue)
				return true;
		} else {
			return true;
		}
		return false;
	}
}