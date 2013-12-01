package it.xpug.scopa.domain;

public class ScopaBrain {

	private Player player;

	public ScopaBrain(Player player) {
		this.player = player;
	}

	public void onCardPlayed(Card playedCard, ScopaTable table) {
		player.onCardPlayed(Card.parse(player.showHand()[0]), table);
	}

}
