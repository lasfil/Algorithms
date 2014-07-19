package tree;

import static java.lang.System.out;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testAdd() {
		HeapADT<Integer> heap = new HeapADT<Integer>();
		int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
	    300, 505, 103, 299, 600, 800, 540, 39, 48, 190, 290, 399,
		350, 355, 291, 292, 293 };

		List<Integer> l = new ArrayList<Integer>();
		for (int i :a) {
			l.add(i);
		}
		for (Integer inte : l)
			heap.add(inte);
		while(heap.size()>0)
			out.println(heap.removeMin());

		/*out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);
		heap.removeMin();
		out.println(heap);*/
		
	}	
}
