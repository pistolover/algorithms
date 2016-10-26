package leetcode;

public class Valid_Palindrome {
	public boolean isPalindrome(String s) {
		if (s.length() == 0)
			return true;
		int len = s.length();

		int start = 0;
		int end = len - 1;

		while (start != end && start < end) {
			String s1 = "";
			String s2 = "";

			while (start < len) {
				if (Character.isLetterOrDigit(s.charAt(start)) != true) {
					start++;
				} else {
					s1 = String.valueOf(s.charAt(start));
					break;
				}
			}

			if (start == len - 1
					&& Character.isLetterOrDigit(s.charAt(start)) == true)
				return true;

			while (end > 0) {
				if (Character.isLetterOrDigit(s.charAt(end)) != true) {
					end--;
				} else {
					s2 = String.valueOf(s.charAt(end));
					break;
				}
			}

			if (end == 0 && Character.isLetterOrDigit(s.charAt(end)) == true)
				return true;

			if (s1.equalsIgnoreCase(s2)) {
				start++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}
}
