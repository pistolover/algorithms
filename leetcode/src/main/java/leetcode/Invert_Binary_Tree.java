package leetcode;

import leetcode.utils.TreeNode;

public class Invert_Binary_Tree {
	/*  4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9*/

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode c1 = new TreeNode(2);

		TreeNode c2 = new TreeNode(7);
		TreeNode c3 = new TreeNode(1);
		TreeNode c4 = new TreeNode(3);
		TreeNode c5 = new TreeNode(6);
		TreeNode c6 = new TreeNode(9);

		root.left = c1;
		root.right = c2;
		c1.left=c3;
		c1.right=c4;
		
		c2.left = c5;
		c2.right =c6;
		
		invertTree(root);
		
		System.err.println(root);
	}
	
	
	/**
	 * Definition for a binary tree node. public class TreeNode { int val;
	 * TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 */
	public static  TreeNode invertTree(TreeNode root) {
		if (root != null) {
			TreeNode left = root.left;
			TreeNode right = root.right;

			if (left != null) {
				root.right = left;
				invertTree(left);
			}else{
				root.right =null;
			}

			if (right != null) {
				root.left = right;
				invertTree(right);
			}else{
				root.left=null;
			}
		}
		return root;

	}
}
