package leetcode;

import java.util.Stack;

public class Reverse_Words_in_a_String_III {
	public String reverseWords(String s) {
		if (s == null)
			return null;
		if ("".equals(s))
			return "";

		Stack<Character> stack = new Stack<>();

		for (char character : s.toCharArray()) {
			if (' ' == character) {

			}
		}

		return null;

	}
}
