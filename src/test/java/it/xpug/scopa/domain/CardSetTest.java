package it.xpug.scopa.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class CardSetTest {

	private static final CardSet EMPTY = new CardSet();
	private static final Card ACE_OF_CUPS = new Card(1, Suit.CUPS);
	private static final Card TWO_OF_CUPS = new Card(2, Suit.CUPS);
	private static final Card THREE_OF_CUPS = new Card(3, Suit.CUPS);
	private static final Card TWO_OF_COINS = new Card(2, Suit.COINS);
	private static final Card ACE_OF_SWORDS = new Card(1, Suit.SWORDS);
	private static final Card TWO_OF_SWORDS = new Card(2, Suit.SWORDS);
	private CardSet set = setWith(ACE_OF_CUPS, TWO_OF_CUPS, TWO_OF_COINS);

	@Test
	public void returnsMatchingCards() throws Exception {
		assertEquals(EMPTY, set.allMatching(THREE_OF_CUPS));
		assertEquals(setWith(ACE_OF_CUPS), set.allMatching(ACE_OF_SWORDS));
		assertEquals(setWith(TWO_OF_CUPS, TWO_OF_COINS), set.allMatching(TWO_OF_SWORDS));
	}
	
	@Test
	public void supportsEquals() {
		CardSet a = new CardSet();
		
		assertNotEquals(a, null);
		assertNotEquals(a, "foo");
		assertEquals(a, a);
	}
	
	@Test
	public void returnsArrayOfParams() throws Exception {
		String[] expected = new String[] { "1-cups", "2-coins", "2-cups" };
		assertArrayEquals(expected , set.toParams());
	}

	private CardSet setWith(Card ...cards) {
		return new CardSet(cards);
	}

}
