package sort;

import org.junit.Test;

import sort.Sort.ListNode;

public class TestSort {
	int[][] a = new int[][] { { 4, 3, 2, 9, 6, 8, 5, 7, 16, 12, 14 }, { 0 },
			{ 40, 3, 3, 2, 2, }, { 0, 0 } };
	ListNode[][] n = new ListNode[a.length][a[0].length];

	@Test
	public void testInsertionSort() {
		for (int j = 0; j < a.length; j++) {
			int[] arr = a[j];
			n[j][0] = new ListNode(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				n[j][i] = new ListNode(arr[i]);
				n[j][i - 1].next = n[j][i];
			}
			InsertionSort.insertionSort(arr);
		}
		
		for (int i = 0; i < n.length; i++) {
			n[i][0] = InsertionSort.insertionSort(n[i][0]);
			ListNode cur = n[i][0];
			while (cur != null) {
				System.out.print(cur.val + " ");
				cur = cur.next;
			}
			System.out.println();
		}
		return;
	}
	
	@Test 
	public void testShellSort() {
		for (int j = 0; j < a.length; j++) {
			int[] arr = a[j];
			InsertionSort.insertionSort(arr);
		}
		return;
	}

}
