package leetcode;

public class Unique_Binary_Search_Trees {
	public int numTrees(int n) {
		int i;
		int result = 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;

		for (i = n - 1; i >= 0; i--) {
			result = result + numTrees(i) * numTrees(n - i - 1);
		}
		return result;
	}
}
