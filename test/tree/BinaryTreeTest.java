package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static java.lang.System.*;

public class BinaryTreeTest {

	/*
	 * @Test public void testAdd() { BinaryTree<String> bt = new
	 * BinaryTree<String>("-"); bt.add(0, 1, "+", Side.LEFT); bt.add(1, 2, "a",
	 * Side.LEFT); bt.add(1, 3, "*", Side.RIGHT); bt.add(3, 4, "b", Side.LEFT);
	 * bt.add(3, 5, "-", Side.RIGHT); bt.add(5, 6, "c", Side.LEFT); bt.add(5, 7,
	 * "d", Side.RIGHT); bt.add(0, 8, "/", Side.RIGHT); bt.add(8, 9, "e",
	 * Side.LEFT); bt.add(8, 10, "f", Side.RIGHT); return;
	 * 
	 * }
	 */

	@Test
	public void testconstructor() {
		BinaryTree<String> left = new BinaryTree<String>("c");
		BinaryTree<String> right = new BinaryTree<String>("d");
		right = new BinaryTree<String>("-", left, right);
		left = new BinaryTree<String>("b");
		right = new BinaryTree<String>("*", left, right);
		left = new BinaryTree<String>("a");
		left = new BinaryTree<String>("+", left, right);
		right = new BinaryTree<String>("/", new BinaryTree<String>("e"),
				new BinaryTree<String>("f"));

		BinaryTree<String> tree = new BinaryTree<String>("-", left, right);
		left = null;
		right = null;
		assertEquals("Tree size is not correct", 11, tree.size());

		tree.preTraverse();
		System.out.println();

		tree.inTraverse();
		System.out.println();

		tree.postTraverse();
		System.out.println();

		out.println(tree);

	}

	@Test
	public void BinarySearchTreeSpinTest() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		// int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
		// 300 };
		int[] a = new int[] { 5, 8, 9, 13, 50, 300 };

		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		for (Integer inte : l)
			bst.add(inte);
		out.println(bst);

		bst.leftSpin();
		out.println(bst);
		bst.rightSpin();
		out.println(bst);
		// bst.leftspin();
		// out.println(bst);
	}

	@Test
	public void BinarySearchTreeRLSpinTest() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		// int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
		// 300 };
		int[] a = new int[] { 5, 3, 13, 10, 7, 15 };

		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		for (Integer inte : l)
			bst.add(inte);
		out.println(bst);

		// bst.rightleftSpin();
		// out.println(bst);
		// bst.rightspin();
		// out.println(bst);
		// bst.leftspin();
		// out.println(bst);
	}

	@Test
	public void BinarySearchTreeTest() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		// int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
		// 300 };
		int[] a = new int[] { 5, 8, 9, 13, 50, 300 };

		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		for (Integer inte : l)
			bst.add(inte);

		// out.println(bst);
		// bst.remove(new Integer(80));
		// out.println(bst);
		bst.preTraverse();
		bst.inTraverse();
		bst.postTraverse();

		Iterator<Integer> iter = bst.inOrderIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

	}

	@Test
	public void AVLTreeTestInsert() {
		AVLTree<Integer> avl = new AVLTree<Integer>();
		/*
		 * int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
		 * 300 }; Arrays.sort(a);
		 */
		int[] a = new int[] { 7, 5, 3, 9, 6, 1 };
		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		for (Integer inte : l)
			avl.add(inte);

		out.println(avl);

		avl.add(new Integer(48));
		avl.add(new Integer(47));
		out.println(avl);
	}

}
