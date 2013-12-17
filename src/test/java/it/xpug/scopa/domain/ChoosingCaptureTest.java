package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ChoosingCaptureTest {

	@Test
	public void noOptions() {
		ScopaTable table = new ScopaTable(swords(1), swords(2), cups(1));
		assertEquals(setOf(), table.allMatching(cups(9)));
	}

	@Test
	public void oneOption() {
		ScopaTable table = new ScopaTable(swords(1), swords(2), cups(1));
		assertEquals(setOf(swords(2)), table.allMatching(cups(2)));
	}

	@Test
	public void twoOptions() {
		ScopaTable table = new ScopaTable(swords(1), cups(1));
		assertEquals(setOf(swords(1), cups(1)), table.allMatching(wands(1)));
	}

	@Test
	public void chooseTheFirstOption() throws Exception {
		ScopaTable table = new ScopaTable(swords(1), cups(1));
		table.capture(wands(1), 0);
		assertEquals(setOf(wands(1), swords(1)), table.capturedCards());
	}

	@Test
	public void chooseTheSecondOption() throws Exception {
		ScopaTable table = new ScopaTable(swords(1), cups(1));
		table.capture(wands(1), 1);
		assertEquals(setOf(wands(1), cups(1)), table.capturedCards());
	}

	private CardSet setOf(Card ... cards) {
		return new CardSet(cards);
	}

}
