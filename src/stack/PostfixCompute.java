package stack;

import java.util.Stack;
import java.util.regex.Pattern;

public class PostfixCompute {
	public static int compute(String expression) throws Exception {
		// Integer result = null;
		Stack<Integer> s = new Stack<Integer>();
		// Pattern operPattern = Pattern.compile("[+|-|*|/]");
		// Pattern numPattern = Pattern.compile("^-?[1-9]d*$");
		String regOper = "[-+*/]";
		String regNum = "^-?[1-9]\\d*$";

		// Matcher mOper = null;
		// Matcher mNum = null;
		String[] expArr = expression.split(" ");
		for (int i = 0; i < expArr.length; i++) {
			// mOper = operPattern.matcher(expArr[i]);
			// mNum = numPattern.matcher(expArr[i]);
			// if (mNum.matches()) {
			if (Pattern.matches(regNum, expArr[i])) {
				s.push(Integer.parseInt(expArr[i]));
				// } else if (mOper.matches()) {
			} else if (Pattern.matches(regOper, expArr[i])) {
				if (s.size() < 2)
					throw new Exception(
							"there must be some mistakes on input expression");
				s.push(operate(s.pop(), s.pop(), expArr[i]));
			}
			System.out.println(s);
		}
		if (s.size() != 1 && !Pattern.matches(regNum, s.peek().toString()))
			throw new Exception(
					"there must be some mistakes on input expression");
		return s.peek().intValue();
	}

	private static Integer operate(Integer num2, Integer num1, String operator)
			throws Exception {
		Integer result = null;
		switch (operator) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			if (num2 == 0) {
				throw new Exception("wrong input of 0 as divider");
			}
			result = num1 / num2;
			break;
		}
		return result;
	}
}
