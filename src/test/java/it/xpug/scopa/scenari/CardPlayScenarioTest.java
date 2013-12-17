package it.xpug.scopa.scenari;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardPlayScenarioTest {

	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsOnTheTable(wands(2));
		 whenIPlay(wands(3));
		 thenTheTableContains(wands(2), wands(3));
		 thenMyCapturedCardsAre();
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsOnTheTable(cups(5));
		 whenIPlay(wands(5));
		 thenMyCapturedCardsAre(wands(5), cups(5));
		 thenTheTableContains();
	}

	protected ScopaTable table = new ScopaTable();

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
