package stack;


import static org.junit.Assert.*;

import org.junit.Test;

import stack.ToBinary;

public class testToBinary {

	@Test
	public void testtoBinary() {
		int i = 100;
		System.out.println(ToBinary.toBinary(i));
		
		System.out.println(Integer.toOctalString(i));
	}

}
