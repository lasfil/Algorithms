package tree;

public class BinaryTree<T extends Comparable> {

	public enum Side {
		LEFT, RIGHT
	};

	private Node<T> root;
	private int size;

	public BinaryTree(T item) {
		root = new Node<T>(0, null, null, item);
		size++;
	}

	public BinaryTree(T item, int id) {
		root = new Node<T>(id, null, null, item);
		size++;
	}

	public int size() {
		return size;
	}

	public void add(int pid, int id, T item, Side s) {

		Node<T> pnode = find(root, pid);
		if (pnode != null && pid == pnode.id) {
			switch (s) {
			case LEFT:
				pnode.left = new Node<T>(id, null, null, item);
				break;
			case RIGHT:
				pnode.right = new Node<T>(id, null, null, item);

			}
			size++;
		} else {
			System.out.println("Wrong parent id: " + pid + " to insert");
		}
	}

	public BinaryTree() {
		super();
	}

	public BinaryTree(T item, int id, BinaryTree<T> left, BinaryTree<T> right) {
		this.root = new Node<T>(item, id);
		if (left != null)
			root.left = left.root;
		if (right != null)
			root.right = right.root;
		size = 1 + left.size() + right.size();
	}

	private Node<T> find(Node<T> n, int pid) {

		Node<T> temp = null;

		if (n != null && n.id != pid) {
			if (n.left != null) {
				temp = find(n.left, pid);
				if (temp.id == pid)
					return temp;
			}
			if (n.right != null) {
				temp = find(n.right, pid);
				if (temp.id == pid)
					return temp;
			}
		}

		return n;
	}

	public void preTraverse() {
		preTraverse(root);
	}

	public void preTraverse(BinaryTree<T> tree) {
		if (tree != null) {
			preTraverse(tree.root);
		}
	}

	public void preTraverse(Node<T> n) {
		if (n != null) {
			System.out.print(n.item);
			if (n.left != null)
				preTraverse(n.left);
			if (n.right != null)
				preTraverse(n.right);

		}
	}

	public void inTraverse() {
		inTraverse(root);
	}

	public void inTraverse(BinaryTree<T> tree) {
		if (tree != null) {
			inTraverse(tree.root);
		}
	}

	private void inTraverse(Node<T> n) {
		if (n != null) {
			if (n.left != null) {
				inTraverse(n.left);
			}

			System.out.print(n.item);

			if (n.right != null) {
				inTraverse(n.right);
			}
		}
	}
	
	public void postTraverse() {
		postTraverse(root);
	}

	public void postTraverse(BinaryTree<T> tree) {
		if (tree != null) {
			postTraverse(tree.root);
		}
	}

	private void postTraverse(Node<T> n) {
		if (n != null) {
			if (n.left != null) {
				postTraverse(n.left);
			}
			
			if (n.right != null) {
				postTraverse(n.right);
			}
			
			System.out.print(n.item);

		}
	}

	private static class Node<T> {
		private Node<T> left;
		private Node<T> right;
		private int id;
		private T item;

		Node(int id, Node<T> left, Node<T> right, T item) {
			this.left = left;
			this.right = right;
			this.id = id;
			this.item = item;
		}

		Node(T item) {
			this.item = item;
		}

		Node(T item, int id) {
			this.item = item;
			this.id = id;
		}
	}
}
