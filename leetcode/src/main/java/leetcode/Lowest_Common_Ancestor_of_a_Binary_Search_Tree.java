package leetcode;

import leetcode.utils.TreeNode;

public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// 在树的两边
		if (p.val >= root.val && q.val <= root.val)
			return root;

		else if (p.val <= root.val && q.val >= root.val)
			return root;

		// 在树的一边 左边或右边
		else if (p.val < root.val && q.val < root.val) {
			// 左边

			return lowestCommonAncestor(root.left, p, q);

		}

		else {

			// 右边

			return lowestCommonAncestor(root.right, p, q);

		}

	}
}
