package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
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
		assertEquals(2, scopaGame.getCountOfOpponentHand());
	}
	
	@Test
	public void aNewGame_isNotFinished() throws Exception {
		ScopaGame scopaGame = new ScopaGame();
		scopaGame.startNewGame();
		assertFalse("game is not over", scopaGame.isOver());
	}
	
	@Test
	public void whenBothPlayersHaveNoCardsAndDeckIsEmpty_gameIsOver() throws Exception {
		ScopaGame scopaGame = new ScopaGame(emptyDeck(), playerWithEmtpyHand(), new ScopaTable());
		assertTrue("game is over", scopaGame.isOver());
	}

	@Test
	public void ifAPlayerHasACard_gameIsNotOver() throws Exception {
		ScopaGame scopaGame = new ScopaGame(emptyDeck(), playerWithOneCard(), new ScopaTable());
		assertFalse("game is not over", scopaGame.isOver());
	}

	@Test
	public void ifDeckIsNotEmpty_gameIsNotOver() throws Exception {
		ScopaGame scopaGame = new ScopaGame(new Deck(), playerWithEmtpyHand(), new ScopaTable());
		assertFalse("game is not over", scopaGame.isOver());
	}

	private Player playerWithEmtpyHand() {
		return new Player();
	}

	private Player playerWithOneCard() {
		return new Player() {{
			isDealt(anyCard());
		}};
	}

	protected Card anyCard() {
		return swords(1);
	}

	private Deck emptyDeck() {
		return new Deck() {{ 
			clear(); 
		}};
	}
}
