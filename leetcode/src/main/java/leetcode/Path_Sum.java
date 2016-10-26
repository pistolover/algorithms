package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import leetcode.utils.TreeNode;

public class Path_Sum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root != null && root.left == null && root.right == null
				&& root.val == sum)
			return true;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Set<TreeNode> set = new HashSet<TreeNode>();
		int count = 0;
		stack.push(root);
		count += root.val;
		while (stack.size() != 0) {
			TreeNode top = stack.peek();

			if (top.left != null && !set.contains(top.left)) {
				stack.push(top.left);
				count += top.left.val;
			} else if (top.right != null && !set.contains(top.right)) {
				stack.push(top.right);
				count += top.right.val;
			} else {
				if (top != root && top.left == null && top.right == null
						&& count == sum) {
					return true;
				} else {
					TreeNode pop = stack.pop();
					set.add(pop);
					count = count - pop.val;
				}
			}
		}

		return false;
	}
}
