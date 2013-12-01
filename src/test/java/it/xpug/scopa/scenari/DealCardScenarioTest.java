package it.xpug.scopa.scenari;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

import org.junit.*;

public class DealCardScenarioTest {

	ScopaGameApplicationService game = new ScopaGameApplicationService();
	
	@Test
	public void deckNotEmpty() throws Exception {
		givenTheDeckIsNotEmpty();
		whenIPlayMyLastCard();
		thenIAmDealtThreeMoreCards();
	}
	
	private void givenTheDeckIsNotEmpty() {
		game.onStartNewGame();
		assertThat(game.getCountOfCardsLeftInTheDeck(), greaterThan(0));
	}

	private void thenIAmDealtThreeMoreCards() {
		assertThat(game.getPlayerHand().length, is(3));
		assertThat(game.getCountOfCardsLeftInTheDeck(), is(24));
	}

	private void whenIPlayMyLastCard() {
		game.onCardPlayed(game.getPlayerHand()[0]);
		game.onCardPlayed(game.getPlayerHand()[0]);
		game.onCardPlayed(game.getPlayerHand()[0]);
	}

}
