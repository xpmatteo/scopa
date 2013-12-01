package it.xpug.scopa.domain;

public class ScopaBrain {

	private Player player;
	private ScopaTable table;

	public ScopaBrain(ScopaTable table, Player player) {
		this.table = table;
		this.player = player;
	}

	public void onCardPlayed(Card playedCard) {
		player.onCardPlayed(Card.parse(player.showHand()[0]));
	}

}
