package leetcode;

import leetcode.utils.ListNode;

public class Remove_Duplicates_from_Sorted_List {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return null;
		ListNode first = head;
		ListNode second = first.next;
		while (second != null) {
			if (first.val == second.val) {
				ListNode last = second.next;
				while (last != null && first.val == last.val) {
					last = last.next;
				}
				if (last != null) {
					first.next = last;
					first = last;
					second = first.next;
				} else {
					first.next = null;
					return head;
				}

			} else {
				first = second;
				second = first.next;
			}
		}
		return head;
	}
}
