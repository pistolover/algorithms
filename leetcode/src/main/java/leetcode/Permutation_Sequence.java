package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Permutation_Sequence {

	public static void main(String[] args) {
		System.err.println(getPermutation(3, 4));
	}
	
	public static  String getPermutation(int n, int k) {
		int[] permutation = new int[n];
		permutation[0] = 1;
		for (int i = 1; i < n; ++i) {
			permutation[i] = permutation[i - 1] * (i + 1);
		}

		// num list that can be used
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= n; ++i) {
			list.add(i);
		}

		// process
		StringBuilder sb = new StringBuilder();
		int pos = n - 1;
		k -= 1;
		while (pos > 0) {
			int index = k / permutation[pos - 1];
			sb.append(list.get(index));
			list.remove(index);
			k = k % permutation[pos-1];
			--pos;
		}
		sb.append(list.get(0));
		return sb.toString();

	}
}
