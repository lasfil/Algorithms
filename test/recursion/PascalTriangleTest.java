package recursion;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Test;

public class PascalTriangleTest {

	@Test
	public void test() {
		PascalTriangle pt = new PascalTriangle(5);
		pt.produce();
		//pt.print();
		System.out.println(pt);
	}
	
	@Test
	public void test1() {
		DecimalFormat df = new DecimalFormat("000.0000");
		df.setMinimumIntegerDigits(3);
		System.out.println(df.format((int)Math.log10(0)));
	}

}
