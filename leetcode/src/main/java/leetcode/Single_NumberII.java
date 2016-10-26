package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Single_NumberII {
	public int singleNumber(int[] A) {
		if (A == null || A.length == 0)
			return -1;
		if (A != null && A.length == 1)
			return A[0];
		Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
		List<Integer> filter = new ArrayList<Integer>();

		for (int i = 0; i < A.length; i++) {
			if (i == 0) {
				maps.put(A[i], 1);
			} else {
				if (!filter.contains(A[i])) {
					if (maps.get(A[i]) == null) {
						maps.put(A[i], 1);
					} else {
						maps.put(A[i], maps.get(A[i]) + 1);
					}

					if (maps.get(A[i]) > 1) {
						maps.remove(A[i]);
						filter.add(A[i]);
					}
				}
			}
		}

		return maps.keySet().toArray(new Integer[0])[0];
	}
}
