package leetcode;

public class Length_of_Last_Word {
	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int len = s.length();
		int count = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (count == 0)
					continue;
				else
					return count;
			}
			count++;
		}
		return count;
	}
}
