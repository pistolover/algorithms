package leetcode;

import java.util.LinkedList;

import leetcode.utils.TreeNode;

public class Maximum_Depth_of_Binary_Tree {
	public int maxDepth(TreeNode root) {
		int level = 0;
		if (root == null)
			return 0;
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
				first++;
			}
			level++;
		}
		return level;
	}
}
