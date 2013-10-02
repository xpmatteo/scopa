package it.xpug.scopa.domain;

public class ScopaService implements GameService {

	private CardSet playerHand = new CardSet();
	private CardSet playerCaptures = new CardSet();
	private CardSet table = new CardSet();
	private Game game = new Game(playerHand, playerCaptures, table);
	
	public ScopaService(CardSet table, CardSet playerHand) {
		this.table = table;
		this.playerHand = playerHand;
		this.game = new Game(playerHand, playerCaptures, table);
	}

	public ScopaService() {
		playerHand = new CardSet();
		playerCaptures = new CardSet();
		table = new CardSet();
		game = new Game(playerHand, playerCaptures, table);
	}

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
