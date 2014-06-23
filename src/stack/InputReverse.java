package stack;

import java.util.Scanner;
import java.util.Stack;

public class InputReverse {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = null;

		while ((input = scan.nextLine()) != null) {
			if (input.equals("exit")) {
				scan.close();
				System.exit(0);

			}
			System.out.println(reverse(input));
		}

		scan.close();
	}

	public static String reverse(String input) {
		String result = null;
		StringBuffer sb = new StringBuffer();
		Stack<Character> s = new Stack<Character>();
		for (char c : input.toCharArray()) {
			s.push(c);
		}

		while (!s.isEmpty()) {
			sb.append(s.pop());
		}

		result = sb.toString();
		sb.setLength(0);

		return result;
	}
}
