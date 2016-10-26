package leetcode;

import leetcode.utils.ListNode;

public class Linked_List_CycleII {
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)
			return null;

		ListNode curr = head;
		ListNode sec = head;

		while (sec.next != null && sec.next.next != null) {
			curr = curr.next;
			sec = sec.next.next;
			if (curr == sec) {
				curr = head;
				while (curr != sec) {
					curr = curr.next;
					sec = sec.next;
				}
				return curr;
			}
		}
		return null;
	}
}
