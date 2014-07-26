package graphics;

import static org.junit.Assert.*;
import static java.lang.System.*;

import org.junit.Test;

public class AGraphicTest {

	@Test
	public void testAdd() {
		AGraphic flights = new AGraphic(new String[] { "a", "b", "c", "d", "e",
				"f", "g", "h", "i", "j", "k", "l", "m" });
		flights.addArc("a", "b");
		flights.addArc("a", "c");
		flights.addArc("a", "f");
		flights.addArc("a", "l");
		flights.addArc("l", "m");
		flights.addArc("l", "j");
		flights.addArc("j", "m");
		flights.addArc("m", "b");
		flights.addArc("d", "e");
		flights.addArc("i", "g");
		flights.addArc("g", "h");
		flights.addArc("g", "k");
		flights.addArc("h", "k");

		//out.println(flights);
		//flights.breadthFirstSearch();
		
		out.println(flights.breadthFirstPath("a", "m"));
	}

}
