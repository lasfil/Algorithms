package tree;

import static java.lang.System.out;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testAdd() {
		HeapADT<Integer> heap = new HeapADT<Integer>();
		int[] a = new int[] { 50, 30, 20, 35, 41, 5, 18, 80, 70, 500, 52, 59,
				300, 505, 103, 299, 600, 800, 540, 39, 48, 190, 290, 399, 350,
				355, 291, 292, 293 };

		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		for (Integer inte : l)
			heap.add(inte);
		while (heap.size() > 0)
			out.println(heap.removeMin());
		/*
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap); heap.removeMin();
		 * out.println(heap); heap.removeMin(); out.println(heap);
		 * heap.removeMin(); out.println(heap);
		 */
	}

	@Test
	public void TestAddTask() {
		HeapADT<Task> heap = new HeapADT<Task>();

		heap.add(new Task(1, "a"));
		heap.add(new Task(2, "b"));
		heap.add(new Task(3, "c"));
		heap.add(new Task(4, "d"));
		heap.add(new Task(5, "e"));
		heap.add(new Task(6, "f"));
		heap.add(new Task(7, "g"));
		heap.add(new Task(8, "h"));
		heap.add(new Task(5, "i"));
		heap.add(new Task(6, "j"));
		heap.add(new Task(7, "k"));
		heap.add(new Task(8, "l"));
		heap.add(new Task(1, "m"));
		heap.add(new Task(2, "n"));
		heap.add(new Task(3, "o"));
		heap.add(new Task(4, "p"));
		heap.add(new Task(5, "q"));
		heap.add(new Task(6, "r"));
		heap.add(new Task(7, "s"));
		heap.add(new Task(8, "t"));
		heap.add(new Task(5, "u"));
		heap.add(new Task(6, "v"));
		heap.add(new Task(7, "w"));
		heap.add(new Task(8, "x"));

		out.println(heap);

		while (heap.size() > 0) {
			// heap.removeMin();
			out.println(heap.removeMin());
		}
	}

	@Test
	public void TestPriority() {
		PriorityQueue<Task> heap = new PriorityQueue<Task>();

		heap.add(new Task(1, "a"));
		heap.add(new Task(2, "b"));
		heap.add(new Task(3, "c"));
		heap.add(new Task(4, "d"));
		heap.add(new Task(5, "e"));
		heap.add(new Task(6, "f"));
		heap.add(new Task(7, "g"));
		heap.add(new Task(8, "h"));
		heap.add(new Task(5, "i"));
		heap.add(new Task(6, "j"));
		heap.add(new Task(7, "k"));
		heap.add(new Task(8, "l"));
		heap.add(new Task(1, "m"));
		heap.add(new Task(2, "n"));
		heap.add(new Task(3, "o"));
		heap.add(new Task(4, "p"));
		heap.add(new Task(5, "q"));
		heap.add(new Task(6, "r"));
		heap.add(new Task(7, "s"));
		heap.add(new Task(8, "t"));
		heap.add(new Task(5, "u"));
		heap.add(new Task(6, "v"));
		heap.add(new Task(7, "w"));
		heap.add(new Task(8, "x"));

		//out.println(heap);

		while (heap.size() > 0) {
			// heap.removeMin();
			out.println(heap.poll());
		}
	}

	public class Task implements Comparable<Task> {
		int priority;
		String name;

		public Task(int p, String s) {
			this.priority = p;
			this.name = s;
		}

		@Override
		public int compareTo(Task t) {
			if (this.priority > t.priority)
				return 1;
			else if (this.priority < t.priority)
				return -1;
			else
				return 0;
		}

		@Override
		public String toString() {
			return "priority: " + this.priority + " task " + this.name;
		}
	}
}
