package tree;

import org.junit.Test;

import tree.BinaryTree.Side;

public class BinaryTreeTest {

	@Test
	public void testAdd() {
		BinaryTree<String> bt = new BinaryTree<String>("-");
		bt.add(0, 1, "+", Side.LEFT);
		bt.add(1, 2, "a", Side.LEFT);
		bt.add(1, 3, "*", Side.RIGHT);
		bt.add(3, 4, "b", Side.LEFT);
		bt.add(3, 5, "-", Side.RIGHT);
		bt.add(5, 6, "c", Side.LEFT);
		bt.add(5, 7, "d", Side.RIGHT);
		bt.add(0, 8, "/", Side.RIGHT);
		bt.add(8, 9, "e", Side.LEFT);
		bt.add(8, 10, "f", Side.RIGHT);
		return;

	}

	@Test
	public void testconstructor() {
		BinaryTree<String> left = new BinaryTree<String>("c", 6);
		BinaryTree<String> right = new BinaryTree<String>("d", 7);
		right = new BinaryTree<String>("-", 5, left, right);
		left = new BinaryTree<String>("b", 4);
		right = new BinaryTree<String>("*", 3, left, right);
		left = new BinaryTree<String>("a", 2);
		left = new BinaryTree<String>("+", 1, left, right);
		right = new BinaryTree<String>("/", 8, new BinaryTree<String>("e", 9),
				new BinaryTree<String>("f", 10));

		BinaryTree<String> tree = new BinaryTree<String>("-", 0, left, right);
		left = null;
		right = null;
		System.out.println(tree.size());
		tree.preTraverse();
		System.out.println();

		tree.inTraverse();
		System.out.println();

		tree.postTraverse();

	}

}
