package sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sort.Sort.ListNode;

public class TestSort {
	int[][] a;
	ListNode[][] n;

	@Before
	public void setUp() {
		a = new int[][] { { 4, 3, 2, 9, 6, 8, 5, 7, 16, 12, 14 }, { 0 },
				{ 40, 3, 3, 2, 2, }, { 0, 0 } };
		n = new ListNode[a.length][a[0].length];
		for (int j = 0; j < a.length; j++) {
			int[] arr = a[j];
			n[j][0] = new ListNode(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				n[j][i] = new ListNode(arr[i]);
				n[j][i - 1].next = n[j][i];
			}
		}
	}

	@After
	public void tearDown() {
		for (int j = 0; j < a.length; j++) {
			ListNode cur = n[j][0];
			while (cur != null) {
				System.out.print(cur.val + " ");
				cur = cur.next;
			}
			System.out.println();

		}
		
		for (int j = 0; j < a.length; j++) {
			for (int i = 0; i < a[j].length; i++) {
				System.out.print(a[j][i] + " ");
			}
			System.out.println();

		}
	}

	@Test
	public void testInsertionSort() {
		for (int j = 0; j < a.length; j++) {
			InsertionSort.insertionSort(a[j]);
			n[j][0] = InsertionSort.insertionSort(n[j][0]);
		}

		return;
	}

	@Test
	public void testShellSort() {
		for (int j = 0; j < a.length; j++) {
			int[] arr = a[j];
			ShellSort.shellSort(arr);
		}
		return;
	}

	@Test
	public void testQuickSort() {
		for (int j = 0; j < a.length; j++) {
			QuickSort.quickSort(a[j]);
			n[j][0] = QuickSort.quickSort(n[j][0]);

			
		}
		return;
	}

}
