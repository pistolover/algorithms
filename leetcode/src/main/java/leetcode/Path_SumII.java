package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Path_SumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		if (root == null) {
			return new LinkedList<List<Integer>>();
		}

		List<Integer> list = new LinkedList<Integer>();
		LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
		goSum(root, 0, sum, result, list);
		return result;

	}

	static void goSum(TreeNode root, int currSum, int sum,
			LinkedList<List<Integer>> result, List<Integer> list) {
		if (root == null)
			return;
		currSum += root.val;
		list.add(root.val);
		if (currSum == sum && root.left == null && root.right == null) {
			result.add(new LinkedList<Integer>(list));
		}

		goSum(root.left, currSum, sum, result, list);
		goSum(root.right, currSum, sum, result, list);
		currSum -= root.val;
		list.remove(list.size() - 1);
		return;
	}
}
