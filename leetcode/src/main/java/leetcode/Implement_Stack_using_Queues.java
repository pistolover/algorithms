package leetcode;

import java.util.LinkedList;
import java.util.List;

public class Implement_Stack_using_Queues {

	// Push element x onto stack.

	private List<Integer> list = new LinkedList<Integer>();
	private List<Integer> list0 = new LinkedList<Integer>();

	public void push(int x) {
		if (list.size() == 0 && list0.size() == 0) {
			list.add(x);
			return;
		}

		if (list.size() != 0) {
			list.add(x);
			return;
		}

		if (list0.size() != 0) {
			list0.add(x);
		}

	}

	// Removes the element on top of the stack.
	public void pop() {

		if (list.size() != 0 && list0.size() == 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				list0.add(list.get(i));
			}
			list.clear();
			return;
		}

		if (list.size() == 0 && list0.size() != 0) {
			for (int i = 0; i < list0.size() - 1; i++) {
				list.add(list0.get(i));
			}
			list0.clear();
		}

	}

	// Get the top element.
	public int top() {
		if (list.size() != 0 && list0.size() == 0) {
			return list.get(list.size() - 1);
		}

		if (list.size() == 0 && list0.size() != 0) {
			return list0.get(list0.size() - 1);
		}

		return -1;
	}

	// Return whether the stack is empty.
	public boolean empty() {

		return list0.size() == 0 && list.size() == 0;
	}
}
