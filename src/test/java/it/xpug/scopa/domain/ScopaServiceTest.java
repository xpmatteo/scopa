package it.xpug.scopa.domain;

import org.junit.*;

public class ScopaServiceTest {

	@Test
	public void canRestartTheGameAsManyTimesAsWeWant() {
		ScopaGame scopaGame = new ScopaGame();
		for (int i = 0; i < 10; i++) {
			scopaGame.startNewGame();
		}
	}

}
