package it.xpug.scopa.domain;



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
		int rank = Integer.parseInt(split[0]);
		Suit suit = Suit.valueOf(split[1].toUpperCase());
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
		return "" + rank + "-" + suit.toString().toLowerCase();
	}
}