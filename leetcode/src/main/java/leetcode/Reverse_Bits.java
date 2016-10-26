package leetcode;

/**
 * 
 * @author lqq
 *
 */
public class Reverse_Bits {
	public int reverseBits(int n) {
		int value = 0;
		// 32位无符号数
		for (int i = 0; i < 32; ++i) {
			if ((n & 1) == 1) {
				value = (value << 1) + 1; // 右移动
				n >>= 1;
			} else {
				value = value << 1;
				n >>= 1; // 右移
			}
		}
		return value;
	}
}
