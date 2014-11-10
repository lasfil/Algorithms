package sort;

public class MergeSort extends Sort {
	public static void mergeSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		mergeSort(a, 0, a.length - 1);

	}

	public static void mergeSortBU(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		// for (int size = 1; size < a.length; size *= 2) {
		//
		// for (int i = 0; i + size < a.length; i += 2 * size) {
		//
		// merge(a, i, i + size - 1, Math.min(a.length - 1, i + 2 * size - 1));
		// }
		// }
		// size是merge的子序列的总长度，从2开始，一直到size小于两倍的a.length
		for (int size = 2; size / 2 < a.length; size *= 2) {
			// i以size为步长，i到i+size－1的子集进行merge，mid就是i + size / 2 - 1
			// tail是i+size-1但是需要考虑这个值越界，所以取它和length－1更小的
			for (int i = 0; i < a.length; i += size) {
				merge(a, i, i + size / 2 - 1,
						Math.min(a.length - 1, i + size - 1));
			}
		}
	}

	public static void mergeSort(int[] a, int start, int end) {
		if (start == end) {
			return;
		}
		int mid = (end + start) / 2;
		// 左半边先排序，递归调用
		mergeSort(a, start, mid);
		// 左半边排好序后右半边排序
		mergeSort(a, mid + 1, end);
		// 最后两边merge
		merge(a, start, mid, end);
	}

	public static void merge(int[] a, int head, int mid, int tail) {
		if (head == tail || mid >= tail) {
			return;
		}
		int[] aux = new int[mid - head + 1];
		for (int i = head; i <= mid; i++) {
			aux[i - head] = a[i];
		}
		int indexA = mid + 1;
		int indexAux = 0;
		int i = head;
		while (indexA <= tail && indexAux < aux.length) {
			if (aux[indexAux] < a[indexA]) {
				a[i++] = aux[indexAux++];
			} else {
				a[i++] = a[indexA++];
			}
		}
		// 如果indexAux等于aux最后的话indexA后边的肯定在对的位置，
		// 不用考虑，只有indexAux小于aux最后一个的时候要把剩下的拷贝到i后边
		while (indexAux < aux.length) {
			a[i++] = aux[indexAux++];
		}

	}

	public static ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode anchor = new ListNode(0);
		anchor.next = head;
		ListNode mid = head;
		int step = 0;
		while (head.next != null) {
			head = head.next;
			step = ~step;
			if (step == 0) {
				mid = mid.next;
			}
		}
		head = mid.next;
		mid.next = null;
		mid = head;
		
		head = mergeSort(anchor.next);
		mid = mergeSort(mid);
		anchor.next = merge(head, mid);
		head = anchor.next;
		anchor = null;
		return head;
	}

	public static ListNode merge(ListNode first, ListNode second) {
		if (first == null) {
			return second;
		}
		if (second == null) {
			return first;
		}
		ListNode front = new ListNode(0);
		ListNode tail = front;

		while (first != null && second != null) {
			if (first.val < second.val) {
				tail.next = first;
				first = first.next;
				tail = tail.next;
				tail.next = null;
			} else {
				tail.next = second;
				second = second.next;
				tail = tail.next;
				tail.next = null;
			}
		}

		tail.next = first == null ? second : first;

		first = front.next;
		front = null;
		return first;
	}
	

}
