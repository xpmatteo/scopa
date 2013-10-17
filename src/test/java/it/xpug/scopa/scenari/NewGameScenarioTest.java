package it.xpug.scopa.scenari;

import static org.junit.Assert.*;
import it.xpug.scopa.scenari.util.*;

import java.util.*;

import org.junit.*;
import org.openqa.selenium.*;

public class NewGameScenarioTest extends GuiTest {

	@Test
	public void whenAGameIsInProgres_ICanCreateANewGame() throws Exception {
		givenAGameIsInProgress();
		createANewGame();
		aNewGameIsCreated();
	}

	private List<String> cardsFromTheOldGame;	
	
	private void givenAGameIsInProgress() {
		driver.get("http://localhost:" + APPLICATION_PORT +"/game/123");
		this.cardsFromTheOldGame = names(cardsOnTheTable());
	}

	private void createANewGame() {
		driver.findElement(By.id("new-game-command")).click();
	}

	private void aNewGameIsCreated() {
		List<String> cardsFromTheCurrentGame = names(cardsOnTheTable());
		assertEquals(4, cardsFromTheCurrentGame.size());
		assertNotEquals("cards on the table not changed", cardsFromTheOldGame, cardsFromTheCurrentGame);
	}

	private List<String> names(List<WebElement> cards) {
		List<String> result = new ArrayList<String>();
		for (WebElement card : cards) {
			result.add(card.findElement(By.tagName("img")).getAttribute("title"));
		}
		return result;
	}
	
	private List<WebElement> cardsOnTheTable() {
		return driver.findElements(By.cssSelector("#table .card"));
	}
}
