package it.xpug.scopa.domain;

public enum Suit {
	CUPS("coppe"),
	SWORDS("spade"),
	COINS("denari"),
	WANDS("bastoni");

	private String italianName;

	private Suit(String italianName) {
		this.italianName = italianName;
	}

	public String italianName() {
		return italianName;
	}
}
