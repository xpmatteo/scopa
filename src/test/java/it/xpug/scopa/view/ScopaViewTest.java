package it.xpug.scopa.view;

import static it.xpug.scopa.domain.Card.*;
import static it.xpug.scopa.infrastructure.XmlFragment.*;
import static org.junit.Assert.*;
import it.xpug.scopa.adapters.html.*;
import it.xpug.scopa.domain.*;
import it.xpug.scopa.infrastructure.*;

import org.junit.*;

public class ScopaViewTest {
	private Deck deck = new Deck();
	private ScopaTable table = new ScopaTable();
	private Player player = new Player(table);
	private ScopaGameApplicationService scopaGameApplicationService = new ScopaGameApplicationService(deck, player, table);
	private ScopaView view = new ScopaView(scopaGameApplicationService);

	@Test
	public void showsCardsFaceupOnTable() throws Exception {
		table.add(bastoni(1));
		assertNodeExists("//div[@id='table']//li[@class='card']//img[@src='/images/cards/wands-01.jpg']");
	}

	@Test
	public void showsButtonsForYourHand() throws Exception {
		player.isDealt(swords(3));
		XmlFragment page = new XmlFragment(view.toHtml());
		XmlFragment playerHand = page.getNode("//div[@id='playerHand']");
		// hidden field
		playerHand.getNode(
				"//form/input[@type='hidden' and @value='swords-03']");
		// button
		playerHand.getNode(
				"//form/button//img[@src='/images/cards/swords-03.jpg']");
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

	private void assertNodeExists(String xpath) {
		XmlFragment xml = new XmlFragment(view.toHtml());
		xml.getNode(xpath);
	}
}
