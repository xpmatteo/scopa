package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck;
	private Player humanPlayer = new Player();
	private Player computerPlayer = new Player();
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
	
	public void letOpponentPlay() {
		play(computerPlayer.playACard(), computerPlayer);
	}

	public void addToPlayerHand(String card) {
		humanPlayer.isDealt(parse(card));
	}

	public void addToOpponentHand(String ... cards) {
		for (String card : cards) {
			computerPlayer.isDealt(Card.parse(card));
		}
	}

	public void addToTable(String card) {
		table.add(parse(card));
	}

	public String[] getPlayerHand() {
		return humanPlayer.showHand();
	}

	public String[] getPlayerCaptures() {
		return humanPlayer.showCaptures();
	}

	public String[] getOpponentCaptures() {
		return computerPlayer.showCaptures();
	}

	public String[] getOpponentHand() {
		return computerPlayer.showHand();
	}

	public String[] getTable() {
		return table.toParams();
	}

	@Override
	public int getCountOfCapturedCards() {
		return humanPlayer.showCaptures().length;
	}

	@Override
	public int getCountOfCardsLeftInTheDeck() {
		return deck.size();
	}

	private void dealToTable() {
		table.add(4, deck);
	}

	private void dealToPlayer() {
		humanPlayer.isDealt(3, deck);
	}

	private void clear() {
		humanPlayer.reset();		
		computerPlayer.reset();
		table = new CardSet();
		deck.shuffle();
	}

	private void play(Card playedCard) {
		play(playedCard, humanPlayer);
	}

	private void play(Card playedCard, Player player) {
		player.remove(playedCard);
		CardSet allMatching = table.allMatching(playedCard);
		if (allMatching.isEmpty()) {
			table.add(playedCard);
		} else {
			Card matchingCard = allMatching.first();
			table.remove(matchingCard);
			player.capture(playedCard, matchingCard);
		}
	}
}
