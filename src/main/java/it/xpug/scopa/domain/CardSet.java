package it.xpug.scopa.domain;

import static java.util.Arrays.*;

import java.util.*;


public class CardSet {

	protected List<Card> cards = new ArrayList<Card>();

	public CardSet(Card ... cards) {
		this.cards.addAll(asList(cards));
	}

	public CardSet(String[] cards) {
		for (String card : cards) {
			this.cards.add(Card.parse(card));
		}
	}

	public CardSet(List<Card> cards) {
		this(cards.toArray(new Card[0]));
	}

	public void add(Card ... cards) {
		this.cards.addAll(asList(cards));
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}
	
	public void add(CardSet others) {
		this.cards.addAll(others.cards);
	}

	public void add(int count, Deck deck) {
		for (int i=0; i < count; i++)
			this.add(deck.dealOneCard());
	}

	public void remove(Card card) {
		cards.remove(card);
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}

	@Override
	public int hashCode() {
		return cards.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CardSet))
			return false;
		CardSet other = (CardSet) obj;
		return cards.equals(other.cards);
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card first() {
		return cards.iterator().next();
	}

	public String[] toParams() {
		List<String> list = new ArrayList<String>();
		for (Card card : cards) {
			list.add(card.toParam());
		}
		Collections.sort(list);
		return list.toArray(new String[0]);
	}

	public int size() {
		return this.cards.size();
	}

	public void clear() {
		cards.clear();
	}

	
}
