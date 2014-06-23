package list;

public class MyLinkedList<T extends Comparable<T>> {

	class Node {
		private T data;
		private Node next;
		int index;

		Node(T data) {
			this.data = data;
		}

		Node(T data, int index) {
			this.data = data;
			this.index = index;
		}

		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node first;

	private Node last;

	private boolean empty = true;

	private int size;

	public int add(T t) {
		int index = 0;
		if (isEmpty()) {
			first = new Node(t, 0);
			empty = false;
			last = first;
		} else {
			Node n = first;

			while (null != n.next) {
				index++;

				n = n.next;
			}

			n.next = new Node(t, ++index);
			last = n.next;

		}

		++size;

		return index;
	}

	public void add(T t, int i) {

		if (isEmpty() || i > size) {
			if (i == 0) {
				first = new Node(t, 0);
				empty = false;
				++size;
			} else
				System.out.println("Wrong index number");
			return;
		}

		int index = 0;
		Node n = first;
		Node newN = new Node(t, i);

		// MyNode n1 = n.next;
		if (i == 0) {
			first = newN;
			first.next = n;
		} else {
			while (n.index != i - 1) {
				n = n.next;
			}

			newN.next = n.next;
			n.next = newN;

		}
		n = newN;
		index = newN.index;
		if (index == size) {
			last = newN;
		}
		++size;
		while (++index < size) {
			n = n.next;

			n.index++;

		}

	}

	public void add1(T t, int i) {

		if (isEmpty() || i > size) {
			if (i == 0) {
				first = new Node(t, 0);
				empty = false;
				++size;
			} else
				System.out.println("Wrong index number");
			return;
		}

		int index = 0;
		Node n = first;
		Node nPre = first;

		index = nPre.index;
		while (n.index != i) {
			if (n.index > 0)
				nPre = nPre.next;
			n = nPre.next;
		}
		if (i == 0) {
			first = new Node(t, 0);
			first.next = n;
		} else {
			nPre.next = new Node(t, i);
			nPre = nPre.next;
			nPre.next = n;
		}
		index = nPre.index;

		++size;
		while (++index < size) {
			n.index++;

			n = n.next;
		}

	}

	public void delete(T t) {
		Node n = first;

		if (first.data.equals(t)) {
			first = first.next;
			size--;
			for (int i = 1; i <= size; i++) {
				n = n.next;
				n.index--;
			}
			return;
		}

		int index = 1;
		Node nPre = first;

		while (index < size) {
			if (n.data.equals(t)) {
				if (null != n.next) {
					nPre.next = n.next;
					n = nPre.next;
					for (int i = n.index; i < size; i++) {
						n.index--;
						if (n.next != null)
							n = n.next;
					}
				} else {
					nPre.next = null;
				}
				size--;
				break;
			} else {
				if (n.index > 0)
					nPre = nPre.next;
				n = nPre.next;
			}
			index++;
		}

	}

	public T get(int i) {
		if (empty || i >= size) {
			return null;
		}

		Node n = first;

		while (null != n.next) {
			if (n.index == i)
				break;
			n = n.next;
		}

		return n.data;
	}

	public T getFirst() {
		if (empty) {
			return null;
		} else {
			return first.data;
		}
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		if (null != first)
			empty = false;
		return empty;
	}

	public void printList() {
		if (empty) {
			System.out.println("This list is empty");
			return;
		}

		Node n = first;

		while (null != n.next) {
			System.out.println(n.index + ": " + n.data);
			n = n.next;
		}

		System.out.println(n.index + ": " + n.data);

	}

	public void mergeInOrder(MyLinkedList<T> l2) {
		Node n2 = null;
		Node n = new Node(null, first);
		while (l2.size > 0) {
			// n.next = root;
			n2 = l2.first;
			l2.first = l2.first.next;
			n2.next = null;
			orderInsert(n, n2);
			size++;

			l2.size--;

		}

		n = null;
		n = first;
		first.index = 0;
		while (n.next != null) {
			n.next.index = n.index + 1;
			n = n.next;
		}
	}

	public void reverse() {
		final Node l = last;
		final Node f = first;
		Node n = new Node(null, first);
		Node n1 = new Node(null, n.next.next);
		Node n2 = new Node(null, n1.next.next);

		while (n1.next != null) {
			if (n1.next != null)
				n1.next.next = n.next;
			n.next = n1.next;
			n1.next = n2.next;
			if (n2.next != null)
				n2.next = n2.next.next;
		}
		last = f;
		first = l;
		last.next = null;
	}

	public void reverse1() {
		Node n = first;
		Node n1 = null;
		last = n;

		while (n != null) {
			first = first.next;
			n.next = n1;
			n1 = n;
			n = first;
		}

		first = n1;
	}

	public void sort() {

		Node tmpRoot = first.next;
		first.next = null;
		Node n = new Node(null);
		Node nf = first;

		while (tmpRoot != null) {
			n = tmpRoot;
			tmpRoot = tmpRoot.next;
			n.next = null;

			orderInsert(nf, n);
		}

	}

	private void orderInsert(Node root, Node n2) {
		Node n = root;
		while (n.next != null && n2.data.compareTo(n.next.data) > 0) {
			n = n.next;
		}

		if (n.next == null) {
			n.next = n2;
		} else {
			n2.next = n.next;
			n.next = n2;
		}

		if (n.data == null) {
			root = n.next;
		}

	}
}
