package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;

import java.util.*;


public class ScopaGameApplicationService implements CardGameService {

	private Deck deck;
	private Player humanPlayer;
	private Player computerPlayer;
	private ScopaBrain scopaBrain;
	private ScopaTable table;
	private Dealer dealer;

	public ScopaGameApplicationService() {
		this(new Deck());
	}

	public ScopaGameApplicationService(Deck deck) {
		this(deck, new ScopaTable());
	}

	public ScopaGameApplicationService(Deck deck, ScopaTable table) {
		this(deck, new Player(table), table);
	}

	public ScopaGameApplicationService(Deck deck, Player humanPlayer, ScopaTable table) {
		this.deck = deck;
		this.table = table;
		this.humanPlayer = humanPlayer;
		this.computerPlayer = new Player(table);
		this.scopaBrain = new ScopaBrain(table, computerPlayer);
		this.dealer = new Dealer(deck, humanPlayer, computerPlayer, table);
	}

	@Override
	public void onStartNewGame() {
		humanPlayer.reset();
		computerPlayer.reset();
		table.clear();
		deck.shuffle();
		dealer.onStartNewGame();
	}

	@Override
	public void onCardPlayed(String card) {
		Card playedCard = parse(card);
		humanPlayer.onCardPlayed(playedCard);
		scopaBrain.onCardPlayed(playedCard);
		dealer.onCardPlayed(playedCard);
	}

	public List<String> getPlayerHand() {
		return humanPlayer.showHand();
	}

	public List<String> getPlayerCaptures() {
		return humanPlayer.showCaptures();
	}

	public List<String> getOpponentCaptures() {
		return computerPlayer.showCaptures();
	}

	public List<String> getOpponentHand() {
		return computerPlayer.showHand();
	}

	public List<String> getTable() {
		return table.toParams();
	}

	@Override
	public int getCountOfPlayerCapturedCards() {
		return humanPlayer.showCaptures().size();
	}

	@Override
	public int getCountOfCardsLeftInTheDeck() {
		return deck.size();
	}

	public int getCountOfOpponentCapturedCards() {
		return computerPlayer.showCaptures().size();
	}

	public int getCountOfOpponentHand() {
		return getOpponentHand().size();
	}

	public void setComputerPlayer(Player player) {
		computerPlayer = player;
	}

	public void setHumanPlayer(Player humanPlayer) {
		this.humanPlayer = humanPlayer;
	}

	public boolean isOver() {
		return humanPlayer.hasEmptyHand() && deck.isEmpty();
	}

	public boolean hasOpponentWon() {
		return isOver() && opponentHasMoreCards();
	}

	public boolean hasHumanWon() {
		return isOver() && opponentHasLessCards();
	}

	public String getStatusMessage() {
		if (hasHumanWon()) {
			return "You have won!";
		}
		if (hasOpponentWon()) {
			return "You have lost!";
		}
		if (itsOverNobodyWon()) {
			return "Game over; nobody won!";
		}
		return "Play a card";
	}

	private boolean opponentHasMoreCards() {
		return humanPlayer.showCaptures().size() < computerPlayer.showCaptures().size();
	}

	private boolean opponentHasLessCards() {
		return humanPlayer.showCaptures().size() > computerPlayer.showCaptures().size();
	}

	private boolean itsOverNobodyWon() {
		return isOver() && humanPlayer.showCaptures().size() == computerPlayer.showCaptures().size();
	}
}
