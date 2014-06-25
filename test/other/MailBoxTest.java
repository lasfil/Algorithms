package other;

import static org.junit.Assert.*;

import org.junit.Test;

public class MailBoxTest {

	@Test
	public void testMail() {
		MailBox mb = new MailBox(8,4,0);
		mb.mail();
		System.out.println(mb);
	}

}
