package leetcode;

public class Majority_Element_Total {
	public int majorityElement(int[] num) {
		if (num == null || num.length == 0)
			return -1;
		int count = 0;
		int index = 0;
		for (int i = 0; i < num.length; ++i) {
			if (index == 0)
				count = num[i];
			if (count == num[i])
				++index;
			else
				--index;
		}
		return count;
	}

}
