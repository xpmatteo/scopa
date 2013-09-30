package it.xpug.scopa.domain;

import java.util.*;


public class CardSet {

	private Set<Card> cards = new HashSet<Card>();

	public CardSet(Card ...cards) {
		this.cards.addAll(Arrays.asList(cards));
	}

	public CardSet(String[] cards) {
		for (String card : cards) {
			this.cards.add(Card.parse(card));
		}
	}

	public void add(Card card) {
		cards.add(card);
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

	public CardSet allMatching(Card card) {
		CardSet result = new CardSet();
		for (Card candidate : this.cards) {
			if (candidate.matches(card)) {
				result.add(candidate);
			}
		}
		return result;
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

	
}
