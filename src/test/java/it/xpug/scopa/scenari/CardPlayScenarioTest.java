package it.xpug.scopa.scenari;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

import org.junit.*;

public class CardPlayScenarioTest {

	@Test
	public void playingANonMatchingCard() throws Exception {
		 givenCardsOnTheTable(bastoni(2));
		 whenIPlay(bastoni(3));
		 thenTheTableContains(bastoni(2), bastoni(3));
		 thenMyCapturedCardsAre();
	}

	@Test
	public void capturingAMatchingCard() throws Exception {
		 givenCardsOnTheTable(coppe(5));
		 whenIPlay(bastoni(5));
		 thenMyCapturedCardsAre(bastoni(5), coppe(5));
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
