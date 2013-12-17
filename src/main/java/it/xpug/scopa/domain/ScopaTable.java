package it.xpug.scopa.domain;



public class ScopaTable extends CardSet {

	private CardSet capturedCards;

	public ScopaTable(Card ... cards) {
		super(cards);
	}

	public CardSet allMatching(Card card) {
		CardSet result = new CardSet();
		for (Card candidate : this.cards) {
			if (candidate.matches(card)) {
				result.add(candidate);
			}
		}
		return result;
	}

	public void play(Card playedCard) {
		CardSet allMatching = this.allMatching(playedCard);
		if (allMatching.isEmpty()) {
			this.add(playedCard);
			capturedCards = new CardSet();
		} else {
			capture(playedCard, 0);
		}
	}

	public void capture(Card playedCard, int matchIndex) {
		CardSet allMatching = this.allMatching(playedCard);
		Card matchingCard = allMatching.get(matchIndex);
		this.remove(matchingCard);
		capturedCards = new CardSet(playedCard, matchingCard);
	}

	public CardSet capturedCards() {
		return capturedCards;
	}

	public CardSet cards() {
		return new CardSet(cards);
	}
}
