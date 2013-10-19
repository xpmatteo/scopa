package it.xpug.scopa.scenari;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardPlayScenarioTest {

	private static final String THREE_OF_WANDS = "wands-03";
	private static final String TWO_OF_WANDS = "wands-02";
	private static final String FIVE_OF_WANDS = "wands-05";
	private static final String FIVE_OF_CUPS = "cups-05";
	private static final String ANOTHER_CARD = "coins-10";

	ScopaGame scopaGame = new ScopaGame();
	
	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsInMyHand(THREE_OF_WANDS, ANOTHER_CARD);
		 givenCardsOnTheTable(TWO_OF_WANDS);
		 whenIPlay(THREE_OF_WANDS);
		 thenMyHandContains(ANOTHER_CARD);
		 thenMyPileContains();
		 thenTheTableContains(THREE_OF_WANDS, TWO_OF_WANDS);
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsInMyHand(FIVE_OF_WANDS, ANOTHER_CARD);
		 givenCardsOnTheTable(FIVE_OF_CUPS);
		 whenIPlay(FIVE_OF_WANDS);
		 thenMyHandContains(ANOTHER_CARD);
		 thenMyPileContains(FIVE_OF_WANDS, FIVE_OF_CUPS);
		 thenTheTableContains();
	}
	
	@Test
	public void theCountOfCapturedCardsIncreasesWhenWeCatpureACard() throws Exception {
		theCountOfCapturedCardsIs(0);
		capturingAMatchingCard();
		theCountOfCapturedCardsIs(2);
	}

	private void theCountOfCapturedCardsIs(int count) {
		assertEquals("count of captured cards", count, scopaGame.countOfCapturedCards());
	}

	private void thenTheTableContains(String ... cards) {
		sort(cards);
		assertArrayEquals(cards, scopaGame.table());
	}

	private void thenMyPileContains(String  ... cards) {
		sort(cards);
		assertArrayEquals(cards, scopaGame.playerCaptures());
	}

	private void thenMyHandContains(String ...cards) {
		sort(cards);
		assertArrayEquals(cards, scopaGame.playerHand());
	}

	private void whenIPlay(String aCard) {
		scopaGame.play(aCard);
	}

	private void givenCardsOnTheTable(String card) {
		scopaGame.addToTable(card);
	}

	private void givenCardsInMyHand(String ... cards) {
		for (String card : cards) {
			scopaGame.addToPlayerHand(card);
		}
	}

}
