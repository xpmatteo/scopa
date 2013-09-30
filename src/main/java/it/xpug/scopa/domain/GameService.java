package it.xpug.scopa.domain;

public class GameService {

	private CardSet playerHand = new CardSet();
	private CardSet playerCaptures = new CardSet();
	private CardSet table = new CardSet();
	private Game game = new Game(playerHand, playerCaptures, table);
	
	public void addToPlayerHand(String card) {
		playerHand.add(Card.parse(card));
	}

	public void addToTable(String card) {
		table.add(Card.parse(card));
	}

	public void play(String card) {
		game.play(Card.parse(card));
	}

	public String[] playerHand() {
		return playerHand.toParams();
	}

	public String[] playerCaptures() {
		return playerCaptures.toParams();
	}

	public String[] table() {
		return table.toParams();
	}

}
