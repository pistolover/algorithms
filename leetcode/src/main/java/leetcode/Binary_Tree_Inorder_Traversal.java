package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Binary_Tree_Inorder_Traversal {
	public List<Integer> inorderTraversal(TreeNode root) {

		List<Integer> result = new LinkedList<Integer>();

		if (root != null) {
			in_order(result, root.left);
			result.add(root.val);
			in_order(result, root.right);
		}
		return result;
	}

	private void in_order(List<Integer> result, TreeNode curr) {
		if (curr != null) {
			in_order(result, curr.left);
			result.add(curr.val);
			in_order(result, curr.right);
		}
	}
}
