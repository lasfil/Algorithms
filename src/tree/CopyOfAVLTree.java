package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyOfAVLTree<T extends Comparable<T>> {
	private static class Node<T> {
		private Node<T> left;
		private Node<T> right;
		private T item;
		private int bf;
		private int lh;
		private int rh;

		Node(Node<T> left, Node<T> right, T item) {
			this.left = left;
			this.right = right;
			this.item = item;
			bf = 0;
			lh = 0;
			rh = 0;
		}

		Node(T item) {
			this.item = item;
			bf = 0;
			lh = 0;
			rh = 0;

		}

		public String toString() {
			return item.toString() + "[" + bf + "(" + lh + "," + rh + ")" + "]";
		}

		

		public void setLh(int lh) {
			this.lh = lh;
			updateBF();
		}

		
		public void setRh(int rh) {
			this.rh = rh;
			updateBF();
		}

		void updateBF() {
			bf = rh - lh;
		}

	};

	private Node<T> root;

	private int size;

	public CopyOfAVLTree() {
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

	public CopyOfAVLTree(T item) {
		root = new Node<T>(null, null, item);
		size++;
	}

	public CopyOfAVLTree(T item, CopyOfAVLTree<T> left, CopyOfAVLTree<T> right) {
		this.root = new Node<T>(item);
		if (left != null)
			root.left = left.root;
		if (right != null)
			root.right = right.root;
		size = 1 + left.size() + right.size();
	}

	public CopyOfAVLTree(Node<T> n) {

		this.root = n;
	}

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

	public Iterator<T> inOrderIterator() {
		List<T> l = new ArrayList<T>();
		this.inTraverse(root, l);

		return l.iterator();
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

		size++;
	}

	private Node<T> insert(Node<T> node, T data) {
		if (node == null) {
			node = new Node<T>(null, null, data);
		} else {
			if (data.compareTo(node.item) < 0) {
				if (node.left != null) {
					node.left = insert(node.left, data);
					node.setLh(Math.max(node.left.lh, node.left.rh) + 1);
					//if ( node.b)

				} else {
					node.left = new Node<T>(null, null, data);
					if (node.right == null) {
						node.setLh(node.lh + 1);
					}

				}
			} else if (data.compareTo(node.item) >= 0) {
				if (node.right != null) {
					node.right = insert(node.right, data);
					node.setRh(Math.max(node.right.lh, node.right.rh) + 1);

				} else {
					node.right = new Node<T>(null, null, data);
					if (node.left == null) {
						node.setRh(node.rh + 1);
					}
				}
			}
		}
		return node;
	}

	public void remove(T element) {
		root = remove(element, root);
		size--;
	}

	private Node<T> remove(T t, Node<T> node) {
		if (node == null)
			return node;// 没有找到,doNothing
		int result = t.compareTo(node.item);
		if (result > 0)
			node.right = remove(t, node.right);
		else if (result < 0)
			node.left = remove(t, node.left);
		else {
			if (node.left != null && node.right != null) {
				node.item = findMin(node.right).item;
				node.right = remove(node.item, node.right);
			} else
				node = (node.left != null) ? node.left : node.right;
		}
		return node;

	}

	public T findMin() {
		return findMin(root).item;
	}

	private Node<T> findMax(Node<T> node) {
		if (node == null || node.right == null) {
			return node;
		} else {
			return findMax(node.right);
		}
	}

	/** 查询出最小元素所在的结点 */
	private Node<T> findMin(Node<T> node) {
		if (node == null)
			return null;
		else if (node.left == null)
			return node;
		return findMin(node.left);// 递归查找
	}

	public void leftSpin() {
		if (root == null) {
			return;
		}
		// create a head node, whose right point to root
		Node<T> head = new Node<T>(null, root, null);
		// point head's right to root's right, in order to make head's right a
		// new root later
		head.right = root.right;
		// point old root's right to new root's left, in order to point new
		// root's left to old root
		root.right = head.right.left;
		// point new root's left to old root
		head.right.left = root;
		// make head's right the new root
		root = head.right;

		head = null;
	}

	public void rightSpin() {
		if (root == null) {
			return;
		}
		// create a head node, whose right point to root
		Node<T> head = new Node<T>(null, root, null);
		// point head's right to root's right, in order to make head's right a
		// new root later
		head.right = root.left;
		// point old root's right to new root's left, in order to point new
		// root's left to old root
		root.left = head.right.right;
		// point new root's left to old root
		head.right.right = root;
		// make head's right the new root
		root = head.right;

		head = null;
	}

	public void rightleftSpin() {
		CopyOfAVLTree<T> rChild = new CopyOfAVLTree<T>(root.right);
		rChild.rightSpin();
		root.right = rChild.root;
		rChild = null;

		this.leftSpin();

	}

	public void leftrightSpin() {
		CopyOfAVLTree<T> lChild = new CopyOfAVLTree<T>(root.left);
		lChild.leftSpin();
		root.left = lChild.root;
		lChild = null;

		this.rightSpin();
	}

}
