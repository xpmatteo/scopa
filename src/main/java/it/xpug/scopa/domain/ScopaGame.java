package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck;
	private Player humanPlayer = new Player();
	private CardSet table;
	private CardSet opponentHand;
	private CardSet opponentCaptures;
	
	public static class Player {
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
	}
	
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
		humanPlayer.isDealt(parse(card));
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
		return humanPlayer.showHand();
	}

	public String[] getPlayerCaptures() {
		return humanPlayer.showCaptures();
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
		
		opponentHand = new CardSet();
		opponentCaptures = new CardSet();
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
