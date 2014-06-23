package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class BiLinkedListTest {

	@Test
	public void testAddLast() {
		BiLinkedList<String> l = new BiLinkedList<String>();
		l.addLast("a");
		l.addLast("c");
		l.addLast("e");
		l.addLast("h");
		l.addLast("d");
		//l.addLast("5");
		l.addLast("x");
		//l.addLast("5");


		

		//BiLinkedList.selectSort(l);
		//BiLinkedList.insertionSort(l);
		BiLinkedList.bubbleSort(l);
		//BiLinkedList.quickSort(l);
		//BiLinkedList.mergeSort(l);
		//System.out.println("17".compareTo("5"));
		System.out.println(l);


		
	}

}
