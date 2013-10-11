package it.xpug.scopa.domain;

import java.util.*;

public class Deck {
	private final Random random;
	private List<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		this(new Random());
	}
	
	public Deck(Random random) {
		this.random = random;
		for (Suit suit : Suit.values()) {
			for (int rank=1; rank <= 10; rank++) {
				cards.add(new Card(rank, suit));
			}
		}
	}
	
	public int size() {
		return cards.size();
	}

	public Card dealOneCard() {
		return cards.remove(random.nextInt(cards.size()));
	}

}
