package tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public BinarySearchTree() {
		super();
	}

	private BinarySearchTree(Node<T> n) {
		this.root = n;
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
				if (node.left != null)
					node.left = insert(node.left, data);
				else
					node.left = new Node<T>(null, null, data);
			} else if (data.compareTo(node.item) >= 0) {
				if (node.right != null)
					node.right = insert(node.right, data);
				else
					node.right = new Node<T>(null, null, data);
			}
		}
		return node;
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

	private Node<T> find(Node<T> n, T element) {

		Node<T> temp = null;

		if (element.compareTo(n.item) < 0) {
			temp = find(n.left, element);
			if (temp.item.equals(element))
				return temp;

		} else if (element.compareTo(n.item) > 0) {
			temp = find(n.right, element);
			if (temp.item.equals(element))
				return temp;
		}

		return n;
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
		BinarySearchTree<T> rChild = new BinarySearchTree<T>(root.right);
		rChild.rightSpin();
		root.right = rChild.root;
		rChild = null;

		this.leftSpin();

	}
	
	public void leftrightSpin() {
		BinarySearchTree<T> lChild = new BinarySearchTree<T>(root.left);
		lChild.leftSpin();
		root.left = lChild.root;
		lChild = null;
		
		this.rightSpin();
	}
}
