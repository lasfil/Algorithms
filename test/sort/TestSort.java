package sort;

import org.junit.Before;
import org.junit.Test;

import sort.Sort.ListNode;

public class TestSort {
	int[][] a;
	ListNode[][] n;

	private void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	@Before
	public void setUp() {
		a = new int[][] {
				{ 4, 3, 2, 9, 6, 8, 5, 7, 16, 12, 14, 1, 23, 16, 18, 15, 21, 2, 23, 23 },
				{ 0 }, { 40, 3, 3, 2, 2, }, { 0, 0, 0, 0 }, { 2, 1, 2 }, { 2, 2, 1 } };
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

	// @After
	public void tearDown() {
		for (int j = 0; j < a.length; j++) {
			System.out.println(n[j][0]);

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

	@Test
	public void testMerge() {
		int[][] B = new int[][] { { 1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7 },
				{ 3, 1, 2, 5, 7 }, { 2, 3, 4, 5, 1 }, { 3, 0 } };
		MergeSort.merge(B[0], 0, 3, 10);
		printArray(B[0]);
		MergeSort.merge(B[1], 0, 0, 4);
		printArray(B[1]);
		MergeSort.merge(B[3], 0, 0, 1);
		printArray(B[3]);
	}

	@Test
	public void testMergeSort() {
		for (int[] arr : a) {
			MergeSort.mergeSort(arr);
			printArray(arr);
		}
		System.out.println("------");
		for (ListNode[] l : n) {
			l[0] = MergeSort.mergeSort(l[0]);
		}
		System.out.println("------");
		tearDown();
	}

	@Test
	public void testMergeSortBU() {
		for (int[] arr : a) {
			MergeSort.mergeSortBU(arr);
			printArray(arr);
		}
	}

	@Test
	public void testHeapSort() {
		for (int[] arr : a) {
			HeapSort.heapSort(arr);
			printArray(arr);
		}
	}

	@Test
	public void testSelectionSort() {
		for (int[] arr : a) {
			SelectionSort.selectionSort(arr);
			printArray(arr);
		}
	}
	@Test
	public void testBubbleSort() {
		for (int[] arr : a) {
			BubbleSort.bubbleSort(arr);
			printArray(arr);
		}
	}

}
