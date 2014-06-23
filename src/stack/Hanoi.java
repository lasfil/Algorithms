package stack;

import java.util.Stack;

public class Hanoi {
	private Stack<Integer> start;
	private Stack<Integer> cache;
	private Stack<Integer> end;

	private int n;

	public Stack<Integer> getStart() {
		return this.start;
	}

	public Stack<Integer> getEnd() {
		return this.end;
	}

	public Hanoi(int n) {
		this.n = n;

		start = new Stack<Integer>();
		for (int i = n; i > 0; i--)
			start.push(i);
		cache = new Stack<Integer>();

		end = new Stack<Integer>();
		
	}

	private void move(Stack<Integer> source, int i, Stack<Integer> dest) {
		if (source.peek() == i && (dest.empty() || i < dest.peek())) {
			dest.push(source.pop());
		}
	}

	public void hanoi(int n, Stack<Integer> start, Stack<Integer> cache,
			Stack<Integer> end) {

		if (n == 1) {
			this.move(start, n, end);
		} else if (n > 1) {
			System.out.println(" n: " + n + "step0 start: " + start
					+ " cache: " + cache + " end: " + end);
			hanoi(n - 1, start, end, cache);
			System.out.println(" n: " + n + "step1 start: " + start
					+ " cache: " + cache + " end: " + end);

			move(start, n, end);
			System.out.println(" n: " + n + "step2 start: " + start
					+ " cache: " + cache + " end: " + end);

			hanoi(n - 1, cache, start, end);
			System.out.println(" n: " + n + "step3 start: " + start
					+ " cache: " + cache + " end: " + end);

		}
	}

	public void hanoi() {
		hanoi(this.n, this.start, this.cache, this.end);
	}

}
