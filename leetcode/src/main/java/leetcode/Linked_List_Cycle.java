package leetcode;

import leetcode.utils.ListNode;

public class Linked_List_Cycle {
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null)
			return false;

		ListNode curr = head;
		ListNode sec = head;

		while (sec.next != null && sec.next.next != null) {
			curr = curr.next;
			sec = sec.next.next;
			if (curr == sec) {
				return true;
			}
		}
		return false;
	}
}
