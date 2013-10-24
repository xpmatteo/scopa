package it.xpug.scopa.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class ScopaGameTest {

	@Test
	public void canRestartTheGameAsManyTimesAsWeWant() {
		ScopaGame scopaGame = new ScopaGame();
		for (int i = 0; i < 10; i++) {
			scopaGame.startNewGame();
		}
	}
	
	@Test
	public void whenGameStarts_bothPlayersAndTableAreDealt() throws Exception {
		Deck deck = new Deck();
		ScopaGame scopaGame = new ScopaGame(deck);
		scopaGame.startNewGame();
		assertEquals(3, scopaGame.getPlayerHand().length);
		assertEquals(3, scopaGame.getOpponentHand().length);
		assertEquals(4, scopaGame.getTable().length);
	}

	@Test
	public void afterHumanPlays_computerResponds() throws Exception {
		ScopaGame scopaGame = new ScopaGame();
		scopaGame.startNewGame();
		
		scopaGame.play(scopaGame.getPlayerHand()[0]);
		
		assertEquals(2, scopaGame.getPlayerHand().length);
		assertEquals(2, scopaGame.getOpponentHand().length);		
	}
}
