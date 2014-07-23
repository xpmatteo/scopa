package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

public class CardTest {

	@Test
	public void canBeCreatedFromParam() throws Exception {
		assertEquals(bastoni(3).toString(), Card.parse("wands-03").toString());
	}

	@Test
	public void returnsItsParam() throws Exception {
		assertEquals("wands-03", bastoni(3).toParam());
	}

	@Test
	public void cardsWithSameRank_match() throws Exception {
		assertThat(coppe(7), isMatching(swords(7)));
	}

	@Test
	public void cardsWithDifferentRank_doNotMatch() throws Exception {
		assertThat(coppe(7), not(isMatching(coppe(5))));
	}

	@Test
	public void hasSensibleToString() throws Exception {
		assertEquals("7 of swords", swords(7).toString());
	}

	@Test
	public void hasItalianName() throws Exception {
		assertEquals("2 di coppe", coppe(2).name());
		assertEquals("3 di bastoni", bastoni(3).name());
		assertEquals("4 di denari", coins(4).name());
		assertEquals("5 di spade", swords(5).name());
		assertEquals("Asso di spade", swords(1).name());
		assertEquals("Fante di denari", coins(8).name());
		assertEquals("Cavallo di bastoni", bastoni(9).name());
		assertEquals("Re di coppe", coppe(10).name());
	}

	@Test
	public void supportsEquals() throws Exception {
		Card a = new Card(3, Suit.SWORDS);
		Card b = new Card(3, Suit.SWORDS);
		Card c = new Card(5, Suit.SWORDS);
		Card d = new Card(3, Suit.CUPS);

		assertNotEquals(a, null);
		assertNotEquals(a, "foo");
		assertEquals(a, a);
		assertEquals(a, b);
		assertEquals(b, a);
		assertNotEquals(a, c);
		assertNotEquals(a, d);
	}

	private Matcher<Card> isMatching(final Card card) {
		return new TypeSafeMatcher<Card>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("a card matching " + card);
			}

			@Override
			protected boolean matchesSafely(Card item) {
				return card.matches(item);
			}
		};
	}
}
