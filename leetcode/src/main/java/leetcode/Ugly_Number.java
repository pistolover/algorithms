package leetcode;

public class Ugly_Number {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.err.println(i + "==" + isUgly(i));
		}
	}

	// 2 3 5
	public static boolean isUgly(int num) {

		if (num == 1)
			return true;

		while (num >= 2 && num % 2 == 0) {
			num = num / 2;
		}

		while (num >= 3 && num % 3 == 0) {
			num = num / 3;
		}

		while (num >= 5 && num % 5 == 0) {
			num = num / 5;
		}

		return num == 1 ? true : false;
	}

}
