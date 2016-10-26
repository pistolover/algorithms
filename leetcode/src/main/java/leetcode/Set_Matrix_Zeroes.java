package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Set_Matrix_Zeroes {
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;

		Set<Integer> rowFilter = new HashSet<Integer>();
		Set<Integer> colFilter = new HashSet<Integer>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					// 当前位置为0 将行和列存储
					rowFilter.add(i);
					colFilter.add(j);
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (rowFilter.contains(i)) {
					matrix[i][j] = 0;
				}

				if (colFilter.contains(j)) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
