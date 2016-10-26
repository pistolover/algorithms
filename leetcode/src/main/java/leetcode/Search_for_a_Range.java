package leetcode;

public class Search_for_a_Range {
	public int[] searchRange(int[] A, int target) {
		if (A == null || A.length == 0)
			return null;

		if (A.length == 1) {
			int[] pos = new int[2];
			if (A[0] == target) {
				pos[0] = pos[1] = 0;
				return pos;
			} else {
				pos[0] = pos[1] = -1;
				return pos;
			}
		}

		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		int end = A.length - 1;
		int start = 0;
		// 如果没找到返回默认值 找到了返回找到的位置

		// 首先确定元素起始位置 然后在确定终止位置

		while (start <= end) {
			int mid = start + ((end - start) >> 1);

			if (A[mid] > target) {
				end = mid - 1;
			} else if (A[mid] < target) {
				start = mid + 1;
			} else if (A[mid] == target) {
				// 找到了
				// 从该位置分别往前往后寻找

				// 往前寻找
				int head = 0;
				int temp = mid;

				if (temp - 1 >= 0) {
					while (temp - 1 >= 0) {
						if (A[temp - 1] == A[temp]) {
							head = temp - 1;
							if (temp - 1 == 0) {
								head = 0;
								result[0] = 0;
								break;
							}
							temp = temp - 1;
						} else {
							if (head == 0) {
								result[0] = mid;
								break;
							} else {
								result[0] = head < mid ? head : mid;
								break;
							}

						}
					}
				} else {
					result[0] = 0;
				}

				// 往后寻找
				int last = 0;
				int temp2 = mid;
				if (temp2 + 1 <= A.length - 1) {
					while (temp2 + 1 <= A.length - 1) {
						if (A[temp2] == A[temp2 + 1]) {
							last = temp2 + 1;
							if (temp2 + 1 == A.length - 1) {
								result[1] = temp2 + 1;
								return result;
							}
							temp2 = temp2 + 1;
						} else {
							result[1] = last > temp2 ? last : temp2;
							return result;
						}
					}
				} else {
					result[1] = A.length - 1;
					return result;
				}
			}
		}

		return result;
	}
}
