package others;

import java.awt.image.RescaleOp;
import java.util.Stack;

// 输入1047000 输出7401
public class StackPrint {

	public static void main(String[] args) {
		System.err.println(print("1047000"));
	}

	public static String print(String s) {

		Stack<Character> stack = new Stack<Character>();

		for (Character c : s.toCharArray()) {
			stack.push(c);
		}
		String result = "";
		while(!stack.isEmpty()){
			if(stack.peek().equals('0') && result.equals("")) {
				stack.pop();
				continue;
			}else{
				result = result + stack.pop();
			}
		}
		return result;

	}
}
