package leetcode;

public class Reverse_Integer {
	public int reverse(int x) {
		String s = String.valueOf(x);
		long sum = 0;
		boolean isnegitive = false;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(0) == '-') {
				isnegitive = true;
			}

			if (Character.isDigit(s.charAt(i))) {
				if (isnegitive) {
					sum = sum * 10
							- Integer.parseInt(String.valueOf(s.charAt(i)));
				} else {
					sum = sum * 10
							+ Integer.parseInt(String.valueOf(s.charAt(i)));
				}
			}
		}

		if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
			return 0;
		}

		return (int) sum;
	}
}
