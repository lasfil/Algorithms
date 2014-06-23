package tree;

import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.Test;

public class HuffmanTreeTest {

	@Test
	public void testCompleteTree() {
		HuffmanTree ht = new HuffmanTree();
		ht.addElement("a", 9);
		ht.addElement("b", 5);
		ht.addElement("c", 2);
		ht.addElement("d", 4);
		ht.addElement("e", 6);
		ht.addElement("f", 9);

		ht.completeTree();
		ht.getElementCode();

		System.out.println(ht);

		String content = "decaffab";

		String codes = ht.encode(content);
		System.out.println(codes);

		String result = ht.decode(codes);
		System.out.println("result:" + result);

		assertEquals("decoded string: " + result + " is not equal to input: "
				+ content, content, result);

	}

	@Test
	public void testScanner() {

		//String s = "1 1 0 1 1 0 1 1";
		String s = "11011011";
	
		Scanner scan = new Scanner(s);
		scan.useDelimiter("");
		while (scan.hasNextByte()) {
			System.out.println(scan.nextByte());
		}
		scan.close();

		/*
		 * String str = "1.1 22.2 s 4 5.3 6 7.5 8 9"; Scanner scanner = new
		 * Scanner(str); // scanner.useDelimiter("\\."); while
		 * (scanner.hasNext()) { if
		 * (scanner.hasNext(Pattern.compile("\\d\\.\\d"))) {
		 * System.out.println(scanner.next()); } else { scanner.next();//
		 * 要调用一下next()相关的方法才会到下一个token } } scanner.close();
		 */
	}

}
