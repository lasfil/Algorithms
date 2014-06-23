package list;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MyLinkedListTest {
	@Test
	public void testMergeInOrder() {
		MyLinkedList<String> l = new MyLinkedList<String>();
		MyLinkedList<String> l1 = new MyLinkedList<String>();

		l.add("1");
		l.add("8");

		l1.add("2");
		l1.add("4");
		l1.add("6");

		l.mergeInOrder(l1);
		// l.printList();

		//l.reverse1();
		l.printList();

	}

	@Test
	public void testSort() {
		MyLinkedList<String> l = new MyLinkedList<String>();

		l.add("1");
		l.add("3");

		l.add("2");
		l.add("4");
		l.add("6");
		
		l.sort();
		l.printList();
		
	}

}
