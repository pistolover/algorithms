package leetcode;

import java.util.Stack;

import leetcode.utils.ListNode;

public class Remove_Nth_Node_From_End_of_List {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null)
			return null;
		Stack<ListNode> s1 = new Stack<ListNode>();
		Stack<ListNode> s2 = new Stack<ListNode>();

		int count = 0;
		while (head != null) {
			s1.push(head);
			head = head.next;
		}

		while (s1.size() != 0) {
			ListNode pop = s1.pop();
			pop.next = null;
			count++;
			if (count == n) {
				continue;
			} else {
				s2.push(pop);
			}
		}

		ListNode result = null;
		ListNode flag = null;
		while (s2.size() != 0) {
			ListNode pop = s2.pop();
			if (result == null) {
				result = pop;
				flag = result;
				continue;
			}
			flag.next = pop;
			flag = flag.next;
		}
		return result;
	}
}
