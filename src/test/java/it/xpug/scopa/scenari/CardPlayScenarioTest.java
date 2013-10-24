package it.xpug.scopa.scenari;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;
import it.xpug.scopa.scenari.util.*;

import org.junit.*;

public class CardPlayScenarioTest extends CardGameDslTest {

	private static final String THREE_OF_WANDS = "wands-03";
	private static final String TWO_OF_WANDS = "wands-02";
	private static final String FIVE_OF_WANDS = "wands-05";
	private static final String FIVE_OF_CUPS = "cups-05";
	private static final String ANOTHER_CARD = "coins-10";

	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsInMyHand(THREE_OF_WANDS, ANOTHER_CARD);
		 givenCardsOnTheTable(TWO_OF_WANDS);
		 whenIPlay(THREE_OF_WANDS);
		 thenMyHandContains(ANOTHER_CARD);
		 thenMyCapturedCardsAre();
		 thenTheTableContains(THREE_OF_WANDS, TWO_OF_WANDS);
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsInMyHand(FIVE_OF_WANDS, ANOTHER_CARD);
		 givenCardsOnTheTable(FIVE_OF_CUPS);
		 whenIPlay(FIVE_OF_WANDS);
		 thenMyHandContains(ANOTHER_CARD);
		 thenMyCapturedCardsAre(FIVE_OF_WANDS, FIVE_OF_CUPS);
		 thenTheTableContains();
	}
	
	@Test
	public void theCountOfCapturedCardsIncreasesWhenWeCatpureACard() throws Exception {
		theCountOfCapturedCardsIs(0);
		capturingAMatchingCard();
		theCountOfCapturedCardsIs(2);
	}

}
