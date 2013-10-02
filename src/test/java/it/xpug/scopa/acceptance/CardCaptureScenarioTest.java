package it.xpug.scopa.acceptance;

import static org.junit.Assert.*;

import java.util.*;

import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardCaptureScenarioTest {

	private static final String THREE_OF_CLUBS = "3-clubs";
	private static final String TWO_OF_CLUBS = "2-clubs";
	private static final String FIVE_OF_CLUBS = "5-clubs";
	private static final String FIVE_OF_CUPS = "5-cups";

	ScopaService scopaService = new ScopaService();
	
	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsInMyHand(THREE_OF_CLUBS);
		 givenCardsOnTheTable(TWO_OF_CLUBS);
		 whenIPlay(THREE_OF_CLUBS);
		 thenMyHandContains();
		 thenMyPileContains();
		 thenTheTableContains(THREE_OF_CLUBS, TWO_OF_CLUBS);
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsInMyHand(FIVE_OF_CLUBS);
		 givenCardsOnTheTable(FIVE_OF_CUPS);
		 whenIPlay(FIVE_OF_CLUBS);
		 thenMyHandContains();
		 thenMyPileContains(FIVE_OF_CLUBS, FIVE_OF_CUPS);
		 thenTheTableContains();
	}
	
	private void thenTheTableContains(String ... cards) {
		Arrays.sort(cards);
		assertArrayEquals(cards, scopaService.table());
	}

	private void thenMyPileContains(String  ... cards) {
		Arrays.sort(cards);
		assertArrayEquals(cards, scopaService.playerCaptures());
	}

	private void thenMyHandContains(Card ...cards) {
		Arrays.sort(cards);
		assertArrayEquals(cards, scopaService.playerHand());
	}

	private void whenIPlay(String aCard) {
		scopaService.play(aCard);
	}

	private void givenCardsOnTheTable(String card) {
		scopaService.addToTable(card);
	}

	private void givenCardsInMyHand(String card) {
		scopaService.addToPlayerHand(card);
	}

}
