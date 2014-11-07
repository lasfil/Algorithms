package sort;

public class ShellSort extends Sort {
	public static void shellSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		// d是递增量，每次缩小一倍
		for (int d = a.length / 2; d > 0; d /= 2) {
			// i是起始位置，从0到d－1
			for (int i = 0; i < d; i++) {
				// j是i向后第j个d，向前做插入排序
				for (int j = 1; i + j * d < a.length; j++) {
					int k = i + j * d;
					while (k > i && a[k] < a[k - d]) {

						swap(a, k, k - d);

						k -= d;
					}
				}
			}
		}
	}
}
