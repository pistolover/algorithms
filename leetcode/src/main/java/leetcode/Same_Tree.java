package leetcode;

import leetcode.utils.TreeNode;

public class Same_Tree {
	public boolean isSameTree(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}

		else if (r1 == null || r2 == null) {
			return false;
		}

		else {
			if (r1.val != r2.val) {
				return false;
			}

			boolean left = isSameTree(r1.left, r2.left);
			boolean right = isSameTree(r1.right, r2.right);

			if (left && right) {
				return true;
			} else {
				return false;
			}
		}
	}
}
