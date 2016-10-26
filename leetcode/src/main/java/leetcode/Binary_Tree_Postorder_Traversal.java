package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Binary_Tree_Postorder_Traversal {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if (root != null) {
			Post_order(result, root.left);
			Post_order(result, root.right);
			result.add(root.val);
		}
		return result;
	}

	private void Post_order(List<Integer> result, TreeNode curr) {
		if (curr != null) {
			Post_order(result, curr.left);
			Post_order(result, curr.right);
			result.add(curr.val);
		}
	}
}
