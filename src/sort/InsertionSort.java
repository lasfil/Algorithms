package sort;

public class InsertionSort extends Sort {
	public static void insertionSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}

		for (int i = 1; i < a.length; i++) {
			int k = i;
			while (k > 0 && a[k] < a[k - 1]) {
				swap(a, k, k - 1);
				k--;
			}
		}

	}

	public static ListNode insertionSort(ListNode root) {
		ListNode head = new ListNode(Integer.MIN_VALUE, root);
		ListNode cur = root;
		ListNode tmp = null;
		while (cur.next != null) {
			// 如果当前val大于下一个，下一个需要重新插入前边正确的位置
			if (cur.val > cur.next.val) {
				tmp = cur.next;
				cur.next = tmp.next;
				tmp.next = null;
				ListNode insert = head;
				while (insert.next != null && tmp.val > insert.next.val) {
					insert = insert.next;
				}
				tmp.next = insert.next;
				insert.next = tmp;
			} else {
				// 如果当前val小于等于下一个，cur向后移动
				cur = cur.next;
			}
		}

		root = head.next;
		head = null;
		return root;
	}
}
