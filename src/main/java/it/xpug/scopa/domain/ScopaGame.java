package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck = new Deck();
	private CardSet playerHand;
	private CardSet playerCaptures;
	private CardSet table;
	
	public ScopaGame() {
		clear();
	}

	public void addToPlayerHand(String card) {
		playerHand.add(parse(card));
	}

	public void addToTable(String card) {
		table.add(parse(card));
	}

	public void play(String card) {
		play(parse(card));
		if (playerHand().length == 0) {
			dealToPlayer();
		}
	}
	
	public String[] playerHand() {
		return playerHand.toParams();
	}

	public String[] playerCaptures() {
		return playerCaptures.toParams();
	}

	public String[] table() {
		return table.toParams();
	}

	@Override
	public int countOfCapturedCards() {
		return playerCaptures.size();
	}

	@Override
	public int countOfCardsLeftInTheDeck() {
		return deck.size();
	}

	@Override
	public void startNewGame() {
		clear();
		dealToPlayer();
		dealToTable();
	}

	private void dealToTable() {
		for (int i=0; i < 4; i++)
			table.add(deck.dealOneCard());
	}

	private void dealToPlayer() {
		for (int i=0; i < 3; i++) 
			playerHand.add(deck.dealOneCard());
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	private void clear() {
		playerHand = new CardSet();
		playerCaptures = new CardSet();
		table = new CardSet();
		deck.shuffle();
	}

	private void play(Card playedCard) {
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

}
