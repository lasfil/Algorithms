package com.zenkirin;

import static org.junit.Assert.*;

import org.junit.Test;

import stack.PostfixCompute;

public class PostfixComputeTest {

	@Test
	public void testcompute() {
		try {
			int input = (67*5+20)/8-7*104;
			System.out.println(input);
			
			int result = PostfixCompute.compute("67 5 * 20 + 8 / 7 104 * -");
			System.out.println(result);
			
			
			
			assertEquals("the result of PostfixCompute.compute is not correct", input, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
