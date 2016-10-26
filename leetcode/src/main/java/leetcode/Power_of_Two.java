package leetcode;

public class Power_of_Two {

	public static void main(String[] args) {

		System.err.println(isPowerOfTwo(88));
	}

	public static boolean isPowerOfTwo(int n) {

		if(n==0) return false;
		if(n==1) return true;
		
		while (n != 0) {
			if (n % 2 == 0) {
				n = n / 2;
				if(n==1) return true;
			} else {
				return false;
			}

		}
		return true;
	}
}
