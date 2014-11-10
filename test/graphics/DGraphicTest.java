package graphics;

import static org.junit.Assert.*;
import static java.lang.System.*;

import org.junit.Test;

public class DGraphicTest {

	@Test
	public void testAdd() {
		DGraphic flights = new DGraphic(new String[] { "0", "1", "2", "3",
				"4", "5" ,"6", "7","8","9","10", "11","12"});
		flights.addArc(4, 2, 0);
		flights.addArc(2, 3, 0);
		flights.addArc(3, 2, 0);
		flights.addArc(6, 0, 0);
		flights.addArc(0, 1, 0);
		flights.addArc(2, 0, 0);
		flights.addArc(11, 12, 0);
		flights.addArc(12, 9, 0);
		flights.addArc(9, 10, 0);
		flights.addArc(9, 11, 0);
		flights.addArc(8, 9, 0);
		flights.addArc(10, 12, 0);
		flights.addArc(11, 4, 0);
		flights.addArc(4, 3, 0);
		flights.addArc(3, 5, 0);
		flights.addArc(7, 8, 0);
		flights.addArc(8, 7, 0);
		flights.addArc(5, 4, 0);
		flights.addArc(0, 5, 0);
		flights.addArc(6, 4, 0);
		flights.addArc(6, 9, 0);
		flights.addArc(7, 6, 0);





		//out.println(flights);
		//flights.depthFirstSearch();
		//flights.connectedVertice("TJ");
		out.println(flights.depthFirstPath("6", "2"));
		out.println(flights.depthFirstPath("2", "6"));
		//out.println(flights.breadthFirstPath("HK", "CD"));
		//flights.breadthFirstSearch();
		//out.println(flights.hasCycle());
		//out.println(flights.reverse().topoOrder());
		//out.println("-----");
		out.println(flights.stronglyConnected("2", "6"));
	}

}
