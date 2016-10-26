package leetcode;

import java.util.Stack;

/**
 * @author liqqc
 *
 */
public class Implement_Queue_using_Stacks {

	public Stack<Integer> _stack1 = new Stack<Integer>();
	public Stack<Integer> _stack2 = new Stack<Integer>();

	public void push(int x) {
		while (!_stack2.isEmpty()) {
			_stack1.push(_stack2.pop());
		}
		_stack1.push(x);
		while (!_stack1.isEmpty()) {
			_stack2.push(_stack1.pop());
		}

	}

	// Removes the element from in front of queue.
	public void pop() {
		_stack2.pop();
	}

	// Get the front element.
	public int peek() {
		return _stack2.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return _stack2.isEmpty();
	}
}
