package it.xpug.scopa.scenari;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;

import org.junit.*;

public class DealCardScenarioTest {

	ScopaGame game = new ScopaGame();
	
	@Test
	public void deckNotEmpty() throws Exception {
		givenTheDeckIsNotEmpty();
		whenIPlayMyLastCard();
		thenIAmDealtThreeMoreCards();
	}
	
	private void givenTheDeckIsNotEmpty() {
		game.startNewGame();
		assertThat(game.countOfCardsLeftInTheDeck(), greaterThan(0));
	}

	private void thenIAmDealtThreeMoreCards() {
		assertThat(game.playerHand().length, is(3));
		assertThat(game.countOfCardsLeftInTheDeck(), is(30));
	}

	private void whenIPlayMyLastCard() {
		game.play(game.playerHand()[0]);
		game.play(game.playerHand()[0]);
		game.play(game.playerHand()[0]);
	}

}
