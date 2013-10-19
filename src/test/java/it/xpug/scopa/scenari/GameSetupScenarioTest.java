package it.xpug.scopa.scenari;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;


import it.xpug.scopa.domain.*;

import org.junit.*;

public class GameSetupScenarioTest {

	@Test
	public void theProperNumberOfCardsAreDealt() {
		ScopaGame scopaGame = new ScopaGame();
		
		scopaGame.startNewGame();
		
		assertEquals(3, scopaGame.playerHand().length);
		assertEquals(4, scopaGame.table().length);
	}

	@Test
	public void cardsAreDealtRandomly() {
		ScopaGame scopaGame = new ScopaGame();
		Deck deck = new Deck(new Random(1234));
		scopaGame.setDeck(deck);
		
		scopaGame.startNewGame();
		
		assertThat(scopaGame.playerHand()[0], is("coins-04"));
		assertThat(scopaGame.table()[0], is("cups-02"));

		scopaGame.startNewGame();		
		assertThat(scopaGame.playerHand()[0], is("coins-05"));
		assertThat(scopaGame.table()[0], is("cups-03"));
	}
}
