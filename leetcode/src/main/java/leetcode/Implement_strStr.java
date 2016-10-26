package leetcode;

public class Implement_strStr {
	public int strStr(String hack, String need) {
		int needlen = need.length();
		int hacklen = hack.length();

		if (needlen == 0)
			return 0;
		if (needlen == 0 && hacklen == 0)
			return -1;
		if (needlen > hacklen)
			return -1;

		for (int i = 0; i < hacklen; i++) {
			int y = needlen;
			int x = i;
			if (y <= hacklen) {
				y = y + i;
				if (y > hacklen)
					return -1;
				String compare = hack.substring(x, y);
				if (need.equals(compare)) {
					return x;
				}
			}
		}

		return -1;
	}
}
