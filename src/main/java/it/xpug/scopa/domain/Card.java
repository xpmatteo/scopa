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

	public String toParam() {
		return format("%s-%02d", suit.toString().toLowerCase(), rank);
	}

	public String name() {
		return rankName() + " di " + suit.italianName();
	}

	private String rankName() {
		switch(rank) {
			case  1: return "Asso";
			case  8: return "Fante";
			case  9: return "Cavallo";
			case 10: return "Re";
			default: return Integer.toString(rank);
		}
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

	public static Card wands(int rank) {
		return new Card(rank, Suit.WANDS);
	}

	public static Card swords(int rank) {
		return new Card(rank, Suit.SWORDS);
	}

	public static Card cups(int rank) {
		return new Card(rank, Suit.CUPS);
	}

	public static Card coins(int rank) {
		return new Card(rank, Suit.COINS);
	}
}