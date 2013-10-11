package it.xpug.scopa.scenari;

import static org.junit.Assert.*;

import java.util.*;

import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardCaptureScenarioTest {

	private static final String THREE_OF_WANDS = "wands-03";
	private static final String TWO_OF_WANDS = "wands-02";
	private static final String FIVE_OF_WANDS = "wands-05";
	private static final String FIVE_OF_CUPS = "cups-05";

	ScopaService scopaService = new ScopaService();
	
	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsInMyHand(THREE_OF_WANDS);
		 givenCardsOnTheTable(TWO_OF_WANDS);
		 whenIPlay(THREE_OF_WANDS);
		 thenMyHandContains();
		 thenMyPileContains();
		 thenTheTableContains(THREE_OF_WANDS, TWO_OF_WANDS);
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsInMyHand(FIVE_OF_WANDS);
		 givenCardsOnTheTable(FIVE_OF_CUPS);
		 whenIPlay(FIVE_OF_WANDS);
		 thenMyHandContains();
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
		assertEquals("count of captured cards", count, scopaService.countOfCapturedCards());
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
