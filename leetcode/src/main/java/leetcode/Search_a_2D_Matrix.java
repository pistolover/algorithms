package leetcode;

public class Search_a_2D_Matrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		// 首先通过二分查找的方式找到对应的行 比较每一行的位置来缩小范围
		// 然后再几行的范围内再进行二分查找
		int fis = 0;
		int end = matrix.length - 1;
		int mid = 0;
		while (fis <= end) {
			mid = fis + ((end - fis) >> 1);

			if (fis == end) {
				if (matrix[fis][0] > target && fis >= 1) {
					for (int i = 0; i < matrix[fis - 1].length; i++) {
						if (matrix[fis - 1][i] == target) {
							return true;
						}
					}
					return false;
				} else if (matrix[fis][0] < target) {
					for (int i = 0; i < matrix[fis].length; i++) {
						if (matrix[fis][i] == target) {
							return true;
						}
					}
					return false;
				}
			}
			if (matrix[mid][0] > target) {
				end = mid - 1;
			} else if (matrix[mid][0] < target) {
				fis = mid + 1;
			} else if (matrix[mid][0] == target) {
				return true;
			}
		}

		// 从end开始
		if (fis > end && end >= 0) {
			for (int i = 0; i < matrix[end].length; i++) {
				if (matrix[end][i] == target) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

}
