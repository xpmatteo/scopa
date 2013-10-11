package it.xpug.scopa.domain;


public class Game {

	private CardSet playerHand;
	private CardSet playerCaptures;
	private CardSet table;

	public Game(CardSet playerHand, CardSet playerPile, CardSet table) {
		this.playerHand = playerHand;
		this.playerCaptures = playerPile;
		this.table = table;
	}

	public void play(Card playedCard) {
		playerHand.remove(playedCard);
		CardSet allMatching = table.allMatching(playedCard);
		if (allMatching.isEmpty()) {
			table.add(playedCard);
		} else {
			Card matchingCard = allMatching.first();
			table.remove(matchingCard);
			playerCaptures.add(playedCard);
			playerCaptures.add(matchingCard);
		}
	}

	public void addToPlayerHand(Card card) {
		playerHand.add(card);
	}

	public void addToTable(Card card) {
		table.add(card);
	}

	public CardSet playerHand() {
		return playerHand;
	}

	public CardSet playerCaptures() {
		return playerCaptures;
	}

	public CardSet table() {
		return table;
	}
}
