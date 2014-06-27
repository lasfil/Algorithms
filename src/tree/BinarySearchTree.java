package tree;

import tree.BinarySearchTreeExp.BinaryNode;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public void add(T element) {
		if (element == null)
			return;
		Node<T> n = new Node<T>(null, null, element);

		if (this.root == null) {
			this.root = n;
		} else {
			addToNode(this.root, n);
		}

		size++;
	}

	private void addToNode(Node<T> node, Node<T> newNode) {
		if (newNode.item.compareTo(node.item) < 0) {
			if (node.left == null) {
				node.left = newNode;
			} else {
				addToNode(node.left, newNode);
			}
		} else {
			if (node.right == null) {
				node.right = newNode;
			} else {
				addToNode(node.right, newNode);
			}
		}
	}

	public void remove(T element) {
		root = remove(element, root);
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

	/** 查询出最小元素所在的结点 */
	private Node<T> findMin(Node<T> node) {
		if (node == null)
			return null;
		else if (node.left == null)
			return node;
		return findMin(node.left);// 递归查找
	}
}
