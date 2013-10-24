package it.xpug.scopa.domain;

public class Player {
	private CardSet hand;
	private CardSet captures;
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
	public Card playACard() {
		return hand.first();
	}
}