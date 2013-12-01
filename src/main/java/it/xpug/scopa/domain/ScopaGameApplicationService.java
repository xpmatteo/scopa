package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;


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

	public boolean isOver() {
		return humanPlayer.hasEmptyHand() && deck.isEmpty();
	}
}
