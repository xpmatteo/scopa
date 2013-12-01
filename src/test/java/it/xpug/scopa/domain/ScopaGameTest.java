package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ScopaGameTest {

	@Test
	public void canRestartTheGameAsManyTimesAsWeWant() {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService();
		for (int i = 0; i < 10; i++) {
			scopaGameApplicationService.onStartNewGame();
		}
	}
	
	@Test
	public void whenGameStarts_bothPlayersAndTableAreDealt() throws Exception {
		Deck deck = new Deck();
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(deck);
		scopaGameApplicationService.onStartNewGame();
		assertEquals(3, scopaGameApplicationService.getPlayerHand().length);
		assertEquals(3, scopaGameApplicationService.getOpponentHand().length);
		assertEquals(4, scopaGameApplicationService.getTable().length);
	}

	@Test
	public void afterHumanPlays_computerResponds() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService();
		scopaGameApplicationService.onStartNewGame();
		
		scopaGameApplicationService.onCardPlayed(scopaGameApplicationService.getPlayerHand()[0]);
		
		assertEquals(2, scopaGameApplicationService.getPlayerHand().length);
		assertEquals(2, scopaGameApplicationService.getOpponentHand().length);
		assertEquals(2, scopaGameApplicationService.getCountOfOpponentHand());
	}
	
	@Test
	public void aNewGame_isNotFinished() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService();
		scopaGameApplicationService.onStartNewGame();
		assertFalse("game is not over", scopaGameApplicationService.isOver());
	}
	
	@Test
	public void whenBothPlayersHaveNoCardsAndDeckIsEmpty_gameIsOver() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(emptyDeck(), playerWithEmtpyHand(), new ScopaTable());
		assertTrue("game is over", scopaGameApplicationService.isOver());
	}

	@Test
	public void ifAPlayerHasACard_gameIsNotOver() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(emptyDeck(), playerWithOneCard(), new ScopaTable());
		assertFalse("game is not over", scopaGameApplicationService.isOver());
	}

	@Test
	public void ifDeckIsNotEmpty_gameIsNotOver() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(new Deck(), playerWithEmtpyHand(), new ScopaTable());
		assertFalse("game is not over", scopaGameApplicationService.isOver());
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
