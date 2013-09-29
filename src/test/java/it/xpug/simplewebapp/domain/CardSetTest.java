package it.xpug.simplewebapp.domain;

import static org.junit.Assert.*;

import java.util.*;

import it.xpug.simplewebapp.acceptance.*;

import org.junit.*;

public class CardSetTest {

	@Test
	public void equals() {
		CardSet a = new CardSet();
		CardSet b = new CardSet();
		
		assertNotEquals(a, null);
		assertNotEquals(a, "foo");
		assertEquals(a, a);
	}

}
