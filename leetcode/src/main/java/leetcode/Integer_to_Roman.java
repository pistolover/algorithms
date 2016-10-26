package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Integer_to_Roman {
	public String intToRoman(int num) {
		Map<Integer, String> maps = new HashMap<Integer, String>();
		maps.put(1, "I");
		maps.put(2, "II");
		maps.put(3, "III");
		maps.put(4, "IV");
		maps.put(5, "V");
		maps.put(6, "VI");
		maps.put(7, "VII");
		maps.put(8, "VIII");
		maps.put(9, "IX");
		maps.put(10, "X");
		maps.put(20, "XX");
		maps.put(30, "XXX");
		maps.put(40, "XL");
		maps.put(50, "L");
		maps.put(60, "LX");
		maps.put(70, "LXX");
		maps.put(80, "LXXX");
		maps.put(90, "XC");
		maps.put(100, "C");
		maps.put(200, "CC");
		maps.put(300, "CCC");
		maps.put(400, "CD");
		maps.put(500, "D");
		maps.put(600, "DC");
		maps.put(700, "DCC");
		maps.put(800, "DCCC");
		maps.put(900, "CM");
		maps.put(1000, "M");
		maps.put(2000, "MM");
		maps.put(3000, "MMM");

		StringBuffer buffer = new StringBuffer();
		if (num <= 10) {
			return maps.get(num);
		} else if (num > 10 && num < 100) {
			int index = num / 10;
			buffer.append(maps.get(index * 10));
			if (num - index * 10 > 0) {
				buffer.append(maps.get(num - index * 10));
			}
			return buffer.toString();
		} else if (num >= 100 && num < 1000) {
			int hun = num / 100;
			buffer.append(maps.get(hun * 100));
			int te = (num - hun * 100) / 10;
			if (te > 0) {
				buffer.append(maps.get(te * 10));
			}
			if (num - hun * 100 - te * 10 > 0) {
				buffer.append(maps.get(num - hun * 100 - te * 10));
			}
			return buffer.toString();
		} else if (num >= 1000 && num <= 3999) {
			int th = num / 1000;
			buffer.append(maps.get(th * 1000));
			int hun = (num - th * 1000) / 100;
			if (hun > 0) {
				buffer.append(maps.get(hun * 100));
			}
			int te = (num - th * 1000 - hun * 100) / 10;
			if (te > 0) {
				buffer.append(maps.get(te * 10));
			}
			if (num - th * 1000 - hun * 100 - te * 10 > 0) {
				buffer.append(maps.get(num - th * 1000 - hun * 100 - te * 10));
			}
			return buffer.toString();
		}
		return buffer.toString();
	}
}
