package it.xpug.scopa.scenari.util;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

public class CardGameDslTest {

	protected ScopaGame game = new ScopaGame();

	protected void theCountOfCapturedCardsIs(int count) {
		assertEquals("count of captured cards", count, game.getCountOfCapturedCards());
	}

	protected void thenTheTableContains(String ... cards) {
		sort(cards);
		assertEquals("table", asList(cards), asList(game.getTable()));
	}

	protected void thenMyCapturedCardsAre(String  ... cards) {
		sort(cards);
		assertEquals("my captured cards", asList(cards), asList(game.getPlayerCaptures()));
	}

	protected void thenMyHandContains(String ...cards) {
		sort(cards);
		assertEquals("my hand", asList(cards), asList(game.getPlayerHand()));
	}

	protected void whenIPlay(String aCard) {
		game.play(aCard);
	}

	protected void givenCardsOnTheTable(String card) {
		game.addToTable(card);
	}

	protected void givenCardsInMyHand(String ... cards) {
		for (String card : cards) {
			game.addToPlayerHand(card);
		}
	}

}
