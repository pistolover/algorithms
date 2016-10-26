package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Binary_Tree_Paths {

	public static void main(String[] args) {
		TreeNode r = new TreeNode(1);
		TreeNode r1 = new TreeNode(2);
		TreeNode r2 = new TreeNode(3);
		TreeNode r3 = new TreeNode(5);

		r.left = r1;
		r.right = r2;
		r1.right = r3;

		binaryTreePaths(r);
	}

	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<String>();

		if (root != null) {
			getpath(root, String.valueOf(root.val), result);
		}
		return result;
	}

	private static void getpath(TreeNode root, String valueOf,
			List<String> result) {
		if (root.left == null && root.right == null)
			result.add(valueOf);

		if (root.left != null) {
			getpath(root.left, valueOf + "->" + root.left.val, result);
		}

		if (root.right != null) {
			getpath(root.right, valueOf + "->" + root.right.val, result);
		}
	}
}
