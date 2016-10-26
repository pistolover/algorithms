package leetcode;

import leetcode.utils.ListNode;

public class Reverse_Linked_List {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null)
			return null;
		if (head != null && head.next == null)
			return head;
		ListNode fis = head;
		ListNode sed = head.next;
		fis.next = null;
		while (sed != null) {
			ListNode thd = sed.next;
			sed.next = fis;
			fis = sed;
			sed = thd;
		}
		return fis;
	}
}
