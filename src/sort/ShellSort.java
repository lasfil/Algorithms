package sort;

public class ShellSort extends Sort{
	public static void shellSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		
		for (int d = a.length / 2; d >= 0; d /= 2) {
			for (int i = 0; i < d; i++) {
				for (int j = 1; i + j * d < a.length; j++) {
					int k = i + j * d ;
					while (k >= i) {
						if (a[k] < a [k - d]) {
							swap(a, k, k - d);
						}
						k -= d;
					}
				}
			}
		}
	}
}
