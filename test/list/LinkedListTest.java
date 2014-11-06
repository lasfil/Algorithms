package list;

import java.util.Collections;
import java.util.LinkedList;

public class LinkedListTest<T> extends LinkedList<T>{
	
	public LinkedListTest() {
		//Node<T> n =this.first;
	}
	public static void main(String[] args) {
		LinkedList<String> l = new LinkedList<String>();

		l.addFirst("2");
		l.add("1");
		l.add("3");
		l.add("10");
		l.add("6");
		l.add("7");
		
		Collections.sort(l);
		

	}
}
