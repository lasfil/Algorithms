package stack;

import java.util.Stack;

public class ToBinary {

	
	public static String toBinary(int i) {
		StringBuffer sb = new StringBuffer();

		Stack<Integer> stack = new Stack<Integer>();

		while (i > 0) {
			stack.push(i % 8);
			i = i >> 3;
		}
		
		while(!stack.empty())
			sb.append(stack.pop());

		return sb.toString();
	}

}
