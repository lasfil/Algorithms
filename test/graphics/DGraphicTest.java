package graphics;

import static org.junit.Assert.*;
import static java.lang.System.*;

import org.junit.Test;

public class DGraphicTest {

	@Test
	public void testAdd() {
		DGraphic flights = new DGraphic(new String[] { "BJ", "SH", "SZ", "XN",
				"CD", "HK" });
		flights.addArc(0, 1, 1600);
		flights.addArc(0, 2, 2500);
		flights.addArc(0, 3, 1800);
		flights.addArc(0, 4, 2600);
		flights.addArc(1, 2, 1000);
		flights.addArc(1, 5, 1600);
		flights.addArc(2, 4, 1200);
		flights.addArc(2, 5, 200);
		flights.addArc(3, 0, 1800);
		flights.addArc(3, 1, 2000);
		flights.addArc(4, 0, 2600);
		flights.addArc(4, 2, 1200);
		flights.addArc(5, 0, 3200);
		flights.addArc(5, 1, 1600);

		out.println(flights);
	}

}
