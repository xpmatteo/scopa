package it.xpug.scopa.domain;


public class Game {

	private CardSet playerHand;
	private CardSet playerPile;
	private CardSet table;

	public Game(CardSet playerHand, CardSet playerPile, CardSet table) {
		this.playerHand = playerHand;
		this.playerPile = playerPile;
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
			playerPile.add(playedCard);
			playerPile.add(matchingCard);
		}
	}

}
