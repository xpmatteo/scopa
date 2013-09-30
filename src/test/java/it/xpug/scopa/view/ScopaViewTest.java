package it.xpug.scopa.view;

import static it.xpug.scopa.infrastructure.XmlFragment.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;
import it.xpug.scopa.infrastructure.*;

import org.junit.*;

public class ScopaViewTest {
	ScopaView view = new ScopaView();

	@Test@Ignore
	public void showsCardsFaceupOnTable() throws Exception {
		Card cardOnTheTable = new Card(1, Suit.CLUBS);
		assertThat(view.toHtml(), containsString(cardOnTheTable.toString()));
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
}
