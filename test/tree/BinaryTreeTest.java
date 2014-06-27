package tree;

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
	public void BinarySearchTreeTest() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.add(new Integer(50));
		bst.add(new Integer(30));
		bst.add(new Integer(20));
		bst.add(new Integer(35));
		bst.add(new Integer(41));
		bst.add(new Integer(5));
		bst.add(new Integer(18));
		bst.add(new Integer(80));
		bst.add(new Integer(70));
		bst.add(new Integer(500));
		bst.add(new Integer(52));
		bst.add(new Integer(59));
		bst.add(new Integer(300));
		bst.add(new Integer(51));
		bst.add(new Integer(33));
		bst.add(new Integer(23));
		bst.add(new Integer(39));
		bst.add(new Integer(48));
		bst.add(new Integer(15));
		bst.add(new Integer(182));
		bst.add(new Integer(81));
		bst.add(new Integer(73));
		bst.add(new Integer(510));
		bst.add(new Integer(58));
		bst.add(new Integer(57));
		bst.add(new Integer(302));
		
		
		
		
		out.println(bst);
		bst.remove(new Integer(41));
		out.println(bst);

		
		
	}

}
