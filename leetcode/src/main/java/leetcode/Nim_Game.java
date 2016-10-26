package leetcode;

public class Nim_Game {
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.err.println(i +"==" + canWinNim(i));
		}
	}
	
	public static  boolean canWinNim(int n) {

		if (n > 0 && n % 4 == 0) {
			return false;
		} else {

			return true;
		}

	}
}
