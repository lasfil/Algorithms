package sort;

public class HeapSort extends Sort {

	public static void heapSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}

		buildMaxHeap(a);
		// 堆排序就是将大顶堆的第i个顶和a的第i项交换，然后从顶向i-1重新堆化一遍
		for (int i = a.length - 1; i > 0; i--) {
			swap(a, i, 0);
			maxHeapify(a, 0, i - 1);
		}
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

			int larger = end;
			if (2 * index + 2 <= end) {
				larger = a[index * 2 + 1] > a[index * 2 + 2] ? index * 2 + 1
						: index * 2 + 2;
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
