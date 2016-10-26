package leetcode;

public class Reverse_String {
	public static void main(String[] args) {
		System.err.println(reverseString01("hello words"));
	}

	public static String reverseString(String s) {
		if (s == null)
			return null;
		StringBuffer buffer = new StringBuffer(s);
		buffer.reverse();
		return buffer.toString();
	}

	public static String reverseString01(String s) {
		if (s == null)
			return null;
		int count = s.length();
		int n = count - 1;
		char[] value = s.toCharArray();
		for (int j = (n - 1) >> 1; j >= 0; j--) {
			int k = n - j;
			char cj = value[j];
			char ck = value[k];
			value[j] = ck;
			value[k] = cj;
		}
		return new String(value);
	}
}
