package it.xpug.scopa.scenari;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;


import it.xpug.scopa.domain.*;

import org.junit.*;

public class GameSetupScenarioTest {

	@Test
	public void theProperNumberOfCardsAreDealt() {
		ScopaService scopaService = new ScopaService();
		
		scopaService.startNewGame();
		
		assertEquals(3, scopaService.playerHand().length);
		assertEquals(4, scopaService.table().length);
	}

	@Test
	public void cardsAreDealtRandomly() {
		ScopaService scopaService = new ScopaService();
		Deck deck = new Deck(new Random(1234));
		scopaService.setDeck(deck);
		
		scopaService.startNewGame();
		
		assertThat(scopaService.playerHand()[0], is("coins-04"));
		assertThat(scopaService.table()[0], is("cups-02"));

		scopaService.startNewGame();		
		assertThat(scopaService.playerHand()[0], is("coins-05"));
		assertThat(scopaService.table()[0], is("cups-03"));
	}
}