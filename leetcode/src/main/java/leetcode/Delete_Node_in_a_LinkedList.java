package leetcode;

import leetcode.utils.ListNode;

public class Delete_Node_in_a_LinkedList {
	
	public void deleteNode(ListNode node) {
		if (node == null)
			return;

		ListNode after = node.next;
		if (after != null) {
			int value = after.val;
			node.val = value;
			node.next = after.next;
		}
	}
}
