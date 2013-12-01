package it.xpug.scopa.domain;

import java.util.*;

public class Deck {
	public static final Deck EMPTY = new Deck() {{ clear(); }};

	private final Random random;
	private List<Card> cards = new ArrayList<Card>();

	public Deck() {
		this(new Random());
	}

	public Deck(Random random) {
		this.random = random;
		shuffle();
	}

	public void clear() {
		cards.clear();
	}

	public void shuffle() {
		cards.clear();
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
		if (cards.isEmpty())
			throw new DeckIsEmptyException();
		return cards.remove(random.nextInt(cards.size()));
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public class DeckIsEmptyException extends RuntimeException {}
}
