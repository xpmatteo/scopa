package it.xpug.simplewebapp.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.*;

public class CardTest {

	
	
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
