package leetcode;

import leetcode.utils.ListNode;

public class Intersection_of_Two_Linked_Lists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int lenA = 0;
		int lenB = 0;
		ListNode A = headA;
		ListNode B = headB;

		if (A == null || B == null)
			return null;

		while (A.next != null) {
			lenA++;
			A = A.next;

		}

		while (B.next != null) {
			lenB++;
			B = B.next;
		}

		// 如果有环状的，终点会不一样
		if (A != B)
			return null;

		A = headA;
		B = headB;

		if (lenA > lenB) {
			for (int i = 0; i < lenA - lenB; i++) {
				A = A.next;
			}
		} else {
			for (int i = 0; i < lenB - lenA; i++) {
				B = B.next;
			}
		}

		while (A != B) {
			A = A.next;
			B = B.next;
		}
		return A;
	}
}
