package sort;

import java.util.Queue;
import java.util.LinkedList;

public class RadixSort extends Sort {
	public static void radixSort(int[] a, int radixLen) {
		if (a == null || a.length < 2 || radixLen == 0) {
			return;
		}
		// 用queue可以保持稳定性
		Queue<Integer>[] radix = new Queue[10];
		for (int i = 0; i < 10; i++) {
			radix[i] = new LinkedList<Integer>();
		}
		int digit = 1;
		for (int i = 0; i < radixLen; i++) {
			for (int j = 0; j < a.length; j++) {
				// digit表示处理到个十百千位
				radix[a[j] / digit - a[j] / digit / 10 * 10].add(a[j]);
			}
			int cur = 0;
			for (Queue<Integer> rad : radix) {
				while (!rad.isEmpty()) {
					a[cur++] = rad.poll();
				}
			}
			digit *= 10;
		}
	}
}
