package sort;

public class SelectionSort extends Sort {
	public static void selectionSort(int[] a) {
		if (a == null || a.length < 1) {
			return;
		}

		for (int i = 0; i < a.length - 1; i++) {
			swap(a, i, findMin(a, i));

		}
	}

	public static int findMin(int[] a, int start) {
		int min = start;
		while (start < a.length - 1) {
			if (a[start] < a[min]) {
				min = start;
			}
			start++;
		}
		return min;
	}
}
