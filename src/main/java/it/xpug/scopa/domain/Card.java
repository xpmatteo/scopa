package it.xpug.scopa.domain;

import static java.lang.String.*;



public class Card {
	private String name;
	private int rank;
	private Suit suit;

	public Card(int rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.name = "" + rank + " of " + suit.toString().toLowerCase();
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean matches(Card other) {
		return this.rank == other.rank;
	}

	public static Card parse(String string) {
		String[] split = string.split("-");
		Suit suit = Suit.valueOf(split[0].toUpperCase());
		int rank = Integer.parseInt(split[1]);
		return new Card(rank, suit);
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Card)) 
			return false;
		return this.toString().equals(arg0.toString());
	}	
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	public String toParam() {
		return format("%s-%02d", suit.toString().toLowerCase(), rank);
	}

	public static Card wands(int rank) {
		return new Card(rank, Suit.WANDS);
	}

	public static Card swords(int rank) {
		return new Card(rank, Suit.SWORDS);
	}
}