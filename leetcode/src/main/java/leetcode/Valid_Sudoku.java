package leetcode;

public class Valid_Sudoku {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char curr = board[i][j];
				if (curr == '.') {
					continue;
				}

				// 行
				for (int k = j + 1; k < board.length; k++) {
					if (curr == board[i][k]) {
						return false;
					}
				}

				// 列

				for (int k = i + 1; k < board.length; k++) {
					if (curr == board[k][j]) {
						return false;
					}
				}

				// 方块

				if (i >= 0 && i < 3 && j >= 0 && j < 3) {
					int count = 0;
					for (int k1 = 0; k1 < 3; k1++) {
						for (int k2 = 0; k2 < 3; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 0 && i < 3 && j >= 3 && j < 6) {
					int count = 0;
					for (int k1 = 0; k1 < 3; k1++) {
						for (int k2 = 3; k2 < 6; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 0 && i < 3 && j >= 6 && j < 9) {
					int count = 0;
					for (int k1 = 0; k1 < 3; k1++) {
						for (int k2 = 6; k2 < 9; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 3 && i < 6 && j >= 0 && j < 3) {
					int count = 0;
					for (int k1 = 3; k1 < 6; k1++) {
						for (int k2 = 0; k2 < 3; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 3 && i < 6 && j >= 3 && j < 6) {
					int count = 0;
					for (int k1 = 3; k1 < 6; k1++) {
						for (int k2 = 3; k2 < 6; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 3 && i < 6 && j >= 6 && j < 9) {
					int count = 0;
					for (int k1 = 3; k1 < 6; k1++) {
						for (int k2 = 6; k2 < 9; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 6 && i < 9 && j >= 0 && j < 3) {
					int count = 0;
					for (int k1 = 6; k1 < 9; k1++) {
						for (int k2 = 0; k2 < 3; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 6 && i < 9 && j >= 3 && j < 6) {
					int count = 0;
					for (int k1 = 6; k1 < 9; k1++) {
						for (int k2 = 3; k2 < 6; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}

				if (i >= 6 && i < 9 && j >= 6 && j < 9) {
					int count = 0;
					for (int k1 = 6; k1 < 9; k1++) {
						for (int k2 = 6; k2 < 9; k2++) {
							if (board[k1][k2] == curr) {
								count++;
							}
							if (count > 1)
								return false;
						}
					}
				}
			}
		}
		return true;
	}
}
