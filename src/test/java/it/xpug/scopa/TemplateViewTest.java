package it.xpug.scopa;

import it.xpug.scopa.freemarker.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TemplateViewTest {
	
	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		TemplateView view = new TemplateView("test.ftl", "src/test/freemarker");
		view.put("user", "Tizius");
	    assertEquals("Hello, Tizius!", view.toHtml());
	}
}
