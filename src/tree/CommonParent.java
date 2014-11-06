package tree;
import java.util.List;
import java.util.ArrayList;
//give a tree, find the common parent node of any two tree node
public class CommonParent {
	static class Node {
		Node left;
		Node right;
		int val;
		Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		Node(int val) {
			this.val = val;
		}
		
		public String toString() {
			return val + "";
		}
	}
	
	public Node getCommonParent(Node root, Node n1, Node n2) {
		if (root == null) {
			return null;
		}
		Node parent = root;
		if (n1 == null) {
			return n2;
		}
		if (n2 == null) {
			return n1;
		}
		
		
		List<Node> list = inOrderTraverse(root);
		
		
		int i1 = list.indexOf(n1);
		int i2 = list.indexOf(n2);
		int ir = list.indexOf(parent);
		if (i1 == -1 || i2 == -1) {
			return null;
		}
		if (i1 == i2) {
			return n1;
		}
		while ((ir - i1) * (i2 - ir) < 0) {
			if (i1 < ir) {
				parent = parent.left;
			} else if (i1 > ir) {
				parent = parent.right;
			}
			ir = list.indexOf(parent);
		}
		
		if (i1 == ir) {
			return n1;
		}
		
		if (i2 == ir) {
			return n2;
		}
		return parent;
	}
	
	private List<Node> inOrderTraverse(Node root) {
		if (root == null) {
			return null;
		}
		List<Node> list = new ArrayList<Node>();
		if (root.left != null) {
			list.addAll(inOrderTraverse(root.left));
		}
		list.add(root);
		if (root.right != null) {
			list.addAll(inOrderTraverse(root.right));
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		
		Node[] node = new Node[16];
		for (int i = 8; i < 16; i++) {
			node[i] = new Node(i);
		}
		node[4] = new Node(4, node[8], node[9]);
		node[5] = new Node(5, node[10], node[11]);
		node[6] = new Node(6, node[12], node[13]);
		node[7] = new Node(7, node[14], node[15]);
		node[3] = new Node(3, node[6], node[7]);
		node[2] = new Node(2, node[4], node[5]);
		node[1] = new Node(1, node[2], node[3]);
		node[0] = new Node(0);
		CommonParent cp = new CommonParent();
		System.out.println(cp.getCommonParent(node[1], node[8], node[8]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[0]));
		System.out.println(cp.getCommonParent(node[1], node[0], node[0]));
		System.out.println(cp.getCommonParent(node[0], node[0], node[0]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[9]));
		System.out.println(cp.getCommonParent(node[1], node[9], node[8]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[4]));
		System.out.println(cp.getCommonParent(node[1], node[9], node[5]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[5]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[6]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[7]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[10]));
		System.out.println(cp.getCommonParent(node[1], node[8], node[15]));
		System.out.println(cp.getCommonParent(node[1], node[6], node[15]));
		
		
	}
	
}
