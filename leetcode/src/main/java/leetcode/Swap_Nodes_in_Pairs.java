package leetcode;

import leetcode.utils.ListNode;

public class Swap_Nodes_in_Pairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode curr = head;
		ListNode flag = null;
		ListNode result = null;
		ListNode falg1 = null;

		while (curr != null && curr.next != null) {
			if (result == null) {
				result = curr.next;
				falg1 = curr.next.next;
				curr.next.next = curr;
				curr.next = falg1;
				flag = curr;
				curr = curr.next;
			} else {
				falg1 = curr.next.next;
				flag.next = curr.next;

				flag.next.next = curr;
				curr.next = falg1;
				flag = curr;
				curr = curr.next;
			}
		}

		return result;
	}
}
