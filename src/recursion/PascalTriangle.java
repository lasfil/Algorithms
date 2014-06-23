package recursion;


public class PascalTriangle {
	private int[][] triangle;
	private int line;

	public PascalTriangle(int line) {
		this.line = line;
		triangle = new int[line][line * 2 + 3];
	}

	public void produce() {
		produce(line - 1);
	}

	public int[] produce(int n) {
		if (n > line) {
			System.out.println("line number out of bound");
			System.exit(0);
		} else if (n == 0) {
			triangle[0][line + 1] = 1;
		} else if (n > 0) {
			// triangle[n][0] = 1;
			int[] lastLine = produce(n - 1);
			for (int i = line + 1 - n; i <= line + 1 + n; i++)
				triangle[n][i] = lastLine[i - 1] + lastLine[i + 1];

		}

		return triangle[n];

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		int digit;
		int maxDigit = (int) Math.log10(triangle[line - 1][line + 1]) + 1;
		for (int i = 0; i < maxDigit; i++) {
			sb.append(" ");
		}
		String zero = sb.toString();
		sb.setLength(0);
		for (int[] line : triangle) {
			for (int i : line) {
				/*
				 * if (i > 0) { if (i < 10) sb.append(" "); sb.append(i); } else
				 * { sb.append("  "); // sb.append("\n"); }
				 */
				if (i == 0) {
					sb.append(zero);
				} else {
					digit = (int) Math.log10(i) + 1;
					for (int j = 0; j < maxDigit - digit; j++) {
						sb.append(" ");
					}
					sb.append(i);
				}
			}
			sb.append("\n");

		}
		String result = sb.toString();
		sb.setLength(0);

		return result;
	}

	public void print() {
		for (int[] line : triangle) {
			for (int i : line) {
				// if (i > 0)
				System.out.print(i);
				// else
				// break;
			}
			System.out.println();

		}
	}
}
