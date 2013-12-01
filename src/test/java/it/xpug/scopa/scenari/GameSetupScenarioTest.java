package it.xpug.scopa.scenari;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;


import it.xpug.scopa.domain.*;

import org.junit.*;

public class GameSetupScenarioTest {
	Deck deck = new Deck(new Random(1234));
	ScopaGame scopaGame = new ScopaGame(deck) {{
		startNewGame();
	}};

	@Test
	public void theProperNumberOfCardsAreDealt() {
		assertEquals(3, scopaGame.getPlayerHand().length);
		assertEquals(3, scopaGame.getCountOfOpponentHand());
		assertEquals(4, scopaGame.getTable().length);
	}

	@Test
	public void noPlayerHasCapturedCards() {
		assertEquals(0, scopaGame.getCountOfPlayerCapturedCards());
		assertEquals(0, scopaGame.getCountOfOpponentCapturedCards());		
	}

	@Test
	public void cardsAreDealtRandomly() {
		assertThat(scopaGame.getPlayerHand()[0], is("coins-04"));
		assertThat(scopaGame.getTable()[0], is("cups-03"));

		scopaGame.startNewGame();		
		assertThat(scopaGame.getPlayerHand()[0], is("cups-01"));
		assertThat(scopaGame.getTable()[0], is("coins-02"));
	}
}
