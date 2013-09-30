package it.xpug.scopa.view;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.domain.*;
import it.xpug.scopa.infrastructure.*;

import org.hamcrest.*;
import org.junit.*;

public class ScopaViewTest {

	@Test@Ignore
	public void showsCardsFaceupOnTable() throws Exception {
		Card cardOnTheTable = new Card(1, Suit.CLUBS);
		ScopaView view = new ScopaView();
		assertThat(view.toHtml(), containsString(cardOnTheTable.toString()));
	}
	
	@Test
	public void hasProperHtmlStructure() throws Exception {
		XmlFragment xml = new XmlFragment(new ScopaView().toHtml());
		assertThat(xml, XmlFragment.matches("/html/head/title"));
	}
}
