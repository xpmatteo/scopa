package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


public class ScopaGame implements CardGame {

	private Deck deck;
	private Player humanPlayer;
	private Player computerPlayer = new Player();
	private ScopaTable table;
	
	public ScopaGame() {
		this(new Deck());
	}

	public ScopaGame(Deck deck) {
		this(deck, new Player(), new ScopaTable());
	}

	public ScopaGame(Deck deck, Player humanPlayer, ScopaTable table) {
		this.deck = deck;
		this.humanPlayer = humanPlayer;
		this.table = table;
	}

	@Override
	public void startNewGame() {
		clear();
		dealToPlayers();
		dealToTable();
	}

	public void play(String card) {
		play(parse(card));
		letOpponentPlay();
		if (getPlayerHand().length == 0) {
			dealToPlayers();
		}
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
	public int getCountOfPlayerCapturedCards() {
		return humanPlayer.showCaptures().length;
	}

	@Override
	public int getCountOfCardsLeftInTheDeck() {
		return deck.size();
	}

	public int getCountOfOpponentCapturedCards() {
		return 0;
	}

	public int getCountOfOpponentHand() {
		return getOpponentHand().length;
	}

	private void dealToTable() {
		table.add(4, deck);
	}

	private void dealToPlayers() {
		humanPlayer.isDealt(3, deck);
		computerPlayer.isDealt(3, deck);
	}

	private void clear() {
		humanPlayer.reset();		
		computerPlayer.reset();
		table.clear();
		deck.shuffle();
	}

	private void play(Card playedCard) {
		play(playedCard, humanPlayer);
	}

	private void play(Card playedCard, Player player) {
		player.remove(playedCard);
		table.play(playedCard);
		player.capture(table.capturedCards());
	}

	private void letOpponentPlay() {
		play(computerPlayer.playACard(), computerPlayer);
	}

	public boolean isOver() {
		return humanPlayer.hasEmptyHand() && deck.isEmpty();
	}
}
