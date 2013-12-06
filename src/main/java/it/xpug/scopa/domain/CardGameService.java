package it.xpug.scopa.domain;

import java.util.*;

public interface CardGameService {
	void onStartNewGame();
	void onCardPlayed(String card);

	String[] getTable();
	List<String> getPlayerHand();
	int getCountOfPlayerCapturedCards();
	int getCountOfCardsLeftInTheDeck();
}