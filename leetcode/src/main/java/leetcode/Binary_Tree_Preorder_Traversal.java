package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Binary_Tree_Preorder_Traversal {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();

		if (root != null) {
			result.add(root.val);
			pre_order(result, root.left);
			pre_order(result, root.right);
		}

		return result;
	}

	private void pre_order(List<Integer> result, TreeNode curr) {
		if (curr != null) {
			result.add(curr.val);
			pre_order(result, curr.left);
			pre_order(result, curr.right);
		}
	}
}
