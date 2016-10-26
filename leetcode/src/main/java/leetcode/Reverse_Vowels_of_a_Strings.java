package leetcode;

public class Reverse_Vowels_of_a_Strings {

	public static void main(String[] args) {
		int[] rt = new int[7];
		for (int k = 0; k < 6; k++) {
			int i = 0;
			int j = k;
			for (i = 0; j > 0; i++) {
				j = j & (j - 1);
			}
			System.err.println(i);
		}

	}

	public String reverseVowels(String s) {
		return null;
	}
}
