package leetcode;

import org.junit.Test;

public class Power_of_Four {

	public static void main(String[] args) {
		System.err.println(isPowerOfFour(16));
	}
	
	public static boolean isPowerOfFour(int num) {
		
		return num > 0 && ((num & (num - 1))==0) && ((num - 1) % 3 == 0);
	}

	@Test
	public void t1() {
		System.err.println((400 & (400 >> 2)) % 4);
		
		System.err.println(10 & 11);
	}
}
