package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> {
	private static class Node<T extends Comparable<T>> {
		private Node<T> left;
		private Node<T> right;
		private T item;
		private boolean color;
		private Node<T> parent;
		private static final boolean BLACK = true;
		private static final boolean RED = false;

		Node(Node<T> left, Node<T> right, T item, Node<T> parent) {
			this.left = left;
			this.right = right;
			this.item = item;
			color = RED;
			this.parent = parent;
		}

		Node(T item) {
			this.item = item;
			color = RED;

		}

		Node(Node<T> left, Node<T> right, T item, Node<T> parent, boolean color) {
			this.left = left;
			this.right = right;
			this.item = item;
			this.color = color;
			this.parent = parent;
		}

		public String toString() {
			return item.toString() + "[" + (color ? "B" : "R") + "]";
		}

		/*
		 * public void setLh(int lh) { this.lh = lh; updateBF(); }
		 * 
		 * public void setRh(int rh) { this.rh = rh; updateBF(); }
		 */

		private boolean isRed() {
			return color == RED;
		}

		private Node<T> getSibling() {
			if (isLeft())
				return parent.right;
			else
				return parent.left;
		}

		private boolean isLeft() {
			return (item.compareTo(parent.item) < 0);
		}

		private boolean getLChildColor() {
			return (left == null) ? BLACK : left.color;
		}

		private boolean getRChildColor() {
			return (right == null) ? BLACK : right.color;
		}

	};

	private Node<T> root;

	private int size;

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
		// System.out.println("add " + element);
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
			root.color = Node.BLACK;

		} else {
			if (data.compareTo(node.item) < 0) {
				if (node.left != null) {
					insert(node.left, data);
				} else {
					node.left = new Node<T>(null, null, data, node);
					// the new node's parent is red, the RB tree need to be
					// rebalance
					if (node.isRed()) {
						rebalanceAfterInsert(node.left);
					}

				}
			} else if (data.compareTo(node.item) >= 0) {
				if (node.right != null) {
					insert(node.right, data);

				} else {
					node.right = new Node<T>(null, null, data, node);
					// the new node's parent is red, the RB tree need to be
					// rebalance

					if (node.isRed()) {
						rebalanceAfterInsert(node.right);
					}
				}
			}
		}

		return node;
	}

	private void rebalanceAfterInsert(Node<T> node) {
		Node<T> current = node;
		Node<T> sibling = null;

		while (current != root && current.parent.isRed()) {
			sibling = current.parent.getSibling();
			if (sibling == null) {
				sibling = new Node<T>(null, null, null, null, Node.BLACK);
			}
			if (sibling != null && sibling.isRed()) {
				// parentrightsibling or parentleftsibling is red
				current.parent.color = Node.BLACK;
				sibling.color = Node.BLACK;
				current.parent.parent.color = Node.RED;
				current = node.parent.parent;
			} else {
				if (current.parent.isLeft()) {
					// current'parent is the left child of grandfather
					// parentleftsibling is black
					if (current.isLeft()) {
						current.parent.color = Node.BLACK;
						current.parent.parent.color = Node.RED;
						if (current.parent.parent != null) {
							rightSpin(current.parent.parent);

						}
					} else {
						current = current.parent;
						leftSpin(current);

					}

				} else {
					// current'parent is the right child of grandfather
					// parentrightsibling is black
					if (current.isLeft()) {
						current = current.parent;
						rightSpin(current);
					} else {
						current.parent.color = Node.BLACK;
						current.parent.parent.color = Node.RED;
						if (current.parent.parent != null) {
							leftSpin(current.parent.parent);
						}
					}
				}
			}
		}
		root.color = Node.BLACK;
		sibling = null;
		current = null;
	}

	public void remove(T element) {
		remove(element, root);
		size--;
	}

	private Node<T> remove(T t, Node<T> node) {
		if (node == null)
			return node;// 没有找到,doNothing
		int result = t.compareTo(node.item);
		if (result > 0) {
			remove(t, node.right);
		} else if (result < 0) {
			remove(t, node.left);
		} else {
			if (node.left != null && node.right != null) {
				node.item = findMin(node.right).item;
				remove(node.item, node.right);
			} else {
				//set the current node to be used later for rebalance start point
				Node<T> current = (node.left != null) ? node.left
						: ((node.right != null) ? node.right : node);
				if (node.left == null & node.right == null) {
					if (node.parent == null) {
						root = null;
					} else {
						rebalanceBeforeDel(node);

						if (node.isLeft()) {
							node.parent.left = null;
						} else {
							node.parent.right = null;
						}
					}
				} else {
					current.parent = node.parent;

					if (node.left != null) {
						if (node.parent == null)
							root = node.left;
						else {

							if (node.isLeft()) {
								node.parent.left = node.left;
							} else {
								node.parent.right = node.left;
							}
						}
					} else {
						if (node.parent == null)
							root = node.right;
						else {
							if (node.isLeft()) {
								node.parent.left = node.right;
							} else {
								node.parent.right = node.right;
							}
						}
					}

					rebalanceBeforeDel(current);
				}
			}
		}
		return node;
	}

	private void rebalanceBeforeDel(Node<T> current) {
		Node<T> sibling = null;
		while (current != null && current != root && !current.isRed()) {
			sibling = current.getSibling();
			if (sibling == null) {
				sibling = new Node<T>(null, null, null, null, Node.BLACK);
			}

			if (current.isLeft()) {
				if (sibling.isRed()) {
					sibling.color = Node.BLACK;
					current.parent.color = Node.RED;
					leftSpin(current.parent);
					sibling = current.getSibling();
					if (sibling == null) {
						sibling = new Node<T>(null, null, null, null,
								Node.BLACK);
					}
				}

				if ((sibling.getLChildColor() == Node.BLACK && sibling
						.getRChildColor() == Node.BLACK)) {
					sibling.color = Node.RED;
					current = current.parent;
				} else {
					if (sibling.getRChildColor() == Node.BLACK) {
						sibling.left.color = Node.BLACK;
						sibling.color = Node.RED;
						rightSpin(sibling);
						sibling = current.getSibling();
					}
					sibling.color = current.parent.color;
					current.parent.color = Node.BLACK;
					sibling.right.color = Node.BLACK;
					leftSpin(sibling.parent);
					current = root;

				}
			} else {
				if (sibling.isRed()) {
					sibling.color = Node.BLACK;
					current.parent.color = Node.RED;
					rightSpin(current.parent);
					sibling = current.getSibling();
					if (sibling == null) {
						sibling = new Node<T>(null, null, null, null,
								Node.BLACK);
					}
				}

				if (sibling.getLChildColor() == Node.BLACK
						&& sibling.getRChildColor() == Node.BLACK) {
					sibling.color = Node.RED;
					current = current.parent;
				} else {
					if (sibling.getLChildColor() == Node.BLACK) {

						sibling.right.color = Node.BLACK;

						sibling.color = Node.RED;
						leftSpin(sibling);
						sibling = current.parent.left;
					}
					sibling.color = current.parent.color;
					current.parent.color = Node.BLACK;
					sibling.left.color = Node.BLACK;
					rightSpin(sibling.parent);
					current = root;
				}
			}
		}
		current.color = Node.BLACK;
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

	private void leftSpin(Node<T> current) {
		Node<T> parentTemp = current.parent;
		RedBlackTree<T> subTree = new RedBlackTree<T>(current);
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
		parentTemp = null;
		subTree = null;
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

	private void rightSpin(Node<T> current) {
		Node<T> parentTemp = current.parent;
		RedBlackTree<T> subTree = new RedBlackTree<T>(current);
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
		parentTemp = null;
		subTree = null;
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
