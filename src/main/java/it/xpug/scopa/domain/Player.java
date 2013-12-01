package it.xpug.scopa.domain;

import java.util.*;

public class Player {
	private CardSet hand = new CardSet();
	private CardSet captures = new CardSet();

	public void isDealt(Card card) {
		hand.add(card);
	}
	public void isDealt(int count, Deck deck) {
		hand.add(count, deck);
	}
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
	public Card playACard() {
		return hand.first();
	}
	public boolean hasEmptyHand() {
		return hand.isEmpty();
	}
}