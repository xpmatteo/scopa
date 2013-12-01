package it.xpug.scopa.domain;

public interface CardGameService {
	void onStartNewGame();
	void onCardPlayed(String card);

	String[] getTable();
	String[] getPlayerHand();
	int getCountOfPlayerCapturedCards();
	int getCountOfCardsLeftInTheDeck();
}