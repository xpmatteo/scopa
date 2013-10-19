package it.xpug.scopa.domain;

import org.junit.*;

public class ScopaServiceTest {

	@Test
	public void canRestartTheGameAsManyTimesAsWeWant() {
		ScopaService scopaService = new ScopaService();
		for (int i = 0; i < 10; i++) {
			scopaService.startNewGame();
		}
	}

}
