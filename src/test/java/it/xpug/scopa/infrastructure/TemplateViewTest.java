package it.xpug.scopa.infrastructure;

import static org.junit.Assert.*;
import it.xpug.scopa.adapters.freemarker.*;

import org.junit.*;

public class TemplateViewTest {
	
	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		TemplateView view = new TemplateView("test.ftl", "src/test/freemarker");
		view.put("user", "Tizius");
	    assertEquals("Hello, Tizius!", view.toHtml());
	}
}
