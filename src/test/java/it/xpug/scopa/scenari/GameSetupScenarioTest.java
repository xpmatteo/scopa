package it.xpug.scopa.scenari;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;


import it.xpug.scopa.domain.*;

import org.junit.*;

public class GameSetupScenarioTest {
	Deck deck = new Deck(new Random(1234));
	ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(deck) {{
		onStartNewGame();
	}};

	@Test
	public void theProperNumberOfCardsAreDealt() {
		assertEquals(3, scopaGameApplicationService.getPlayerHand().size());
		assertEquals(3, scopaGameApplicationService.getCountOfOpponentHand());
		assertEquals(4, scopaGameApplicationService.getTable().size());
	}

	@Test
	public void noPlayerHasCapturedCards() {
		assertEquals(0, scopaGameApplicationService.getCountOfPlayerCapturedCards());
		assertEquals(0, scopaGameApplicationService.getCountOfOpponentCapturedCards());
	}

	@Test
	public void cardsAreDealtRandomly() {
		assertThat(scopaGameApplicationService.getPlayerHand().get(0), is("coins-04"));
		assertThat(scopaGameApplicationService.getTable().get(0), is("cups-03"));

		scopaGameApplicationService.onStartNewGame();
		assertThat(scopaGameApplicationService.getPlayerHand().get(0), is("cups-01"));
		assertThat(scopaGameApplicationService.getTable().get(0), is("coins-02"));
	}
}
