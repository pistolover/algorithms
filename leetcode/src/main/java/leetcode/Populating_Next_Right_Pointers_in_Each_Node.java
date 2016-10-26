package leetcode;

import java.util.LinkedList;
import java.util.List;

import leetcode.utils.TreeLinkNode;

public class Populating_Next_Right_Pointers_in_Each_Node {
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		if (root != null && root.left == null && root.right == null)
			root.next = null;

		List<TreeLinkNode> list = new LinkedList<TreeLinkNode>();
		list.add(root);
		int count = 0;
		int level = 0;
		int levelcount = 1;
		while (list.size() != 0) {
			TreeLinkNode fis = list.remove(0);
			count++;
			if (list.size() >= 0 && count != levelcount) {
				TreeLinkNode sec = list.get(0);
				fis.next = sec;
			} else {
				fis.next = null;
				level++;
				levelcount = (int) Math.pow(2, level);
				count = 0;
			}

			if (fis.left != null) {
				list.add(fis.left);
			}

			if (fis.right != null) {
				list.add(fis.right);
			}
		}
	}
}
