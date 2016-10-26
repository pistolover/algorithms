package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Remove_Element {
	public int removeElement(int[] A, int elem) {
		int len = A.length;
		if (len == 0)
			return 0;
		List<Integer> list = new LinkedList<Integer>();

		for (int i = 0; i < len; i++) {
			if (A[i] != elem) {
				list.add(A[i]);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			A[i] = list.get(i);
		}

		return list.size();
	}
}
