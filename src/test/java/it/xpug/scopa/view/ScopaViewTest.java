package it.xpug.scopa.view;

import static it.xpug.scopa.infrastructure.XmlFragment.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;
import it.xpug.scopa.infrastructure.*;

import org.hamcrest.*;
import org.junit.*;

public class ScopaViewTest {
	private CardSet table = new CardSet();
	private CardSet playerHand = new CardSet();
	private GameService gameService = new GameService(table, playerHand);
	private ScopaView view = new ScopaView(gameService);

	@Test
	public void showsCardsFaceupOnTable() throws Exception {
		Card card = new Card(1, Suit.CLUBS);
		table.add(card);
		assertNodeIs("//div[@id='table']//li[@class='card']", card.toParam());
	}

	@Test
	public void showsButtonsForYourHand() throws Exception {
		Card card = new Card(3, Suit.SWORDS);
		playerHand.add(card);
		XmlFragment page = new XmlFragment(view.toHtml());
		XmlFragment playerHand = page.getNode("//div[@id='playerHand']");
		// hidden field
		playerHand.getNode(
				"//form/input[@type='hidden' and @value='3-swords']");
		// button
		playerHand.getNode(
				"//form/input[@type='button' and @value='3-swords']");
	}
	
	@Test
	public void hasProperHtmlStructure() throws Exception {
		XmlFragment xml = new XmlFragment(view.toHtml());
		assertThat(xml, matches("/html/head/title"));
		assertThat(xml, matches("/html/body"));
	}
	
	@Test
	public void showsTheTable() throws Exception {
		XmlFragment xml = new XmlFragment(view.toHtml());
		assertThat(xml, matches("//div[@id='table']"));
	}

	private void assertNodeIs(String xpath, String actual) {
		XmlFragment xml = new XmlFragment(view.toHtml());
		assertThat(xml.getNode(xpath).getTextContent(), is(actual));
	}
}
