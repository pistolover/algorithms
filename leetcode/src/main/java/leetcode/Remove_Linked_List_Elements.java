package leetcode;

import leetcode.utils.ListNode;

public class Remove_Linked_List_Elements {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(3);
		ListNode n6 = new ListNode(1);
		ListNode n7 = new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		removeElements(n1, 1);

	}

	/**
	 * @author liqqc
	 * @param head
	 * @param val
	 */
	public static ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return null;

		ListNode r = head;
		ListNode s = head;
		ListNode t = head.next;
		// 起始位置的确定
		while (s != null && s.val == val) {
			s = s.next;
			if (t != null) {
				t = t.next;
			}
			r = s;
		}

		while (t != null) {
			// 如果相同则移除
			if (t.val == val) {
				t = t.next;
				s.next = t;
			} else {
				s = s.next;
				t = t.next;
			}
		}
		return r;
	}
}
