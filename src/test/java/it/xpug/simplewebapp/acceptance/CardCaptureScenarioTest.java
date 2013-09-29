package it.xpug.simplewebapp.acceptance;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class CardCaptureScenarioTest {

	private static final Card THREE_OF_CLUBS = new Card("3 of clubs");
	private static final Card TWO_OF_CLUBS = new Card("2 of clubs");

	CardSet playerPile = new CardSet();
	CardSet playerHand = new CardSet();
	CardSet table = new CardSet();
	
	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsInMyHand(THREE_OF_CLUBS);
		 givenCardsOnTheTable(TWO_OF_CLUBS);
		 whenIPlay(THREE_OF_CLUBS);
		 thenMyHandContains();
		 thenMyPileContains();
		 thenTheTableContains(THREE_OF_CLUBS, TWO_OF_CLUBS);
	}

	private void thenTheTableContains(Card ... cards) {
		assertEquals("table", new CardSet(cards), table);
	}

	private void thenMyPileContains(Card ... cards) {
		assertEquals("my pile", new CardSet(cards), playerPile);
	}

	private void thenMyHandContains(Card ...cards) {
		assertEquals("my hand", new CardSet(cards), playerHand);
	}

	private void whenIPlay(Card card) {
		table.add(card);
		playerPile.remove(card);
	}

	private void givenCardsOnTheTable(Card card) {
		table.add(card);
	}

	private void givenCardsInMyHand(Card card) {
		playerPile.add(card);
	}

}
