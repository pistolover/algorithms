package leetcode;

public class Sum_of_Two_Integers {
	public int getSum(int a, int b) {
		while (b != 0) {
			int c = a ^ b;
			b = (a & b) << 1;
			a = c;
		}
		return a;
	}
}
