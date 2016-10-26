package leetcode;

public class Sort_Colors {
	public void sortColors(int[] A) {
		if (A == null || A.length <= 1)
			return;

		int _zeor = 0;
		int _one = 0;
		int _two = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0)
				_zeor++;
			if (A[i] == 1)
				_one++;
			if (A[i] == 2)
				_two++;
		}

		for (int i = 0; i < A.length; i++) {
			if (_zeor > 0) {
				A[i] = 0;
				_zeor--;
				continue;
			}
			if (_one > 0) {
				A[i] = 1;
				_one--;
				continue;
			}

			if (_two > 0) {
				A[i] = 2;
				_two--;
			}
		}
	}
}
