package it.xpug.scopa.domain;

public class Dealer {

	private Deck deck;
	private Player humanPlayer;
	private Player computerPlayer;
	private ScopaTable table;

	public Dealer(Deck deck, Player humanPlayer, Player computerPlayer, ScopaTable table) {
		this.deck = deck;
		this.humanPlayer = humanPlayer;
		this.computerPlayer = computerPlayer;
		this.table = table;
	}

	public void onStartNewGame() {
		dealToPlayers();
		dealToTable();
	}

	public void onCardPlayed(Card playedCard) {
		if (humanPlayer.hasEmptyHand() && !deck.isEmpty()) {
			dealToPlayers();
		}
	}

	private void dealToPlayers() {
		humanPlayer.isDealt(3, deck);
		computerPlayer.isDealt(3, deck);
	}

	private void dealToTable() {
		table.add(4, deck);
	}
}
