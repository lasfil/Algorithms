package list;

public class BiLinkedList<T> {

	private Node<T> first;
	private Node<T> last;
	private int size;

	public int size() {
		return size;
	}

	public void addLast(T t) {
		Node<T> newN = new Node<T>(last, t, null);

		if (last == null) {
			first = newN;
		} else {
			last.next = newN;
			newN.pre = last;
		}

		last = newN;
		last.next = first;
		first.pre = last;

		size++;
	}

	public void printlist() {
		Node<T> n = first;
		int i = 0;

		while (i++ < size) {
			System.out.println(n.data);
			n = n.next;
		}

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		Node<T> n = first;
		int i = 0;
		sb.append("[ ");
		while (i++ < size) {
			sb.append(n.data + " ");
			n = n.next;
		}
		sb.append("]");
		String result = sb.toString();
		sb.setLength(0);
		return result;
	}

	public static <T extends Comparable<T>> void selectSort(BiLinkedList<T> bl) {
		if (bl.size < 2)
			return;
		Node<T> head = new Node<T>(null, null, bl.first);
		bl.first.pre = head;
		Node<T> rear = new Node<T>(bl.last, null, null);
		bl.last.next = rear;
		Node<T> position = head;
		Node<T> n, min;
		while (position.next != rear.pre) {
			min = position.next;
			n = min.next;
			while (n != rear) {

				if (n.data.compareTo(min.data) < 0)
					min = n;
				n = n.next;
			}

			min.pre.next = min.next;
			min.next.pre = min.pre;
			min.next = position.next;
			min.next.pre = min;
			min.pre = position;
			position.next = min;

			position = position.next;

		}

		bl.first = head.next;
		bl.last = rear.pre;
		bl.first.pre = bl.last;
		bl.last.next = bl.first;
		position = null;
		min = null;
		n = null;
		head = null;
		rear = null;

	}

	public static <T extends Comparable<T>> void insertionSort(
			BiLinkedList<T> bl) {

		Node<T> position = bl.first.next;
		Node<T> n;
		T data;

		while (position != bl.first) {
			// n and position start with same position
			n = position;
			data = position.data;
			// 从n的位置向前遍历至first，n的前一个node的data值如果比position的data值大，则n的前一个node的data值挪至n
			// 反之如果n的前一个node的data不再比position的data值大，n持有的空位则为position的data值应该插入的地方
			while (n != bl.first && n.pre.data.compareTo(data) > 0) {
				n.data = n.pre.data;
				n = n.pre;
			}
			n.data = data;
			// 每次position位置的data完成正确的插入后，position向后移一位，直到完成从第二到最后的遍历
			position = position.next;
		}

	}

	public static <T extends Comparable<T>> void bubbleSort(BiLinkedList<T> bl) {
		if (bl.size() < 2)
			return;
		Node<T> head = new Node<T>(null, null, bl.first);
		bl.first.pre = head;
		Node<T> rear = new Node<T>(bl.last, null, null);
		bl.last.next = rear;
		Node<T> position = rear;
		Node<T> n;
		// 这里使用position.pre作为游标，因为该值有可能变动位置，如果只使用position，可能会因为交换位置而改动游标应处的位置
		// position作为外部游标，从rear到第二个结点截止，相当于序列中count从length-1到第一个位置
		while (position.pre != head.next) {
			n = head;
			boolean mod = false;
			// n作为内部游标，同样不能直接指向会换位的结点，所以比较的是n的后两个结点，使得结点互换完毕后n保持位置不动
			// n从头向后遍历，直到n的下一个结点是position的前一个结点，此时n.next.next是position，不予比较
			while (n.next != position.pre) {
				if (n.next.data.compareTo(n.next.next.data) > 0) {
					n.next = n.next.next;
					n.next.pre.next = n.next.next;
					n.next.next.pre = n.next.pre;
					n.next.pre.pre = n.next;
					n.next.next = n.next.pre;
					n.next.pre = n;
					mod = true;
				}
				n = n.next;

			}
			System.out.println("compare");
			if (mod == false)
				break;
			position = position.pre;
		}

		bl.first = head.next;
		bl.last = rear.pre;
		bl.first.pre = bl.last;
		bl.last.next = bl.first;

		head = null;
		rear = null;
		position = null;
		n = null;
	}

	public static <T extends Comparable<T>> void quickSort(BiLinkedList<T> bl) {
		if (bl.size() < 2)
			return;

		Node<T> head = new Node<T>(null, null, bl.first);
		bl.first.pre = head;
		Node<T> rear = new Node<T>(bl.last, null, null);
		bl.last.next = rear;

		quickSort(bl.first, bl.last);

		bl.first = head.next;
		bl.last = rear.pre;
		bl.first.pre = bl.last;
		bl.last.next = bl.first;

		head = null;
		rear = null;
	}

	private static <T extends Comparable<T>> void quickSort(Node<T> start,
			Node<T> end) {
		if (start == end || start == null || end == null) {
			return;
		}
		final Node<T> head = start.pre;
		final Node<T> rear = end.next;
		/*
		 * 将start结点作为分区元素，end作为游标，从后向前遍历， 但是先将end前移再比较end的后一个结点，这样当end后一个结点需要改变
		 * 连接位置时，不影响end的游标位置。当比较结点小于等于分区元素时， 将该结点插入到head后第一个位置，如果大于分区元素，保持位置
		 */
		while (end != start) {
			end = end.pre;

			if (end.next.data.compareTo(start.data) <= 0) {
				start.pre.next = end.next;
				end.next = end.next.next;
				start.pre.next.next = start;
				end.next.pre = end;
				start.pre.next.pre = start.pre;
				start.pre = start.pre.next;
			}

		}
		/*
		 * 此时start作为分界线，左边的结点都是小于等于他自身的，右边结点都是大于他自身。
		 * 如果start此时是第一个结点或最后一个结点，停止递归调用
		 */
		if (start.pre != head)
			quickSort(head.next, start.pre);
		if (start.next != rear)
			quickSort(start.next, rear.pre);

	}

	private static class Node<T> {
		private T data;
		private Node<T> pre;
		private Node<T> next;
		private int freq;

		public Node(Node<T> p, T d, Node<T> n) {
			this.pre = p;
			this.data = d;
			this.next = n;
		}
	}

}
