package it.xpug.simplewebapp.acceptance;

public class Card {
	private String name;

	public Card(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}