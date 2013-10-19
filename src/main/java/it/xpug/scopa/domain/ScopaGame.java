package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck;
	private CardSet playerHand;
	private CardSet playerCaptures;
	private CardSet table;
	
	public ScopaGame() {
		this(new Deck());
	}

	public ScopaGame(Deck deck) {
		this.deck = deck;
		clear();
	}

	@Override
	public void startNewGame() {
		clear();
		dealToPlayer();
		dealToTable();
	}

	public void play(String card) {
		play(parse(card));
		if (getPlayerHand().length == 0) {
			dealToPlayer();
		}
	}
	
	public void addToPlayerHand(String card) {
		playerHand.add(parse(card));
	}

	public void addToTable(String card) {
		table.add(parse(card));
	}

	public String[] getPlayerHand() {
		return playerHand.toParams();
	}

	public String[] getPlayerCaptures() {
		return playerCaptures.toParams();
	}

	public String[] getTable() {
		return table.toParams();
	}

	@Override
	public int getCountOfCapturedCards() {
		return playerCaptures.size();
	}

	@Override
	public int getCountOfCardsLeftInTheDeck() {
		return deck.size();
	}

	private void dealToTable() {
		for (int i=0; i < 4; i++)
			table.add(deck.dealOneCard());
	}

	private void dealToPlayer() {
		for (int i=0; i < 3; i++) 
			playerHand.add(deck.dealOneCard());
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
