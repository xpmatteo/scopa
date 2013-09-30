package it.xpug.scopa.domain;

import it.xpug.scopa.acceptance.*;

public class Card {
	private String name;
	private int rank;

	public Card(int rank, Suit suit) {
		this.rank = rank;
		this.name = "" + rank + " of " + suit.toString().toLowerCase();
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean matches(Card other) {
		return this.rank == other.rank;
	}
}