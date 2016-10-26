package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Pascals_TriangleII {
	public List<Integer> getRow(int num) {
		List<Integer> row = new LinkedList<Integer>();
		if (num == 0) {
			row.add(1);
			return row;
		}

		if (num == 1) {
			row.add(1);
			row.add(1);
			return row;
		}

		if (num > 1) {
			row.add(1);
			row.add(1);
			for (int i = 2; i <= num; i++) {
				List<Integer> temp = new LinkedList<Integer>();

				for (int j = 0; j < i + 1; j++) {
					if (j == 0)
						temp.add(1);
					if (j == i)
						temp.add(1);
					if (j != 0 && j != i) {
						temp.add(row.get(j - 1) + row.get(j));
					}
				}
				row = temp;
			}
		}
		return row;
	}
}
