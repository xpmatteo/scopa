package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static it.xpug.scopa.domain.CardSet.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ChoosingCaptureTest {
	ScopaTable table = new ScopaTable(swords(1), swords(2), cups(1));

	@Test
	public void noOptions() {
		assertEquals(setOf(), table.allMatching(cups(9)));
	}

	@Test
	public void oneOption() {
		assertEquals(setOf(swords(2)), table.allMatching(cups(2)));
	}

	@Test
	public void twoOptions() {
		assertEquals(setOf(swords(1), cups(1)), table.allMatching(wands(1)));
	}

	@Test
	public void chooseTheFirstOption() throws Exception {
		table.capture(wands(1), 0);
		assertEquals(setOf(wands(1), swords(1)), table.capturedCards());
	}

	@Test
	public void chooseTheSecondOption() throws Exception {
		table.capture(wands(1), 1);
		assertEquals(setOf(wands(1), cups(1)), table.capturedCards());
	}

}
