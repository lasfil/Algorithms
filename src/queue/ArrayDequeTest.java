package queue;
import java.util.Queue;
import java.util.ArrayDeque;
public class ArrayDequeTest {
	public static void main(String[] args) {
		ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
		aq.add(1);
		aq.add(2);
		aq.add(3);
		aq.addLast(4);
		System.out.println(aq.poll());
	}
}
