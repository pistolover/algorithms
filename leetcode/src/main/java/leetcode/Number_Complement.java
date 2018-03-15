package leetcode;

import test.Main;

public class Number_Complement {
	public static void main(String[] args) {
		System.err.println(findComplement(1));
	}

	public static int findComplement(int num) {
		// 将num对应的二进制翻转
		String str = Integer.toBinaryString(num);
		String r = "";
		for (char i : str.toCharArray()) {
			if (i == '0') {
				r += "1";
			} else {
				r += "0";
			}
		}

		int k = 0;
		for (char i : r.toCharArray()) {
			if (i == '0') {
				k++;
			} else {
				break;
			}
		}

		r = r.substring(k);

		return r.equalsIgnoreCase("") ? 0 : Integer.valueOf(r, 2);

	}
}
