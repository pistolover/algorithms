package leetcode;

public class Climbing_Stairs {
	public int climbStairs(int num) {
		// 该题目和斐波拉切数列、生兔子问题属于同一类问题
		// 如果定义为 int则num=46时会越界；如果定义为long则num=92时会越界
		if (num <= 0)
			return 0;
		int[] sum = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			if (i == 0)
				sum[i] = 1;
			if (i == 1)
				sum[i] = 1;
			if (i > 1) {
				sum[i] = sum[i - 1] + sum[i - 2];
			}
		}
		return sum[num];
	}
}
