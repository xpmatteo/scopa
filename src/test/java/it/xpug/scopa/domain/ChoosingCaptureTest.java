package it.xpug.scopa.domain;

import static it.xpug.scopa.domain.Card.*;
import static it.xpug.scopa.domain.CardSet.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ChoosingCaptureTest {
	ScopaTable table = new ScopaTable(swords(1), swords(2), coppe(1));

	@Test
	public void noOptions() {
		assertEquals(setOf(), table.allMatching(coppe(9)));
	}

	@Test
	public void oneOption() {
		assertEquals(setOf(swords(2)), table.allMatching(coppe(2)));
	}

	@Test
	public void twoOptions() {
		assertEquals(setOf(swords(1), coppe(1)), table.allMatching(bastoni(1)));
	}

	@Test
	public void chooseTheFirstOption() throws Exception {
		table.capture(bastoni(1), 0);
		assertEquals(setOf(bastoni(1), swords(1)), table.capturedCards());
	}

	@Test
	public void chooseTheSecondOption() throws Exception {
		table.capture(bastoni(1), 1);
		assertEquals(setOf(bastoni(1), coppe(1)), table.capturedCards());
	}

}
