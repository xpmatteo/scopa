package it.xpug.scopa.domain;

import java.util.*;

public class Player {
	protected CardSet hand = new CardSet();
	private CardSet captures = new CardSet();
	private ScopaTable table;

	public Player(ScopaTable table) {
		this.table = table;
	}

	public void isDealt(Card card) {
		hand.add(card);
	}
	public void isDealt(int count, Deck deck) {
		hand.add(count, deck);
	}

	// REFACTOR should return cards not params
	public String[] showHand() {
		return hand.toParams();
	}
	public void reset() {
		hand = new CardSet();
		captures = new CardSet();
	}
	public void remove(Card playedCard) {
		hand.remove(playedCard);
	}
	public String[] showCaptures() {
		return captures.toParams();
	}

	public void capture(Card ... cards) {
		captures.add(cards);
	}

	public void capture(CardSet cards) {
		captures.add(cards);
	}
	public void capture(List<Card> capturedCards) {
		captures.add(capturedCards);
	}
	public boolean hasEmptyHand() {
		return hand.isEmpty();
	}

	public void onCardPlayed(Card playedCard) {
		remove(playedCard);
		table.play(playedCard);
		capture(table.capturedCards());
	}

}