package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HuffmanTree {
	public static class Element {
		private String str;
		private int weight;
		private String code;

		public Element(String str, int weight, String code) {
			this.str = str;
			this.weight = weight;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public String getStr() {
			return str;
		}

		public int getWeight() {
			return weight;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

	}

	private static class Node {
		private String string;
		private int weight;
		private int parent;
		private int left;
		private int right;
		private String code;

		/*
		 * public int getParent() { return parent; }
		 * 
		 * public void setParent(int parent) { this.parent = parent; }
		 * 
		 * public int getLeft() { return left; }
		 * 
		 * public void setLeft(int left) { this.left = left; }
		 * 
		 * public int getRight() { return right; }
		 * 
		 * public void setRight(int right) { this.right = right; }
		 */

		public Node(String string, int weight, int parent, int left, int right,
				String code) {
			super();
			this.string = string;
			this.weight = weight;
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.code = code;
		}

		public Node(String string, int weight, String code) {
			super();
			this.string = string;
			this.weight = weight;
			this.code = code;
		}

	}

	private final String lCode = "0";

	private final String rCode = "1";

	private List<Node> tree = new ArrayList<Node>();

	private List<Element> eList = new ArrayList<Element>();

	private int treeSize;

	public void addElement(String str, int weight) {
		eList.add(new Element(str, weight, null));
	}

	public void getElementCode() {
		Node n = null;
		Node temp = null;
		for (int i = 1; i <= eList.size(); i++) {
			n = tree.get(i);
			temp = tree.get(n.parent);
			while (temp.parent != 0) {
				n.code = temp.code + n.code;

				temp = tree.get(temp.parent);
			}

			eList.get(i - 1).code = n.code;
		}
	}

	private Map<String, String> getDictionary() {

		Map<String, String> dic = new HashMap<String, String>();
		for (int i = 0; i < eList.size(); i++) {
			dic.put(eList.get(i).str, eList.get(i).code);
		}
		return dic;
	}

	public void completeTree1() {
		if (tree == null || tree.isEmpty() || tree.size() == 0) {
			if (eList != null && eList.size() != 0) {
				innitialTree();
			} else
				return;
		}
		Node[] children = new Node[2];
		Node left = null;
		Node right = null;
		// get first parent
		select(children);
		left = children[0];
		right = children[1];
		if (right != null) {
			tree.add(new Node(null, left.weight + right.weight, 0, tree
					.indexOf(left), tree.indexOf(right), rCode));
			left.parent = tree.size() - 1;
			right.parent = tree.size() - 1;
			left.code = lCode;
			right.code = rCode;

		}

		for (int i = tree.size(); i <= treeSize; i++) {

			left = minimal();
			right = tree.get(tree.size() - 1);

			if (left != right) {
				tree.add(new Node(null, left.weight + right.weight, 0, tree
						.indexOf(left), tree.indexOf(right), rCode));
				left.parent = tree.size() - 1;
				right.parent = tree.size() - 1;
				right.code = rCode;
			}
		}

		left = null;
		right = null;
		children = null;

	}

	public void innitialTree() {

		if (eList.size() == 0) {
			return;
		}
		tree.add(new Node(null, 0, null));

		for (Element e : eList) {
			tree.add(new Node(e.getStr(), e.getWeight(), lCode));
		}

		treeSize = 2 * eList.size() - 1;
	}

	private Node minimal() {
		int a = 0;
		Node mini = null;
		while (++a <= eList.size()) {
			if (tree.get(a).parent == 0) {
				if (mini == null || tree.get(a).weight < mini.weight) {
					mini = tree.get(a);
				}
			}
		}
		return mini;
	}
	
	

	private void select(Node[] children) {
		if (children.length < 2) {
			System.out.println("The length of children is not long enough");
			return;
		}
		int a = 0;
		while (a++ < tree.size()) {
			if (tree.get(a).parent == 0) {
				children[0] = tree.get(a);
				break;
			}
		}
		while (++a < tree.size()) {
			if (tree.get(a).parent == 0) {
				children[1] = tree.get(a);
				break;
			}
		}

		if (children[0] == children[1])
			return;
		Node n = null;
		for (int i = 2; i < tree.size(); i++) {
			n = tree.get(i);
			if (n.parent == 0 && n != children[0]) {
				if (n.weight < children[0].weight) {
					children[1] = children[0];
					children[0] = n;
				} else {
					if (n.weight < children[1].weight) {
						children[1] = n;

					}
				}
			}
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tree.size(); i++) {
			Node n = tree.get(i);
			sb.append("[");
			sb.append(i);
			sb.append("]");
			sb.append(" ");
			sb.append(n.string);
			sb.append(" ");
			sb.append(n.weight);
			sb.append(" ");
			sb.append(n.parent);
			sb.append(" ");
			sb.append(n.left);
			sb.append(" ");
			sb.append(n.right);
			sb.append(" ");
			sb.append(n.code);
			sb.append("\n");

		}

		sb.append("Element: \n");

		for (Element e : eList) {
			sb.append(e.str + " ");
			sb.append(e.weight + " ");
			sb.append(e.code + "\n");
		}
		return sb.toString();
	}

	public int treeSize() {

		return treeSize;
	}

	public String encode(String string) {
		StringBuffer sb = new StringBuffer();

		for (int j = 0; j < string.length(); j++) {
			char c = string.charAt(j);
			for (int i = 0; i < eList.size(); i++) {
				if (eList.get(i).str.equals(String.valueOf(c))) {
					sb.append(eList.get(i).code);
					break;
				}
			}
		}
		return sb.toString();
	}

	public String decode1(String codes) {
		int index = -1;
		Map<String, String> dic = this.getDictionary();
		List<String> codeList = new ArrayList<String>(dic.values());

		List<String> strList = new ArrayList<String>(dic.keySet());

		StringBuffer temp = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < codes.length(); i++) {
			temp.append(String.valueOf(codes.charAt(i)));
			index = codeList.indexOf(temp.toString());

			if (index != -1) {
				result.append(strList.get(index));
				temp.setLength(0);
			}
		}

		String s = result.toString();
		result.setLength(0);

		return s;
	}

	public void completeTree() {
		if (tree == null || tree.isEmpty() || tree.size() == 0) {
			if (eList != null && eList.size() != 0) {
				innitialTree();
			} else
				return;
		}
		Node[] children = new Node[2];
		Node left = null;
		Node right = null;

		for (int i = tree.size(); i <= treeSize; i++) {

			select(children);
			left = children[0];
			right = children[1];

			if (left != right) {
				tree.add(new Node(null, left.weight + right.weight, 0, tree
						.indexOf(left), tree.indexOf(right), rCode));
				left.parent = tree.size() - 1;
				right.parent = tree.size() - 1;
			}
			left.code = lCode;
			right.code = rCode;
			// }
		}

		left = null;
		right = null;
		children = null;

	}

	public String decode(String codes) {
		StringBuffer sb = new StringBuffer();
		Scanner scanner = new Scanner(codes);
		scanner.useDelimiter("");
		Node n = tree.get(tree.size() - 1);
		while (scanner.hasNextInt()) {
			int i = scanner.nextInt();
			if (i == 0) {
				n = tree.get(n.left);
			} else if (i == 1) {
				n = tree.get(n.right);
			}
			if (n.left == 0) {
				sb.append(n.string);
				n = tree.get(tree.size() - 1);
			}

		}

		String result = sb.toString();
		sb.setLength(0);
		scanner.close();
		return result;
	}
}
