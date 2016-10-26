package leetcode;

public class ZigZag_Conversion {
	public String convert(String s, int nRows) {
		if (nRows <= 0)
			return "";
		if (nRows == 1 || nRows >= s.length())
			return s;
		StringBuffer buffer = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len && i < nRows; ++i) {
			int start = i;
			buffer.append(s.charAt(start));
			for (int j = 1; start < len; ++j) {
				if (i == 0 || i == nRows - 1) {
					start = start + 2 * nRows - 2;
				} else {
					if (j % 2 == 1) {
						start = start + 2 * (nRows - i - 1);
					} else {
						start = start + 2 * i;
					}
				}
				if (start < len)
					buffer.append(s.charAt(start));
			}
		}
		return buffer.toString();
	}
}
