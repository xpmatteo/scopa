package it.xpug.scopa.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

public class CardTest {

	@Test
	public void canBeCreatedFromParam() throws Exception {
		Card expected = new Card(3, Suit.WANDS);
		Card actual = Card.parse("wands-03");
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	public void returnsItsParam() throws Exception {
		Card card = new Card(3, Suit.WANDS);
		assertEquals("wands-03", card.toParam());
	}
	
	@Test
	public void cardsWithSameRank_match() throws Exception {
		Card sevenOfCups = new Card(7, Suit.CUPS);
		Card sevenOfSwords = new Card(7, Suit.SWORDS);
		assertThat(sevenOfCups, isMatching(sevenOfSwords));
	}
	
	@Test
	public void cardsWithDifferentRank_doNotMatch() throws Exception {
		Card sevenOfCups = new Card(7, Suit.CUPS);
		Card fiveOfCups= new Card(5, Suit.CUPS);
		assertThat(sevenOfCups, not(isMatching(fiveOfCups)));
	}
	
	@Test
	public void hasSensibleToString() throws Exception {
		Card sevenOfSwords = new Card(7, Suit.SWORDS);
		assertEquals("7 of swords", sevenOfSwords.toString());
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
