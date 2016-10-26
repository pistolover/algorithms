package leetcode;

import leetcode.utils.ListNode;

public class Merge_Two_Sorted_Lists {
	public ListNode mergeTwoLists(ListNode L1, ListNode L2) {
		if (L1 == null && L2 == null)
			return null;
		if (L1 == null && L2 != null)
			return L2;
		if (L1 != null && L2 == null)
			return L1;

		ListNode p = null;
		ListNode q = p;

		while (L1 != null && L2 != null) {
			if (L1.val < L2.val) {
				if (p == null) {
					p = L1;
					q = p;
					L1 = L1.next;
					continue;
				}
				q.next = L1;
				q = q.next;
				L1 = L1.next;

			} else {
				if (p == null) {
					p = L2;
					q = p;
					L2 = L2.next;
					continue;
				}
				q.next = L2;
				q = q.next;
				L2 = L2.next;
			}
		}

		while (L1 != null) {
			q.next = L1;
			q = q.next;
			L1 = L1.next;
		}

		while (L2 != null) {
			q.next = L2;
			q = q.next;
			L2 = L2.next;
		}

		return p;
	}
}
