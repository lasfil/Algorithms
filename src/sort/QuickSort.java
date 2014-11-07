package sort;

public class QuickSort extends Sort {
	public static void quickSort(int[] a) {
		if (a == null) {
			return;
		}
		quickSort(a, 0, a.length);
	}

	public static void quickSort(int[] a, int start, int end) {
		if (end - start < 2) {
			return;
		}
		if (end - start == 2) {
			if (a[start] > a[end - 1]) {
				swap(a, start, end - 1);
			}
			return;
		}

		int low = start + 1;
		int high = end - 1;
		// a[start]是pivot
		while (low <= high) {
			// low处比start小或等low右移一位
			if (a[low] <= a[start]) {
				low++;
				// high处比start大于或者等于，high左移
			} else if (a[high] >= a[start]) {
				high--;
				// 此时low和high正好应该交换
			} else if (a[low] > a[high]) {
				swap(a, low, high);
				low++;
				high--;
			}
		}
		// 最后high停在比pivot小的最后一位上，跟start交换后刚好high作为分界线
		swap(a, start, high);
		quickSort(a, start, high);
		quickSort(a, high + 1, end);
	}

	public static ListNode quickSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode anchor = new ListNode(0);
		ListNode tail = new ListNode(0);
		tail = anchor;
		ListNode cur = head;
		// head作为pivot，比它小的都解下来接在anchor后
		while (cur.next != null) {
			if (cur.next.val >= head.val) {
				cur = cur.next;
			} else {
				ListNode tmp = cur.next;
				cur.next = tmp.next;
				tmp.next = null;
				tail.next = tmp;
				tail = tail.next;
			}
		}
		tail.next = null;
		// anchor后是比pivot小的子集，重新排序后插在head前面
		anchor.next = quickSort(anchor.next);
		for (tail = anchor; tail.next != null; tail = tail.next)
			;
		tail.next = head;
		// head后是比pivot大的子集，重新原地排序
		head.next = quickSort(head.next);
		head = anchor.next;
		anchor = null;
		return head;
	}

}
