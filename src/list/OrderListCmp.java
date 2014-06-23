package list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderListCmp {

	public static void main(String[] args) {
		List<Integer> la = Arrays.asList(5, 3, 4, 34, 23, 99);
		List<Integer> lb = Arrays.asList(4, 5, 3, 23, 34, 99,123);

		int s = compare(la, lb);
		System.out.println(s);
		System.out.println(compare1(la, lb));

	}

	public static int compare1(List<Integer> la, List<Integer> lb) {
		int result = 0;
		Collections.sort(la);
		Collections.sort(lb);

		int laSize = la.size();
		int lbSize = lb.size();
		int count = laSize < lbSize ? laSize : lbSize;

		for (int i = 0; i <= count; i++) {
			if (i == count) {
				if (i == lbSize) {
					return 0;
				} else {
					return -1;
				}
			}
			if (i >= lbSize) {
				result = 1;
				break;

			} else {
				if (la.get(i) != lb.get(i)) {
					result = la.get(i) > lb.get(i) ? 1 : -1;
					break;
				}
			}

		}

		return result;
	}

	public static int compare(List<Integer> la, List<Integer> lb) {
		Collections.sort(la);
		Collections.sort(lb);
		int result = 0;

		int laSize = la.size();
		int lbSize = lb.size();
		int count = laSize > lbSize ? laSize : lbSize;

		for (int i = 0; i < count; i++) {

			if (i >= laSize) {
				result = -1;
				break;
			}

			if (i >= lbSize) {
				result = 1;
				break;
			}

			System.out.println(la.get(i) + " " + lb.get(i));
			if (la.get(i) != lb.get(i)) {
				result = la.get(i) > lb.get(i) ? 1 : -1;
				break;
			}

		}

		return result;
	}

}
