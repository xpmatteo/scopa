package it.xpug.scopa.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class CardSetTest {

	private static final Card ACE_OF_CUPS = new Card(1, Suit.CUPS);
	private static final Card TWO_OF_CUPS = new Card(2, Suit.CUPS);
	private static final Card TWO_OF_COINS = new Card(2, Suit.COINS);
	private CardSet set = new CardSet(ACE_OF_CUPS, TWO_OF_CUPS, TWO_OF_COINS);

	@Test
	public void supportsEquals() {
		CardSet a = new CardSet();
		
		assertNotEquals(a, null);
		assertNotEquals(a, "foo");
		assertEquals(a, a);
	}
	
	@Test
	public void returnsArrayOfParams() throws Exception {
		String[] expected = new String[] { "coins-02", "cups-01", "cups-02" };
		assertArrayEquals(expected , set.toParams());
	}
}
