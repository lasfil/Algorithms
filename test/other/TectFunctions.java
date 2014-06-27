package other;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class TectFunctions {

	@Test
	public void test() {
		ArrayList<Integer> al = new ArrayList<Integer>() {
			{
				add(3);
				add(5);
				add(8);
				add(0);
				add(28);
				add(2);
				add(39);

			}
		};
		
		List<Integer> als = al.subList(3, 7);
		System.out.println(al);

		System.out.println(als);
		
		List<Integer>[] mailboxes = (ArrayList<Integer>[]) (new ArrayList[4]);
		
		System.out.println(new Integer(15).compareTo(null));

	}

}
