package other;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	}

}
