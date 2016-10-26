package leetcode;

public class Count_and_Say {
	public String countAndSay(int n) {
		if (n == 1)
			return "1";
		String s = "1";
		StringBuffer buffer = new StringBuffer();
		// 记录重复的值
		int count = 0;
		// 迭代次数
		int round = 0;
		int i;
		while (++round < n) {
			count = 1;
			buffer.setLength(0);
			for (i = 1; i < s.length(); i++) {
				// 重复的值，继续计数
				if (s.charAt(i) == s.charAt(i - 1)) {
					count++;
				} else {
					// 有新的值出现，记录到buffer
					buffer.append(count).append(s.charAt(i - 1));
					// 重置count
					count = 1;
				}
			}
			buffer.append(count).append(s.charAt(i - 1));
			// 更新s
			s = buffer.toString();
		}
		return buffer.toString();
	}
}
