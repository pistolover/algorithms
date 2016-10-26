package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Excel_Sheet_Column_Number {
	public int titleToNumber(String s) {
		Map<String, Integer> all = new HashMap<String, Integer>();
		all.put("A", 1);
		all.put("G", 7);
		all.put("L", 12);
		all.put("Q", 17);
		all.put("V", 22);
		all.put("B", 2);
		all.put("H", 8);
		all.put("M", 13);
		all.put("R", 18);
		all.put("W", 23);
		all.put("C", 3);
		all.put("I", 9);
		all.put("N", 14);
		all.put("S", 19);
		all.put("X", 24);
		all.put("D", 4);
		all.put("J", 10);
		all.put("O", 15);
		all.put("T", 20);
		all.put("Y", 25);
		all.put("E", 5);
		all.put("K", 11);
		all.put("P", 16);
		all.put("U", 21);
		all.put("Z", 26);
		all.put("F", 6);

		if (s == null)
			return -1;
		int len = s.length();
		int sum = 0;
		int power = len - 1;
		for (int i = 0; i < len; i++) {
			int curr = all.get(String.valueOf(s.charAt(i)));
			sum += curr * Math.pow(all.size(), power);
			power--;
		}
		return sum;
	}
}
