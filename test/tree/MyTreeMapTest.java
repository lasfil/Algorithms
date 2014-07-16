package tree;

import static org.junit.Assert.*;
import static java.lang.System.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyTreeMapTest {

	@Test
	public void testPut() {
		int[] a = new int[] { 12, 1, 9, 2, 0, 11, 7, 19, 4, 15, 18, 5, 14, 13,
				10, 16, 6, 3, 8, 17 };
		List<Integer> l = new ArrayList<Integer>();
		for (int i : a) {
			l.add(i);
		}
		MyTreeMap<Integer> t = new MyTreeMap<Integer>();
		for (Integer inte : l)
			t.put(inte);
		out.println(t);
		
		for (Integer inte :l) {
			out.println("remove: " + inte);

			t.remove(inte);
			out.println(t);
		}
		
	}

}
