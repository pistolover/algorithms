package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Compare_Version_Numbers {
	public int compareVersion(String version1, String version2) {
		List<String> list1 = new LinkedList<String>();

		if (version1.contains(".")) {
			char[] charArray = version1.toCharArray();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] != '.') {
					buffer.append(charArray[i]);
					if (i == charArray.length - 1) {
						list1.add(buffer.toString());
					}
				} else {
					list1.add(buffer.toString());
					buffer.setLength(0);
				}
			}
		} else {
			list1.add(version1);
		}

		List<String> list2 = new LinkedList<String>();
		if (version2.contains(".")) {
			char[] charArray = version2.toCharArray();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] != '.') {
					buffer.append(charArray[i]);
					if (i == charArray.length - 1) {
						list2.add(buffer.toString());
					}
				} else {
					list2.add(buffer.toString());
					buffer.setLength(0);
				}
			}
		} else {
			list2.add(version2);
		}

		int max = list1.size() >= list2.size() ? list1.size() : list2.size();

		for (int i = 0; i < max; i++) {
			if (list1.size() >= list2.size()) {
				if (i > list2.size() - 1) {
					if (compare(list1.get(i), "0") == 0) {
						continue;
					} else {
						return compare(list1.get(i), "0");
					}
				} else {
					if (compare(list1.get(i), list2.get(i)) == 0) {
						continue;
					} else {
						return compare(list1.get(i), list2.get(i));
					}
				}
			}

			if (list2.size() > list1.size()) {
				if (i > list1.size() - 1) {
					if (compare("0", list2.get(i)) == 0) {
						continue;
					} else {
						return compare("0", list2.get(i));
					}
				} else {
					if (compare(list1.get(i), list2.get(i)) == 0) {
						continue;
					} else {
						return compare(list1.get(i), list2.get(i));
					}
				}
			}

		}

		return 0;
	}

	public static int compare(String s1, String s2) {
		int v1 = Integer.parseInt(s1);
		int v2 = Integer.parseInt(s2);
		if (v1 > v2) {
			return 1;
		} else if (v1 < v2) {
			return -1;
		} else {
			return 0;
		}
	}
}
