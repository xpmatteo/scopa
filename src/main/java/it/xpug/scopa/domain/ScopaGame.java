package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck;
	private CardSet playerHand;
	private CardSet playerCaptures;
	private CardSet table;
	private CardSet opponentHand;
	private CardSet opponentCaptures;
	
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
	
	public void letOpponentPlay() {
		Card cardThatOpponentChoosesToPlay = opponentHand.first();
		play(cardThatOpponentChoosesToPlay, opponentHand, opponentCaptures);
	}

	public void addToPlayerHand(String card) {
		playerHand.add(parse(card));
	}

	public void addToOpponentHand(String ... cards) {
		for (String card : cards) {
			opponentHand.add(Card.parse(card));
		}
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

	public String[] getOpponentCaptures() {
		return opponentCaptures.toParams();
	}

	public String[] getOpponentHand() {
		return opponentHand.toParams();
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
		table.addMany(4, deck);
	}

	private void dealToPlayer() {
		playerHand.addMany(3, deck);
	}

	private void clear() {
		playerHand = new CardSet();
		opponentHand = new CardSet();
		playerCaptures = new CardSet();
		opponentCaptures = new CardSet();
		table = new CardSet();
		deck.shuffle();
	}

	private void play(Card playedCard) {
		play(playedCard, playerHand, playerCaptures);
	}

	private void play(Card playedCard, CardSet hand, CardSet captures) {
		hand.remove(playedCard);
		CardSet allMatching = table.allMatching(playedCard);
		if (allMatching.isEmpty()) {
			table.add(playedCard);
		} else {
			Card matchingCard = allMatching.first();
			table.remove(matchingCard);
			captures.add(playedCard);
			captures.add(matchingCard);
		}
	}

}
