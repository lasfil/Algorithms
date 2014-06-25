package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class BiLinkedListTest {

	@Test
	public void testAddLast() {
		BiLinkedList<String> l = new BiLinkedList<String>();
		l.addLast("y");
		l.addLast("c");
		l.addLast("e");
		l.addLast("h");
		l.addLast("d");
		// l.addLast("5");
		l.addLast("x");
		// l.addLast("5");

		// BiLinkedList.selectSort(l);
		// BiLinkedList.insertionSort(l);
		BiLinkedList.bubbleSort(l);
		// BiLinkedList.quickSort(l);
		// BiLinkedList.mergeSort(l);
		// System.out.println("17".compareTo("5"));
		System.out.println(l);

	}

	@Test
	public void testRadixSort() {
		BiLinkedList<Integer> bl = new BiLinkedList<Integer>() {
			{
				addLast(new Integer(13811));
				addLast(new Integer(34166));
				addLast(new Integer(89456));
				addLast(new Integer(13812));
				addLast(new Integer(36129));
				addLast(new Integer(99374));
				addLast(new Integer(27982));
				
			}
		};
		
		BiLinkedList.radixSort(bl, 5);
		
		System.out.println(bl);

	}

}
