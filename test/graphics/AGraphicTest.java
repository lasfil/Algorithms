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
		flights.addArc("b", "c");
		flights.addArc("c", "f");
		flights.addArc("a", "c");
		//flights.addArc("l", "m");
		//flights.addArc("l", "j");
		//flights.addArc("j", "m");
		//flights.addArc("m", "b");
		//flights.addArc("d", "e");
		flights.addArc("i", "g");
		flights.addArc("g", "h");
		flights.addArc("h", "k");
		flights.addArc("k", "i");

		//out.println(flights);
		//flights.breadthFirstSearch();
		//flights.depthFirstSearch();
		
		//out.println(flights.breadthFirstPath("a", "m"));
		
		out.println(flights.connected("g", "i"));
		
		//out.println(flights.isTwoColorable());
	}

}
