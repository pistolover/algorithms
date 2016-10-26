package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liqqc
 *
 */
public class Valid_Anagram {

	// use api method
	public static boolean isAnagram(String s, String t) {
		if (s == null || t == null)
			return false;

		if (s.trim().equals(t.trim()))
			return true;

		if (s.length() != t.length())
			return false;

		char[] charArray = s.toCharArray();
		char[] charArray2 = t.toCharArray();
		Arrays.sort(charArray);
		Arrays.sort(charArray2);

		return new String(charArray).equals(new String(charArray2));
	}

	// use map
	public static boolean isAnagram2(String s, String t) {
		if (s == null || t == null)
			return false;

		if (s.trim().equals(t.trim()))
			return true;

		if (s.length() != t.length())
			return false;

		char[] charArray = s.toCharArray();
		char[] charArray2 = t.toCharArray();

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character c : charArray) {
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}

		for (Character c : charArray2) {
			if (!map.containsKey(c)) {
				return false;
			} else {
				if (map.get(c) <= 0) {
					return false;
				} else {
					map.put(c, map.get(c) - 1);
				}
			}
		}

		return true;
	}

	// use array
	public static boolean isAnagram3(String s, String t) {

		if (s == null || t == null)
			return false;

		if (s.length() != t.length())
			return false;

		int[] arr = new int[256];

		for (char c : s.toCharArray()) {
			if (arr[c] == 0) {
				arr[c] = 1;
			} else {
				arr[c] = arr[c] + 1;
			}
		}

		for (char c : t.toCharArray()) {
			if (arr[c] == 0) {
				return false;
			} else {
				arr[c] = arr[c] - 1;
			}
		}

		return true;
	}

}
