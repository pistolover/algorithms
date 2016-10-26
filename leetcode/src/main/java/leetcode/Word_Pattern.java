package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Word_Pattern {

	public static void main(String[] args) {

		System.err.println(wordPattern("abba", "dog dog dog dog"));

	}

	public static boolean wordPattern(String pattern, String str) {

		char[] pt = pattern.toCharArray();
		String[] split = str.split(" ");

		if (pt.length != split.length)
			return false;

		Map<Character, String> map = new HashMap<Character, String>();

		for (int i = 0; i < pt.length; i++) {
			if (!map.containsKey(pt[i])) {
				if (map.values().contains(split[i])) {
					return false;
				} else {
					map.put(pt[i], split[i]);

				}
			} else {
				if (map.get(pt[i]) != null) {
					if (map.get(pt[i]).equals(split[i])) {
						continue;
					} else {
						return false;
					}
				} else {
					if (map.values().contains(split[i])) {
						return false;
					} else {
						map.put(pt[i], split[i]);

					}
				}

			}
		}
		return true;

	}
}
