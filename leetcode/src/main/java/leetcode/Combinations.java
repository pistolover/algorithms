package leetcode;

import java.util.ArrayList;
import java.util.List;

/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

public class Combinations {

	public static void main(String[] args) {
		combine(10, 3);
	}

	// n个元素中取k个元素的组合数
	public static List<List<Integer>> combine(int n, int k) {
		if (k > n)
			return null;

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Object[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = i + 1;
		}

		/* 存放结果的二维数组 */
		Object[][] obj = new Object[combination(n, k)][k];
		combine(a, 0, 0, k, new Object[k], obj);

		for (int i = 0; i < obj.length; i++) {
			List<Integer> sun = new ArrayList<Integer>();
			for (int j = 0; j < obj[i].length; j++) {
				sun.add((Integer) obj[i][j]);
			}
			result.add(sun);
		}

		return result;
	}

	/**
	 * <p>
	 * 计算 C(m,n)个数 = (m!)/(n!*(m-n)!) 057
	 * </p>
	 * 从M个数中选N个数，函数返回有多少种选法 参数 m 必须大于等于 n m = 0; n = 0; retuan 1; 059
	 * 
	 */
	public static int combination(int m, int n) {
		if (m < n)
			return 0; // 如果总数小于取出的数，直接返回0
		int k = 1;
		int j = 1;
		// 该种算法约掉了分母的(m-n)!,这样分子相乘的个数就是有n个了
		for (int i = n; i >= 1; i--) {
			k = k * m;
			j = j * n;
			m--;
			n--;
		}
		return k / j;
	}

	/**
	 * 082
	 * <p>
	 * 递归算法，把结果写到obj二维数组对象
	 * </p>
	 * 083
	 * 
	 * @param src
	 *            084
	 * @param srcIndex
	 *            085
	 * @param i
	 *            086
	 * @param n
	 *            087
	 */
	static int objLineIndex = 0;

	private static void combine(Object src[], int srcIndex, int i, int n, Object[] tmp, Object[][] obj) {
		for (int j = srcIndex; j < src.length - (n - 1); j++) {
			tmp[i] = src[j];
			if (n == 1) {
				System.arraycopy(tmp, 0, obj[objLineIndex], 0, tmp.length);
				objLineIndex++;
			} else {
				n--;
				i++;
				combine(src, j + 1, i, n, tmp, obj);
				n++;
				i--;
			}
		}

	}

}
