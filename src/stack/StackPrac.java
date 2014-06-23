package stack;

import java.util.Arrays;
import java.util.Stack;

public class StackPrac {

	public static void main(String[] args) {
		Stack<String> l = new Stack<String>();
		String[] s = new String[] { "a", "b", "c", "d", "e", "f" };
		
		l.addAll(Arrays.asList(s));
		l.add("x");
		l.remove(3);
		l.trimToSize();
		System.out.println(l.peek());
		System.out.println(l);

	}
}
