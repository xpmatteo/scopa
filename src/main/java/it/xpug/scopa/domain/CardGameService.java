package it.xpug.scopa.domain;

public interface CardGameService {
	void startNewGame();
	void play(String card);

	String[] table();
	String[] playerHand();
	int countOfCapturedCards();
}