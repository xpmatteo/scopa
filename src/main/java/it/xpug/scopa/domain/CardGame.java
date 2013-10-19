package it.xpug.scopa.domain;

public interface CardGame {
	void startNewGame();
	void play(String card);

	String[] table();
	String[] playerHand();
	int countOfCapturedCards();
	int countOfCardsLeftInTheDeck();
}