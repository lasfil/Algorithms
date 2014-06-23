package queue;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaesarCipherTest {

	@Test
	public void test() {
		CaesarCipher c = new CaesarCipher();
		c.setKeys(new int[] { 5, 12, -3, 8, -9, 4, 10 });
		String message = "All programmers are playwrights and all "
				+ "computers are lousy actors.";
		String cMessage = c.cipher(message);

		assertEquals(
				"ciphered message is not correct: " + cMessage,
				"Fxi(gvyl~^udi|x,^z\\$zqmvimqmp(Xrn%mitgyr|r|\\v}%mompyzv(Xg~t~p6",
				cMessage);
		
		assertEquals("deciphered message is not correct" ,message, c.decipher(cMessage));
	}

}
