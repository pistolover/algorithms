package leetcode;

import leetcode.utils.ListNode;

public class Palindrome_LinkedList {

	/* Could you do it in O(n) time and O(1) space? */

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);

		n1.next = n2;

		isPalindrome(n1);
	}

	public static boolean isPalindrome(ListNode head) {

		// 思路为将链表翻转，判断新旧链表是否对应位置元素相等
		if (head == null || (head!=null&&head.next==null))
			return true;
		
		ListNode fis = head;
		ListNode sed = head.next;
		fis.next = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(fis.val);
		while (sed != null) {
			buffer.append(sed.val);
			ListNode thd = sed.next;
			sed.next = fis;
			fis = sed;
			sed = thd;
		}

		StringBuffer buffer2 = new StringBuffer();

		while(fis!=null){
			buffer2.append(fis.val);
			fis = fis.next;
		}

		return buffer2.toString().equals(buffer.toString());
	}
}
