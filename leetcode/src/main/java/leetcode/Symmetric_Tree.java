package leetcode;

import leetcode.utils.TreeNode;

public class Symmetric_Tree {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		} else if (root.left == null && root.right == null) {
			return true;
		} else if (root.left == null || root.right == null) {
			return false;
		} else {
			if (root.left.val != root.right.val) {
				return false;
			} else {
				return isSameTree(root.left, root.right);
			}
		}

	}

	public static boolean isSameTree(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		} else if (r1 == null || r2 == null) {
			return false;
		} else {
			if (r1.val != r2.val) {
				return false;
			} else {
				boolean _left = isSameTree(r1.left, r2.right);
				boolean _right = isSameTree(r1.right, r2.left);

				if (_left && _right) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
