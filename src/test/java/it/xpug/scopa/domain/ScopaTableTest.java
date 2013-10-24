package it.xpug.scopa.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class ScopaTableTest extends ScopaTable {

	private static final CardSet EMPTY = new CardSet();
	private static final Card ACE_OF_CUPS = new Card(1, Suit.CUPS);
	private static final Card TWO_OF_CUPS = new Card(2, Suit.CUPS);
	private static final Card THREE_OF_CUPS = new Card(3, Suit.CUPS);
	private static final Card TWO_OF_COINS = new Card(2, Suit.COINS);
	private static final Card ACE_OF_SWORDS = new Card(1, Suit.SWORDS);
	private static final Card TWO_OF_SWORDS = new Card(2, Suit.SWORDS);

	@Test
	public void returnsMatchingCards() throws Exception {
		ScopaTable table = new ScopaTable(ACE_OF_CUPS, TWO_OF_CUPS, TWO_OF_COINS);
		assertEquals(EMPTY, table.allMatching(THREE_OF_CUPS));
		assertEquals(setWith(ACE_OF_CUPS), table.allMatching(ACE_OF_SWORDS));
		assertEquals(setWith(TWO_OF_CUPS, TWO_OF_COINS), table.allMatching(TWO_OF_SWORDS));
	}
	
	private CardSet setWith(Card ...cards) {
		return new CardSet(cards);
	}

}
