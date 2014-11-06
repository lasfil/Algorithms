package sort;

public abstract class Sort {
	public static void swap(int[] a, int i, int j) {
		if (a == null || i > a.length || j > a.length) {
			return;
		}
		
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	public static ListNode swap(ListNode before) {
		if (before == null || before.next == null || before.next.next == null) {
			return before;
		}
		
		ListNode cur = before.next;
		ListNode after = cur.next;
		before.next = after;
		cur.next = after.next;
		after.next = cur;
		before = after;
		return before;
		
	}
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
		
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
		public String toString() {
			return val + "";
		}
	}
}
