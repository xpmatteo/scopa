package it.xpug.scopa.scenari;

import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardPlayScenarioTest {

	private static final Card THREE_OF_WANDS = Card.parse("wands-03");
	private static final Card TWO_OF_WANDS = Card.parse("wands-02");
	private static final Card FIVE_OF_WANDS = Card.parse("wands-05");
	private static final Card FIVE_OF_CUPS = Card.parse("cups-05");
	
	
	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsOnTheTable(TWO_OF_WANDS);
		 whenIPlay(THREE_OF_WANDS);
		 thenTheTableContains(TWO_OF_WANDS, THREE_OF_WANDS);
		 thenMyCapturedCardsAre();
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsOnTheTable(FIVE_OF_CUPS);
		 whenIPlay(FIVE_OF_WANDS);
		 thenMyCapturedCardsAre(FIVE_OF_WANDS, FIVE_OF_CUPS);
		 thenTheTableContains();
	}
	

	protected ScopaTable table = new ScopaTable();

	protected void theCountOfCapturedCardsIs(int count) {
		assertEquals("count of captured cards", count, table.capturedCards().size());
	}

	protected void thenTheTableContains(Card ... expected) {
		assertEquals("table", new CardSet(expected), table.cards());
	}

	protected void thenMyCapturedCardsAre(Card ... cards) {
		assertEquals("my captured cards", new CardSet(cards), table.capturedCards());
	}

	protected void whenIPlay(Card aCard) {
		table.play(aCard);
	}

	protected void givenCardsOnTheTable(Card card) {
		table.add(card);
	}
}
