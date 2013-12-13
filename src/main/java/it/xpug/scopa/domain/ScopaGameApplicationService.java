package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static java.util.Arrays.*;

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
		return asList(humanPlayer.showHand());
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
		return computerPlayer.showCaptures().length;
	}

	public int getCountOfOpponentHand() {
		return getOpponentHand().length;
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

	private boolean opponentHasMoreCards() {
		return humanPlayer.showCaptures().length < computerPlayer.showCaptures().length;
	}

	private boolean opponentHasLessCards() {
		return humanPlayer.showCaptures().length > computerPlayer.showCaptures().length;
	}

	private boolean itsOverNobodyWon() {
		return isOver() && humanPlayer.showCaptures().length == computerPlayer.showCaptures().length;
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
}
