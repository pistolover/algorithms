package leetcode;

public class Longest_Common_Prefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String min = strs[0];
		if (min.length() == 0)
			return "";

		if (strs.length == 1)
			return min;

		for (int i = 1; i < strs.length; i++) {
			if (min.length() > strs[i].length())
				min = strs[i];
		}

		StringBuffer buff = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < min.length(); i++) {
			char c = min.charAt(i);
			for (int j = 0; j < strs.length; j++) {
				if (strs[j].length() != 0) {
					if (strs[j].charAt(i) == c) {
						flag = true;
						continue;
					} else {
						flag = false;
						return buff.toString();
					}
				}
			}

			if (flag) {
				buff.append(c);
			}
		}
		return buff.toString();
	}
}
