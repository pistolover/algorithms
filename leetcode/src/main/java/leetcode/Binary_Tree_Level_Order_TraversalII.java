package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeNode;

public class Binary_Tree_Level_Order_TraversalII {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		// 注意root为空时不能返回null
		if (root == null)
			return result;

		List<TreeNode> all = new LinkedList<TreeNode>();
		all.add(root);
		int first = 0; // 当前待访问节点，初始为第一个节点，即根节点
		int last = 1; // 当前链表中元素个数，初始只有一个
		while (first < all.size()) { // 如果待访问节点存在于链表
			last = all.size(); // 下一行访问开始，定位last为当前行最后一个节点下一个节点所在位置
			List<Integer> level = new LinkedList<Integer>();
			while (first < last) { // 如果first==last表示该行所有节点都被访问到了，跳出循环
				level.add(all.get(first).val);
				if (all.get(first).left != null) {
					all.add(all.get(first).left);
				}
				if (all.get(first).right != null) {
					all.add(all.get(first).right);
				}
				first++; // 每访问完一个节点就指向下一个节点
			}
			result.add(level);
		}

		List<List<Integer>> fina = new LinkedList<List<Integer>>();

		for (int i = result.size() - 1; i >= 0; i--) {
			fina.add(result.get(i));
		}
		return fina;
	}
}
