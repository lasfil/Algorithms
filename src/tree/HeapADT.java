package tree;


public class HeapADT<T extends Comparable<T>> {
	private static class Node<T extends Comparable<T>> {
		private Node<T> left;
		private Node<T> right;
		private T item;
		private Node<T> parent;
		private int size;

		Node(Node<T> left, Node<T> right, T item, Node<T> parent) {
			this.left = left;
			this.right = right;
			this.item = item;
			this.parent = parent;
			this.size = 1;
		}

		Node(T item) {
			this.item = item;
			this.size = 1;

		}

		public String toString() {
			return item.toString() + "[" + size + "]";
		}

		@SuppressWarnings("unused")
		private Node<T> getSibling() {
			if (isLeft())
				return parent.right;
			else
				return parent.left;
		}

		private boolean isLeft() {
			return (item.compareTo(parent.item) < 0);
		}

		public void updateSize() {
			int leftSize = left == null ? 0 : left.size;
			int rightSize = right == null ? 0 : right.size;
			size = 1 + leftSize + rightSize;
		}

		private int getLeftSize() {
			return left == null ? 0 : left.size;
		}

		private int getRightSize() {
			return right == null ? 0 : right.size;
		}

	}

	private Node<T> root;

	private int size;

	public HeapADT() {
		super();
	}

	public HeapADT(T item) {
		root = new Node<T>(null, null, item, null);
		size++;
	}

	public HeapADT(T item, HeapADT<T> left, HeapADT<T> right) {
		this.root = new Node<T>(item);
		if (left != null)
			root.left = left.root;
		if (right != null)
			root.right = right.root;
		size = 1 + left.size() + right.size();
	}

	public HeapADT(Node<T> n) {

		this.root = n;
	}

	public int size() {
		if (root != null)
			return root.size;
		else
			return 0;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ]");

		stringAppend(sb, root, "");

		String result = sb.toString();
		sb.setLength(0);
		return result;
	}

	private void stringAppend(StringBuffer sb, Node<T> n, String prefix) {

		if (n != null) {
			sb.append(prefix + n.toString());
			sb.append("\n");

			if (n.left != null) {
				sb.append("[l]");
				stringAppend(sb, n.left, prefix + "   ");
			}

			if (n.right != null) {
				sb.append("[r]");

				stringAppend(sb, n.right, prefix + "   ");
			}
		}
	}

	public void add(T element) {
		if (element == null)
			return;
		root = insert(root, element);
		/*
		 * Node<T> n = new Node<T>(null, null, element);
		 * 
		 * if (this.root == null) { this.root = n; } else { addToNode(this.root,
		 * n); }
		 */

		size = root.size;

	}

	private Node<T> insert(Node<T> node, T data) {
		if (node == null) {
			node = new Node<T>(null, null, data, node);
		} else {
			int ls = node.getLeftSize();
			int rs = node.getRightSize();
			// left size
			if (ls == rs || (ls & (ls + 1)) != 0) {
				node.left = insert(node.left, data);
				if (node.item.compareTo(node.left.item) >= 0) {
					T temp = node.item;
					node.item = node.left.item;
					node.left.item = temp;
				}
			} else {
				node.right = insert(node.right, data);
				if (node.item.compareTo(node.right.item) >= 0) {
					T temp = node.item;
					node.item = node.right.item;
					node.right.item = temp;
				}
			}

		}

		node.updateSize();

		return node;
	}

	public T findMin() {
		return root.item;
	}

	public T removeMin() {
		T remove = root.item;
		Node<T> last = removeLast(root);
		if (last != null) {
			root.item = last.item;
			heapfi(root);
		} else
			root = null;

		return remove;
	}

	private void heapfi(Node<T> node) {
		int cmpNL, cmpNR, cmpLR;
		if (node == null || (node.left == null && node.right == null)) {
			return;
		} else if (node.left == null) {
			cmpNL = -1;
			cmpLR = 1;
			cmpNR = node.item.compareTo(node.right.item);

		} else if (node.right == null) {
			cmpNR = -1;
			cmpLR = -1;
			cmpNL = node.item.compareTo(node.left.item);

		} else {
			cmpNL = node.item.compareTo(node.left.item);
			cmpNR = node.item.compareTo(node.right.item);
			cmpLR = node.left.item.compareTo(node.right.item);

		}

		T temp = null;
		if (cmpNL == 1 && cmpNR == 1) {
			if (cmpLR <= 0) {
				temp = node.left.item;
				node.left.item = node.item;
				node.item = temp;
				heapfi(node.left);
			} else {
				temp = node.right.item;
				node.right.item = node.item;
				node.item = temp;
				heapfi(node.right);
			}
		} else if (cmpNL == 1) {
			temp = node.left.item;
			node.left.item = node.item;
			node.item = temp;
			heapfi(node.left);
		} else if (cmpNR == 1) {
			temp = node.right.item;
			node.right.item = node.item;
			node.item = temp;
			heapfi(node.right);
		}

	}

	private Node<T> removeLast(Node<T> node) {
		Node<T> last = null;
		if (node == null || (node.left == null && node.right == null))
			return last;
		int ls = node.getLeftSize();
		int rs = node.getRightSize();
		if (rs == 1 && ls == 1) {
			last = node.right;
			node.right = null;
			// node.updateSize();
		} else if (rs == 0 && ls == 1) {
			last = node.left;
			node.left = null;
			// node.updateSize();
		} else if (ls == rs || (rs & (rs + 1)) != 0) {
			last = removeLast(node.right);
		} else {
			last = removeLast(node.left);
		}

		node.updateSize();

		return last;
	}

	public T removeLast() {
		return removeLast(root).item;
	}
}
