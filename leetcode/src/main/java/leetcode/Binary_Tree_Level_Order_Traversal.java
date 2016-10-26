package leetcode;

import java.util.LinkedList;
import java.util.List;
import leetcode.utils.TreeNode;

public class Binary_Tree_Level_Order_Traversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (root == null)
			return result;

		List<TreeNode> all = new LinkedList<TreeNode>();
		all.add(root);
		int first = 0;
		int last = 1;
		while (first < all.size()) {
			last = all.size();
			List<Integer> level = new LinkedList<Integer>();
			while (first < last) {
				level.add(all.get(first).val);
				if (all.get(first).left != null) {
					all.add(all.get(first).left);
				}
				if (all.get(first).right != null) {
					all.add(all.get(first).right);
				}
				first++;
			}
			result.add(level);
		}
		return result;
	}
}
