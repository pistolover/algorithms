package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Pascals_Triangle {
	public List<List<Integer>> generate(int num) {
		List<List<Integer>> rows = new LinkedList<List<Integer>>();
		// num太大可能越界
		for (int i = 1; i <= num; i++) {
			List<Integer> row = new LinkedList<Integer>();
			if (i == 1) {
				row.add(1);
			}
			if (i == 2) {
				row.add(1);
				row.add(1);
			}

			if (i >= 3) {
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						row.add(1);
					}
					if (j == i - 1) {
						row.add(1);
					}

					if (j != 0 && j != i - 1) {
						List<Integer> list = rows.get(i - 2);
						row.add(list.get(j - 1) + list.get(j));
					}
				}
			}
			rows.add(row);
		}
		return rows;
	}
}
