package it.xpug.scopa.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class DealerTest {
	Deck deck = new Deck();
	ScopaTable table = new ScopaTable();
	Player humanPlayer = new Player(table);
	Player computerPlayer = new Player(table);
	Dealer dealer = new Dealer(deck, humanPlayer, computerPlayer, table);

	@Test
	public void whenPlayerStillHasCards_willNotDealCards() {
		humanPlayer.isDealt(anyCard());
		assertFalse(deck.isEmpty());
		assertEquals(1, humanPlayer.showHand().length);

		dealer.onCardPlayed(anyCard());

		assertEquals(1, humanPlayer.showHand().length);
		assertEquals(0, computerPlayer.showHand().length);
	}


	@Test
	public void whenLastCardIsPlayed_andDeckIsNotEmpty_willDealCards() {
		assertFalse(deck.isEmpty());
		assertTrue(humanPlayer.hasEmptyHand());

		dealer.onCardPlayed(anyCard());

		assertEquals(3, humanPlayer.showHand().length);
		assertEquals(3, computerPlayer.showHand().length);
		assertEquals(0, table.size());
	}


	@Test
	public void whenDeckIsEmpty_willNotDealCards() {
		deck.clear();
		assertTrue(deck.isEmpty());
		assertTrue(humanPlayer.hasEmptyHand());

		// expect no exceptions
		dealer.onCardPlayed(anyCard());
	}

	private Card anyCard() {
		return Card.swords(1);
	}

}
