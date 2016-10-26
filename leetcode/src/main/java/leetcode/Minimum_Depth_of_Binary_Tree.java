package leetcode;

import java.util.LinkedList;
import leetcode.utils.TreeNode;

public class Minimum_Depth_of_Binary_Tree {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root != null && root.left == null && root.right == null)
			return 1;

		int level = 1;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		int first = 0;
		int last = 1;
		while (first < list.size()) {
			last = list.size();
			while (first < last) {
				if (list.get(first).left != null) {
					list.add(list.get(first).left);
				}
				if (list.get(first).right != null) {
					list.add(list.get(first).right);
				}

				if (list.get(first).left == null
						&& list.get(first).right == null) {
					return level++;
				}
				first++;
			}
			level++;
		}
		return level;
	}
}
