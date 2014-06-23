package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class BiLinkedListTest {

	@Test
	public void testAddLast() {
		BiLinkedList<String> l = new BiLinkedList<String>();
		l.addLast("7");
		l.addLast("6");
		l.addLast("5");
		l.addLast("4");
		l.addLast("3");
		

		//BiLinkedList.selectSort(l);
		//BiLinkedList.insertionSort(l);
		BiLinkedList.bubbleSort(l);
		//BiLinkedList.quickSort(l);
		System.out.println(l);


		
	}

}
