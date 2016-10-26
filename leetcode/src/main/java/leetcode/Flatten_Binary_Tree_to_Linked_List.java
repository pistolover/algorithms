package leetcode;

import leetcode.utils.TreeNode;

public class Flatten_Binary_Tree_to_Linked_List {
	public void flatten(TreeNode root) {
		while (root != null) {
			if (root.left != null) {
				TreeNode pre = root.left;
				while (pre.right != null)
					pre = pre.right;
				pre.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			root = root.right;
		}
	}
}
