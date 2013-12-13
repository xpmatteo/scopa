package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ScopaGameApplicationServiceTest {

	Deck deck = new Deck();

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
		assertEquals(3, scopaGameApplicationService.getPlayerHand().size());
		assertEquals(3, scopaGameApplicationService.getCountOfOpponentHand());
		assertEquals(4, scopaGameApplicationService.getTable().length);
	}

	@Test
	public void afterHumanPlays_computerResponds() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService();
		scopaGameApplicationService.onStartNewGame();

		scopaGameApplicationService.onCardPlayed(scopaGameApplicationService.getPlayerHand().get(0));

		assertEquals(2, scopaGameApplicationService.getPlayerHand().size());
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
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(Deck.EMPTY, playerWithEmtpyHand(), new ScopaTable());
		assertTrue("game is over", scopaGameApplicationService.isOver());
	}

	@Test
	public void ifAPlayerHasACard_gameIsNotOver() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(Deck.EMPTY, playerWithOneCard(), new ScopaTable());
		assertFalse("game is not over", scopaGameApplicationService.isOver());
	}

	@Test
	public void ifDeckIsNotEmpty_gameIsNotOver() throws Exception {
		ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(new Deck(), playerWithEmtpyHand(), new ScopaTable());
		assertFalse("game is not over", scopaGameApplicationService.isOver());
	}

	@Test
	public void returnsCountOfOpponentCapturedCards() throws Exception {
		ScopaGameApplicationService game = new ScopaGameApplicationService();
		Player player = aPlayerWithCountOfCapturedCardsOf(2);
		game.setComputerPlayer(player);
		assertEquals(2, game.getCountOfOpponentCapturedCards());

	}

	@Test
	public void opponentHasWonWhen_gameIsOver_and_opponentHasMoreCapturedCards() throws Exception {
		ScopaGameApplicationService game = aGameThatComputerHasWon();
		assertTrue(game.isOver());
		assertTrue(game.hasOpponentWon());
	}

	@Test
	public void opponentHasNotWonWhen_gameNotIsOver() throws Exception {
		ScopaGameApplicationService game = new ScopaGameApplicationService(deck);
		Player computerPlayer = aPlayerWithCountOfCapturedCardsOf(2);
		Player humanPlayer = aPlayerWithCountOfCapturedCardsOf(0);
		humanPlayer.isDealt(deck);
		game.setComputerPlayer(computerPlayer);
		game.setHumanPlayer(humanPlayer);
		assertFalse(game.isOver());
		assertFalse(game.hasOpponentWon());
	}

	@Test
	public void whenGameIsInProgress_showPlayACard() throws Exception {
		ScopaGameApplicationService game = aGameInProgress();
		assertEquals("Play a card", game.getStatusMessage());
	}

	@Test
	public void whenComputerPlayerHasWon_showYouHaveLost() throws Exception {
		ScopaGameApplicationService game = aGameThatComputerHasWon();
		assertEquals("You have lost!", game.getStatusMessage());
	}

	@Test
	public void whenHumanPlayerHasWon_showYouHaveWon() throws Exception {
		ScopaGameApplicationService game = aGameThatHumanHasWon();
		assertEquals("You have won!", game.getStatusMessage());
	}

	@Test
	public void whenGameIsOver_andNobodyWon_showItsEven() throws Exception {
		ScopaGameApplicationService game = aGameThatNobodyHasWon();
		assertEquals("Game over; nobody won!", game.getStatusMessage());
	}

	private ScopaGameApplicationService aGameThatHumanHasWon() {
		return aGameOverWithCaptures(18, 22);
	}

	private ScopaGameApplicationService aGameThatNobodyHasWon() {
		return aGameOverWithCaptures(20, 20);
	}

	private ScopaGameApplicationService aGameOverWithCaptures(int countOfComputerCaptures, int countOfHumanCaptures) {
		ScopaGameApplicationService game = new ScopaGameApplicationService(deck);
		Player computerPlayer = aPlayerWithCountOfCapturedCardsOf(countOfComputerCaptures);
		Player humanPlayer = aPlayerWithCountOfCapturedCardsOf(countOfHumanCaptures);
		game.setComputerPlayer(computerPlayer);
		game.setHumanPlayer(humanPlayer);
		return game;
	}

	private ScopaGameApplicationService aGameThatComputerHasWon() {
		return aGameOverWithCaptures(22, 18);
	}



	private ScopaGameApplicationService aGameInProgress() {
		ScopaGameApplicationService game = new ScopaGameApplicationService(deck);
		game.onStartNewGame();
		return game;
	}



	private Player aPlayerWithCountOfCapturedCardsOf(int count) {
		Player player = new Player(new ScopaTable());
		for (int i = 0; i < count; i++) {
			player.capture(deck.dealOneCard());
		}
		return player;
	}

	private Player aPlayer() {
		return new Player(new ScopaTable());
	}

	private Player playerWithEmtpyHand() {
		return aPlayer();
	}

	private Player playerWithOneCard() {
		return new Player(new ScopaTable()) {{
			isDealt(anyCard());
		}};
	}

	protected Card anyCard() {
		return swords(1);
	}
}
