package sort;

import java.util.Arrays;

import org.junit.Test;

public class HeapSort extends Sort {
	@Test
	public void test() {

		System.out.println(Arrays.toString(heapSort(new int[] { 33, 221, 221, 167, 151, 173, 60, 58, 44, 181, 207,
				287, 281, 297, 204, 249, 111, 163, 291, 132, 200, 129, 205, 11, 57, 183, 175, 103, 68, 276, 251, 192,
				177, 77, 246, 239, 105, 28, 204, 70, 292, 91, 211, 140, 228, 203, 67, 127, 100, 282 })));
	}

	public static int[] heapSort(int[] a) {
		if (a == null || a.length < 2) {
			return a;
		}

		buildMaxHeap(a);
		// 堆排序就是将大顶堆的第i个顶和a的第i项交换，然后从顶向i-1重新堆化一遍
		for (int i = a.length - 1; i > 0; i--) {
			swap(a, i, 0);
			maxHeapify(a, 0, i - 1);
		}
		return a;
	}

	public static void buildMaxHeap(int[] a) {
		// 建堆的过程就是从下到上将所有父结点堆化的过程
		for (int i = a.length / 2 - 1; i >= 0; i--) {
			maxHeapify(a, i, a.length - 1);
		}
	}

	private static void maxHeapify(int[] a, int index, int end) {
		// index的左孩子是2*index＋1， 右孩子是2*index＋2，
		// 所以判断while的边界是2*index＋2不能大于end
		while (2 * index + 1 <= end) {
			// large是左右孩子中更大的那个的下标
			// 注意一种情况就是右孩子不存在

			int larger = 2 * index + 1;
			if (2 * index + 2 <= end) {
				larger = a[index * 2 + 1] > a[index * 2 + 2] ? index * 2 + 1 : index * 2 + 2;
			}
			// index的值比larger大的话证明堆的顺序正确，不需要再继续
			if (a[index] > a[larger]) {
				return;
			} else {
				// 孩子结点比父结点大，所以父结点跟larger交换，然后index变成larger继续判断
				swap(a, index, larger);
				index = larger;
			}
		}
	}
}
