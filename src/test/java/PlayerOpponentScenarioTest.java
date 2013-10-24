import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.util.*;

import it.xpug.scopa.domain.*;
import it.xpug.scopa.scenari.util.*;

import org.junit.*;


public class PlayerOpponentScenarioTest extends CardGameDslTest {

	@Test
	public void theOpponentPlaysANonMatchingCard() throws Exception {
		game.addToOpponentHand("wands-01", "wands-02", "wands-03");
		
		game.letOpponentPlay();
		
		thenTheTableContains("wands-01");
		thenOpponentHandContains("wands-02", "wands-03");
		thenOpponentCapturedCardsAre();
	}

	private void thenOpponentCapturedCardsAre(String ... cards) {
		assertEquals("opponent captured cards", asList(cards), asList(game.getOpponentCaptures()));
	}

	private void thenOpponentHandContains(String ... cards) {
		assertEquals("opponent hand", asList(cards), asList(game.getOpponentHand()));
	}

}
