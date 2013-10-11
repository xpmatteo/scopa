package it.xpug.scopa.domain;


public class ScopaService implements CardGameService {

	private Game game;
	private Deck deck = new Deck();
	
	public ScopaService() {
		CardSet playerHand = new CardSet();
		CardSet playerCaptures = new CardSet();
		CardSet table = new CardSet();
		game = new Game(playerHand, playerCaptures, table);
	}

	public void addToPlayerHand(String card) {
		game.addToPlayerHand(Card.parse(card));
	}

	public void addToTable(String card) {
		game.addToTable(Card.parse(card));
	}

	public void play(String card) {
		game.play(Card.parse(card));
	}

	public String[] playerHand() {
		return game.playerHand().toParams();
	}

	public String[] playerCaptures() {
		return game.playerCaptures().toParams();
	}

	public String[] table() {
		return game.table().toParams();
	}

	@Override
	public int countOfCapturedCards() {
		return game.playerCaptures().size();
	}

	@Override
	public void startNewGame() {
		for (int i=0; i < 3; i++) 
			this.game.addToPlayerHand(deck.dealOneCard());
		for (int i=0; i < 4; i++) 
			this.game.addToTable(deck.dealOneCard());
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}
