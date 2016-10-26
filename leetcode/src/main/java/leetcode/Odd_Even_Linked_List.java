package leetcode;

import leetcode.utils.ListNode;

public class Odd_Even_Linked_List {

	/**
	 * 1-2-3-4-5-6-7-8-9
	 * 1-3-2-4-5...
	 * 1-3-5-2-4...
	 * 
	 * Definition for singly-linked list. public class ListNode { int val;
	 * ListNode next; ListNode(int x) { val = x; } } Given 1->2->3->4->5->NULL,
	 * return 1->3->5->2->4->NULL.
	 * 
	 */

	public static void main(String[] args) {
		ListNode r = new ListNode(1);
		r.next = new ListNode(2);
		r.next.next = new ListNode(3);
		r.next.next.next = new ListNode(4);
		r.next.next.next.next = new ListNode(5);
		r.next.next.next.next.next = new ListNode(6);
		r.next.next.next.next.next.next = null;

		System.err.println(oddEvenList(r));
	}

	public static ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode odd = new ListNode(0);
		ListNode even = new ListNode(0);
		ListNode currodd = odd;
		ListNode curreven = even;
		int i = 0;
		ListNode root = head;
		while (root != null) {
			i++;
			if (i % 2 != 0) {
				currodd.next = new ListNode(root.val);
				currodd = currodd.next;
			} else {
				curreven.next = new ListNode(root.val);
				curreven = curreven.next;
			}
			root = root.next;
		}
		currodd.next = even.next;

		return odd.next;
	}

	//TODO 
	public ListNode oddEvenList0(ListNode head) {
		return null;
	}
}
