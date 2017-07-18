package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Keyboard_Row {

	public static void main(String[] ags) {
		findWords(new String[] { "asdfghjklASDFGHJKLasdfghjklASDFGHJKLzxcvbnmZXCVBNMaew",
				"asdfghjklASDFGHJKLqwertyuiopQWERTYUIOP",
				"zxcvbnmZXCVBNMaewzxcvbnmZXCVBNMaewzxcvbnmZXCVBNMaewzxcvbnmZXCVBNMaew" });

	}

	public static String[] findWords(String[] words) {
		if (words == null || words.length == 0)
			return new String[]{};

		Set s1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'Q', 'W', 'E', 'R', 'T',
				'Y', 'U', 'I', 'O', 'P'));
		Set s2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'A', 'S', 'D', 'F', 'G', 'H',
				'J', 'K', 'L'));
		Set s3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'));
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		String result = "";
		for (String s : words) {
			char[] charArray = s.toCharArray();
			for (char c : charArray) {
				if (s1.contains(c)) {
					if (b2 == true || b3 == true) {
						b1 = true;
						break;
					}
					b1 = true;
					continue;
				}
				if (s2.contains(c)) {
					if (b1 == true || b3 == true) {
						b2 = true;
						break;
					}
					b2 = true;
					continue;
				}
				if (s3.contains(c)) {
					if (b1 == true || b2 == true) {
						b3 = true;
						break;
					}
					b3 = true;
					continue;
				}
			}

			if (b1 == true && b2 == false && b3 == false || b1 == false && b2 == true && b3 == false
					|| b1 == false && b2 == false && b3 == true) {
				result = result + s + ",";
			}
			b1 = false;
			b2 = false;
			b3 = false;
		}

		if (result.length() > 1) {
			result = result.substring(0, result.length() - 1);
		}
		String[] split = result.split(",");
		return split;

	}
}
