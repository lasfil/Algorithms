package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> {
	private static class Node<T extends Comparable<T>> {
		private Node<T> left;
		private Node<T> right;
		private T item;
		private int color;
		private Node<T> parent;

		Node(Node<T> left, Node<T> right, T item, Node<T> parent) {
			this.left = left;
			this.right = right;
			this.item = item;
			color = 0;
			this.parent = parent;
		}

		Node(T item) {
			this.item = item;
			color = 0;

		}

		Node(Node<T> left, Node<T> right, T item, Node<T> parent, int color) {
			this.left = left;
			this.right = right;
			this.item = item;
			this.color = color;
			this.parent = parent;
		}

		public String toString() {
			return item.toString() + "[" + color + "]";
		}

		/*
		 * public void setLh(int lh) { this.lh = lh; updateBF(); }
		 * 
		 * public void setRh(int rh) { this.rh = rh; updateBF(); }
		 */

		private boolean isRed() {
			return color == 0;
		}

		private Node<T> getSibling() {
			if (parent.isLeft())
				return parent.parent.right;
			else
				return parent.parent.left;
		}

		private boolean isLeft() {
			return (item.compareTo(parent.item) < 0);
		}

	};

	private Node<T> root;

	private int size;

	private Node<T> rear = new Node<T>(null, null, null, null, 1);

	public RedBlackTree() {
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

	public RedBlackTree(T item) {
		root = new Node<T>(null, null, item, null);
		size++;
	}

	public RedBlackTree(T item, RedBlackTree<T> left, RedBlackTree<T> right) {
		this.root = new Node<T>(item);
		if (left != null)
			root.left = left.root;
		if (right != null)
			root.right = right.root;
		size = 1 + left.size() + right.size();
	}

	public RedBlackTree(Node<T> n) {

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
		System.out.println("add " + element);
		if (element == null)
			return;
		insert(root, element);

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
			root = new Node<T>(null, null, data, null);
			root.color = 1;

		} else {
			if (data.compareTo(node.item) < 0) {
				if (node.left != null) {
					insert(node.left, data);

				} else {
					node.left = new Node<T>(null, null, data, node);
					// the new node's parent is red, the RB tree need to be
					// rebalance
					if (node.isRed()) {
						rebalance(node.left);
					}

				}
			} else if (data.compareTo(node.item) >= 0) {
				if (node.right != null) {
					insert(node.right, data);
					// the height of right child has changed, get the new height
					// and plus one, then set to this node's right child tree
					// height
					/*
					 * if (Math.abs(node.bf) == 2) { node.right =
					 * rebalance(node.right); }
					 */
				} else {
					node.right = new Node<T>(null, null, data, node);
					// the new node's parent is red, the RB tree need to be
					// rebalance

					if (node.isRed()) {
						rebalance(node.right);
					}
				}
			}
		}
		
		return node;
	}

	private void rebalance(Node<T> node) {
		Node<T> current = node;
		Node<T> sibling = null;
		Node<T> parentTemp = null;
		RedBlackTree<T> subTree = null;

		while (current != root && current.parent.isRed()) {
			sibling = current.getSibling();
			if (sibling == null) {
				sibling = new Node<T>(null, null, null, null, 1);
			}
			if (sibling != null && sibling.isRed()) {
				// parentrightsibling or parentleftsibling is red
				current.parent.color = 1;
				sibling.color = 1;
				current.parent.parent.color = 0;
				current = node.parent.parent;
			} else {
				if (current.parent.isLeft()) {
					// current'parent is the left child of grandfather
					// parentleftsibling is black
					if (current.isLeft()) {
						current.parent.color = 1;
						current.parent.parent.color = 0;
						if (current.parent.parent != null) {
							parentTemp = current.parent.parent.parent;
							subTree = new RedBlackTree<T>(current.parent.parent);
							subTree.rightSpin();
							if (parentTemp == null) {
								root = subTree.root;
							} else {
								if (subTree.root.item
										.compareTo(parentTemp.item) < 0) {
									parentTemp.left = subTree.root;
								} else {
									parentTemp.right = subTree.root;
								}
								subTree.root.parent = parentTemp;
							}
						}
					} else {
						current = current.parent;
						parentTemp = current.parent;
						subTree = new RedBlackTree<T>(current);
						subTree.leftSpin();
						if (parentTemp == null) {
							root = subTree.root;
						} else {
							if (subTree.root.item.compareTo(parentTemp.item) < 0) {
								parentTemp.left = subTree.root;
							} else {
								parentTemp.right = subTree.root;
							}
							subTree.root.parent = parentTemp;
						}
					}

				} else {
					// current'parent is the right child of grandfather
					// parentrightsibling is black
					if (current.isLeft()) {
						current = current.parent;
						parentTemp = current.parent;
						subTree = new RedBlackTree<T>(current);
						subTree.rightSpin();
						if (parentTemp == null) {
							root = subTree.root;
						} else {
							if (subTree.root.item.compareTo(parentTemp.item) < 0) {
								parentTemp.left = subTree.root;
							} else {
								parentTemp.right = subTree.root;
							}
							subTree.root.parent = parentTemp;
						}

					} else {
						current.parent.color = 1;
						current.parent.parent.color = 0;
						if (current.parent.parent != null) {
							parentTemp = current.parent.parent.parent;
							subTree = new RedBlackTree<T>(current.parent.parent);
							subTree.leftSpin();
							if (parentTemp == null) {
								root = subTree.root;
							} else {
								if (subTree.root.item
										.compareTo(parentTemp.item) < 0) {
									parentTemp.left = subTree.root;
								} else {
									parentTemp.right = subTree.root;
								}
								subTree.root.parent = parentTemp;
							}
						}
					}
				}
			}
		}
		root.color = 1;
		sibling = null;
		parentTemp = null;
		current = null;
		subTree = null;
	}

	public void remove(T element) {
		root = remove(element, root);
		size--;
	}

	private Node<T> remove(T t, Node<T> node) {
		if (node == null)
			return node;// 没有找到,doNothing
		int result = t.compareTo(node.item);
		if (result > 0) {
			node.right = remove(t, node.right);
		} else if (result < 0) {
			node.left = remove(t, node.left);
		} else {
			if (node.left != null && node.right != null) {
				node.item = findMin(node.right).item;
				node.right = remove(node.item, node.right);
			} else {
				node = (node.left != null) ? node.left : node.right;
				if (node != null) {
				}
			}
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
		Node<T> head = new Node<T>(null, root, null, null);
		// point head's right to root's right, in order to make head's right a
		// new root later
		head.right = root.right;
		// point old root's right to new root's left, in order to point new
		// root's left to old root
		root.right = head.right.left;
		if (root.right != null)
			root.right.parent = root;
		// point new root's left to old root
		head.right.left = root;
		root.parent = head.right;
		// make head's right the new root
		root = head.right;
		root.parent = null;
		head = null;
	}

	public void rightSpin() {
		if (root == null) {
			return;
		}
		// create a head node, whose right point to root
		Node<T> head = new Node<T>(null, root, null, null);
		// point head's right to root's right, in order to make head's right a
		// new root later
		head.right = root.left;
		// point old root's right to new root's left, in order to point new
		// root's left to old root
		root.left = head.right.right;
		if (root.left != null)
			root.left.parent = root;
		// point new root's left to old root
		head.right.right = root;
		root.parent = head.right;

		// make head's right the new root
		root = head.right;
		root.parent = null;

		head = null;
	}

	public void rightleftSpin() {
		RedBlackTree<T> rChild = new RedBlackTree<T>(root.right);
		rChild.rightSpin();
		root.right = rChild.root;
		rChild = null;

		this.leftSpin();

	}

	public void leftrightSpin() {
		RedBlackTree<T> lChild = new RedBlackTree<T>(root.left);
		lChild.leftSpin();
		root.left = lChild.root;
		lChild = null;

		this.rightSpin();
	}

}
