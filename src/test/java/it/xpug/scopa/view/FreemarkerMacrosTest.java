package it.xpug.scopa.view;

import static it.xpug.scopa.domain.Card.*;
import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

import freemarker.template.*;

public class FreemarkerMacrosTest {

	private SimpleHash model = new SimpleHash();

	@Test
	public void substitutesVariablesInTemplate() throws Exception {
		String template = ""
				+ "<#include 'src/main/freemarker/macros.ftl'>"
				+ "<@cardImage theCard=card />";
		String expected = "<img src=\"/images/cards/swords-04.jpg\" alt=\"4 di spade\" title=\"4 di spade\" width=\"60\" />";
		model.put("card", swords(4));
		assertEquals(expected, render(template));
	}

	private String render(String templateString) throws IOException, TemplateException {
		Template template = new Template("my template",
				new StringReader(templateString), new Configuration());

		StringWriter writer = new StringWriter();
		template.process(model, writer);
		return writer.toString().trim();
	}

}
