package list;

import static org.junit.Assert.*;
import static java.lang.System.*;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListSortTest {

	@Test
	public void testSelectSort() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(10);
		al.add(8);
		al.add(3);
		al.add(2);
		al.add(9);
		al.add(3);

		ArrayLIstSort.selectionSort(al);

		out.println(al);
	}
	
	@Test
	public void testInsertionSort() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(8);
		al.add(3);
		al.add(2);
		al.add(9);
		al.add(3);

		ArrayLIstSort.insertionSort(al);

		out.println(al);
	}
	
	@Test
	public void testBubbleSort() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		al.add(7);
		al.add(6);
		al.add(5);
		al.add(4);
		al.add(3);
		al.add(2);


		ArrayLIstSort.bubbleSort(al);

		out.println(al);
	}
	
	@Test
	public void testQuickSort() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		al.add(3);
		al.add(2);
		al.add(1);
		al.add(6);
		al.add(8);
		al.add(9);
		al.add(7);
		al.add(2);
		al.add(5);


		ArrayLIstSort.quickSort(al);

		out.println(al);
	}
	

}
