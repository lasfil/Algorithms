package tree;


public class MyTreeMap<K extends Comparable<K>> {
	private transient Entry<K> root = null;
	private transient int size = 0;

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	static final class Entry<K> {
		K key;
		Entry<K> left = null;
		Entry<K> right = null;
		Entry<K> parent;
		boolean color = BLACK;

		/**
		 * Make a new cell with given key, value, and parent, and with
		 * {@code null} child links, and BLACK color.
		 */
		Entry(K key, Entry<K> parent) {
			this.key = key;
			this.parent = parent;
		}

		/**
		 * Returns the key.
		 * 
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		public String toString() {
			return key.toString() + "[" + (color ? "B" : "R") + "]";
		}

	}

	public K put(K key) {
		Entry<K> t = root;
		if (t == null) {

			root = new Entry<>(key, null);
			size = 1;
			return null;
		}
		int cmp;
		Entry<K> parent;
		// split comparator and comparable paths

		if (key == null)
			throw new NullPointerException();
		Comparable<? super K> k = (Comparable<? super K>) key;
		do {
			parent = t;
			cmp = k.compareTo(t.key);
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;

		} while (t != null);

		Entry<K> e = new Entry<>(key, parent);
		if (cmp < 0)
			parent.left = e;
		else
			parent.right = e;
		fixAfterInsertion(e);
		size++;
		return null;
	}

	private void fixAfterInsertion(Entry<K> x) {
		x.color = RED;

		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Entry<K> y = rightOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Entry<K> y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}

	private static <K, V> boolean colorOf(Entry<K> p) {
		return (p == null ? BLACK : p.color);
	}

	private static <K, V> Entry<K> parentOf(Entry<K> p) {
		return (p == null ? null : p.parent);
	}

	private static <K, V> void setColor(Entry<K> p, boolean c) {
		if (p != null)
			p.color = c;
	}

	private static <K, V> Entry<K> leftOf(Entry<K> p) {
		return (p == null) ? null : p.left;
	}

	private static <K, V> Entry<K> rightOf(Entry<K> p) {
		return (p == null) ? null : p.right;
	}

	/** From CLR */
	private void rotateLeft(Entry<K> p) {
		if (p != null) {
			Entry<K> r = p.right;
			p.right = r.left;
			if (r.left != null)
				r.left.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}

	/** From CLR */
	private void rotateRight(Entry<K> p) {
		if (p != null) {
			Entry<K> l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			l.right = p;
			p.parent = l;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ]");

		stringAppend(sb, root, "");

		String result = sb.toString();
		sb.setLength(0);
		return result;
	}

	private void stringAppend(StringBuffer sb, Entry<K> n, String prefix) {

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

	public K remove(Object key) {
		Entry<K> p = getEntry(key);
		if (p == null)
			return null;

		K oldValue = p.key;
		deleteEntry(p);
		return oldValue;
	}

	final Entry<K> getEntry(Object key) {
		// Offload comparator-based version for sake of performance

		if (key == null)
			throw new NullPointerException();
		Comparable<? super K> k = (Comparable<? super K>) key;
		Entry<K> p = root;
		while (p != null) {
			int cmp = k.compareTo(p.key);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	private void deleteEntry(Entry<K> p) {
		size--;

		// If strictly internal, copy successor's element to p and then make p
		// point to successor.
		if (p.left != null && p.right != null) {
			Entry<K> s = successor(p);
			p.key = s.key;
			p = s;
		} // p has 2 children

		// Start fixup at replacement node, if it exists.
		Entry<K> replacement = (p.left != null ? p.left : p.right);

		if (replacement != null) {
			// Link replacement to parent
			replacement.parent = p.parent;
			if (p.parent == null)
				root = replacement;
			else if (p == p.parent.left)
				p.parent.left = replacement;
			else
				p.parent.right = replacement;

			// Null out links so they are OK to use by fixAfterDeletion.
			p.left = p.right = p.parent = null;

			// Fix replacement
			if (p.color == BLACK)
				fixAfterDeletion(replacement);
		} else if (p.parent == null) { // return if we are the only node.
			root = null;
		} else { // No children. Use self as phantom replacement and unlink.
			if (p.color == BLACK)
				fixAfterDeletion(p);

			if (p.parent != null) {
				if (p == p.parent.left)
					p.parent.left = null;
				else if (p == p.parent.right)
					p.parent.right = null;
				p.parent = null;
			}
		}
	}

	/** From CLR */
	private void fixAfterDeletion(Entry<K> x) {
		while (x != root && colorOf(x) == BLACK) {
			if (x == leftOf(parentOf(x))) {
				Entry<K> sib = rightOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}

				if (colorOf(leftOf(sib)) == BLACK
						&& colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			} else { // symmetric
				Entry<K> sib = leftOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}

				if (colorOf(rightOf(sib)) == BLACK
						&& colorOf(leftOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}

		setColor(x, BLACK);
	}

	static <K> MyTreeMap.Entry<K> successor(Entry<K> t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Entry<K> p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else {
			Entry<K> p = t.parent;
			Entry<K> ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}
}
