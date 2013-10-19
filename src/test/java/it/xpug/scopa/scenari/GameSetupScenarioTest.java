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
		
		assertEquals(3, scopaGame.getPlayerHand().length);
		assertEquals(4, scopaGame.getTable().length);
	}

	@Test
	public void cardsAreDealtRandomly() {
		Deck deck = new Deck(new Random(1234));
		ScopaGame scopaGame = new ScopaGame(deck);
		
		scopaGame.startNewGame();
		
		assertThat(scopaGame.getPlayerHand()[0], is("coins-04"));
		assertThat(scopaGame.getTable()[0], is("cups-02"));

		scopaGame.startNewGame();		
		assertThat(scopaGame.getPlayerHand()[0], is("coins-05"));
		assertThat(scopaGame.getTable()[0], is("cups-03"));
	}
}
