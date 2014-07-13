package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

	protected static class Node<T> {
		protected Node<T> left;
		protected Node<T> right;
		protected T item;
		
		Node() {
			super();
		}

		Node(Node<T> left, Node<T> right, T item) {
			this.left = left;
			this.right = right;
			this.item = item;
		}

		Node(T item) {
			this.item = item;
		}

		public String toString() {
			return item.toString();
		}

	};

	protected Node<T> root;

	protected int size;

	public BinaryTree() {
		super();
	}

	/*
	 * public void add(int pid, int id, T item, Side s) {
	 * 
	 * Node<T> pnode = find(root, pid); if (pnode != null && pid == pnode.id) {
	 * switch (s) { case LEFT: pnode.left = new Node<T>(id, null, null, item);
	 * break; case RIGHT: pnode.right = new Node<T>(id, null, null, item);
	 * 
	 * } size++; } else { System.out.println("Wrong parent id: " + pid +
	 * " to insert"); } }
	 */

	public BinaryTree(T item) {
		root = new Node<T>(null, null, item);
		size++;
	}

	public BinaryTree(T item, BinaryTree<T> left, BinaryTree<T> right) {
		this.root = new Node<T>(item);
		if (left != null)
			root.left = left.root;
		if (right != null)
			root.right = right.root;
		size = 1 + left.size() + right.size();
	}

	/*
	 * private Node<T> find(Node<T> n, int pid) {
	 * 
	 * Node<T> temp = null;
	 * 
	 * if (n != null && n.id != pid) { if (n.left != null) { temp = find(n.left,
	 * pid); if (temp.id == pid) return temp; } if (n.right != null) { temp =
	 * find(n.right, pid); if (temp.id == pid) return temp; } }
	 * 
	 * return n; }
	 */

	public void inTraverse() {
		inTraverse(root, null);
		System.out.println();
	}

	private void inTraverse(Node<T> n, List<T> l) {
		if (n != null) {
			if (n.left != null) {
				inTraverse(n.left, l);
			}

			if (l == null)
				System.out.print(n.item + " ");
			else
				l.add(n.item);

			if (n.right != null) {
				inTraverse(n.right, l);
			}
		}
	}

	public void postTraverse() {
		postTraverse(root, null);
		System.out.println();

	}

	private void postTraverse(Node<T> n, List<T> l) {
		if (n != null) {
			if (n.left != null) {
				postTraverse(n.left, l);
			}

			if (n.right != null) {
				postTraverse(n.right, l);
			}

			if (l == null)
				System.out.print(n.item + " ");
			else
				l.add(n.item);

		}
	}

	public void preTraverse() {
		preTraverse(root, null);
		System.out.println();

	}

	private void preTraverse(Node<T> n, List<T> l) {
		if (n != null) {
			if (l == null)
				System.out.print(n.item + " ");
			else
				l.add(n.item);
			if (n.left != null)
				preTraverse(n.left, l);
			if (n.right != null)
				preTraverse(n.right, l);

		}
	}

	public int size() {
		return size;
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

			sb.append(prefix + n.item.toString());
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

	public Iterator<T> inOrderIterator() {
		List<T> l = new ArrayList<T>();
		this.inTraverse(root, l);

		return l.iterator();
	}
}
