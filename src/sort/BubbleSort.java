package sort;

public class BubbleSort extends Sort{
	public static void bubbleSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		
		for (int i = a.length - 1; i > 0; i--) {
			int j = 0;
			while (j < i) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j+1);
				}
				j++;
			}
		}
	}
}
