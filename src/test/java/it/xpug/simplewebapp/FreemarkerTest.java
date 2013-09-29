package it.xpug.simplewebapp;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

import freemarker.template.*;


public class FreemarkerTest {
	@Test
	public void testFreemarker() throws Exception {
	    String templateString = "Hello, ${user}";
	    Template template = new Template("my template", 
	        new StringReader(templateString), new Configuration());

	    Map<Object, Object> model = new HashMap<Object, Object>();
	    model.put("user", "Tizius");

	    StringWriter writer = new StringWriter();
	    template.process(model, writer);
	    assertEquals("Hello, Tizius", writer.toString());
	}
	
	@Test
	public void loadFreemarkerTemplateFromFile() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("src/test/freemarker"));
		Template template = configuration.getTemplate("test.ftl");
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("user", "Pippo");
		StringWriter writer = new StringWriter();
		template.process(model, writer );
		
		assertEquals("Hello, Pippo!", writer.toString());
	}
}
