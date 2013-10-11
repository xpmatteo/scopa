package it.xpug.scopa.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.*;
import org.junit.*;

public class DeckTest extends CardSetTest {
	Deck deck = new Deck();

	@Test
	public void theDeckHas40Cards() {
		assertThat(deck.size(), is(40));
	}
	
	@Test
	public void whenIDealACardTheDeckHasOneLessCard() throws Exception {
		deck.dealOneCard();
		assertThat(deck.size(), is(39));
	}
	
	@Test
	public void cardsAreDealtRandomly() throws Exception {		
		Deck oneDeck = new Deck(new Random(100));
		assertThat(oneDeck.dealOneCard(), isCard("6 of wands"));
		assertThat(oneDeck.dealOneCard(), isCard("8 of cups"));
		
		Deck anotherDeck = new Deck(new Random(875876354));
		assertThat(anotherDeck.dealOneCard(), isCard("8 of wands"));
		assertThat(anotherDeck.dealOneCard(), isCard("7 of wands"));
	}
	
	private Matcher<? super Card> isCard(final String cardDescription) {
		return new TypeSafeMatcher<Card>() {
			@Override
			public void describeTo(Description description) {
				description.appendText(cardDescription);
			}

			@Override
			protected boolean matchesSafely(Card actual) {
				return actual.toString().equals(cardDescription);
			}
		};
	}

}
