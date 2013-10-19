package it.xpug.scopa.domain;

public interface CardGame {
	void startNewGame();
	void play(String card);

	String[] getTable();
	String[] getPlayerHand();
	int getCountOfCapturedCards();
	int getCountOfCardsLeftInTheDeck();
}