package list;

import java.util.ArrayList;

public class ArrayLIstSort {
	public static <T extends Comparable<T>> void selectionSort(ArrayList<T> al) {
		if (al.size() < 2)
			return;
		int min;
		T temp;

		for (int index = 0; index < al.size() - 1; index++) {
			min = index;
			for (int i = min + 1; i < al.size(); i++) {
				if (al.get(i).compareTo(al.get(min)) < 0) {
					min = i;
				}
			}

			if (min != index) {
				temp = al.get(index);
				al.set(index, al.get(min));
				al.set(min, temp);
			}
		}
	}

	public static <T extends Comparable<T>> void insertionSort(ArrayList<T> al) {
		for (int index = 1; index < al.size(); index++) {
			T key = al.get(index);
			int scan = index;
			while (scan > 0 && al.get(scan - 1).compareTo(key) > 0) {
				al.set(scan, al.get(scan - 1));
				scan--;
			}

			al.set(scan, key);

		}
	}

	public static <T extends Comparable<T>> void bubbleSort(ArrayList<T> al) {
		int count = al.size() - 1;
		T temp;
		while (count > 0) {
			// 这个flag检验一次遍历有没有位置的移动，如果没有表示序列已经排好序，不需再进入while
			boolean mod = false;

			for (int i = 0; i < count; i++) {
				if (al.get(i).compareTo(al.get(i + 1)) > 0) {
					temp = al.get(i);
					al.set(i, al.get(i + 1));
					al.set(i + 1, temp);
					mod = true;
				}
			}
			System.out.println(al);
			if (mod == false)
				break;
			count--;
		}
	}

	public static <T extends Comparable<T>> void quickSort(ArrayList<T> al) {
		if (al.size() < 2)
			return;
		quickSort(al, 0, al.size() - 1);
	}

	private static <T extends Comparable<T>> void quickSort(ArrayList<T> al,
			int start, int end) {
		int left = start;
		int right = end;
		T temp;

		while (left < right) {
			while (left < right && al.get(left).compareTo(al.get(start)) <= 0) {
				left++;
			}

			while (al.get(right).compareTo(al.get(start)) > 0) {
				right--;
			}

			if (left < right) {
				temp = al.get(left);
				al.set(left, al.get(right));
				al.set(right, temp);
			}
		}

		temp = al.get(start);
		al.set(start, al.get(right));
		al.set(right, temp);

		if (right - start > 1)
			quickSort(al, start, right - 1);

		if (end - right > 1)
			quickSort(al, right + 1, end);

	}

	public static <T extends Comparable<T>> void mergeSort(ArrayList<T> al) {
		if (al.size() < 2)
			return;

		mergeSort(al, 0, al.size() - 1);
	}

	private static <T extends Comparable<T>> void mergeSort(ArrayList<T> al,
			int start, int end) {
		int mid = (start + end) / 2;

		if (end > start + 1) {
			mergeSort(al, start, mid);
			mergeSort(al, mid + 1, end);
		}

		ArrayList<T> temp = new ArrayList<T>();

		int left = start;
		int right = mid + 1;

		while (left <= mid && right <= end) {
			if (al.get(left).compareTo(al.get(right)) <= 0) {
				temp.add(al.get(left));
				left++;

			} else {
				temp.add(al.get(right));
				right++;

			}

		}

		if (left <= mid) {
			temp.addAll(al.subList(left, mid + 1));
		}

		if (right <= end) {
			temp.addAll(al.subList(right, end + 1));
		}

		for (int i = start; i <= end; i++) {
			al.set(i, temp.get(i - start));
		}

	}

}
