package leetcode;

public class Excel_Sheet_Column_Title {
	public String convertToTitle(int num) {
		if (num < 1) {
			return "";
		} else {
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			while (num > 0) {
				num--;
				char c = (char) (num % 26 + 'A');
				temp = temp + c;
				num /= 26;
			}
			for (int i = temp.length() - 1; i >= 0; i--) {
				buffer.append(temp.charAt(i));
			}
			return buffer.toString();
		}
	}
}
