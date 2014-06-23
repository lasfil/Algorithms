package com.zenkirin;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import stack.Hanoi;

public class HanoiTest {

	@Test
	public void test() {
		int n = 3;
		Hanoi h = new Hanoi(n);
		h.hanoi();
		
		Stack<Integer> s = new Stack<Integer>();
		for(int i = n; i>0; i--)
			s.push(i);
		assertEquals("The hanoi result isn't correct", s, h.getEnd());
	}
	
	@Test
	public  void test1() {
		System.out.println(-4%7);
	}

}
