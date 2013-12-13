package it.xpug.scopa.adapters.freemarker;

import java.io.*;

import freemarker.template.*;

public class TemplateView {

	private final SimpleHash model = new SimpleHash();
	private final String templateName;
	private final String templateDirectory;

	// Production
	public TemplateView(String templateName) {
		this(templateName, "src/main/freemarker");
	}

	// Test
	public TemplateView(String templateName, String templateDirectory) {
		this.templateName = templateName;
		this.templateDirectory = templateDirectory;
	}

	public void put(String key, Object value) {
		model.put(key, value);
	}

	public String toHtml() {
		try {
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
			Template template = configuration.getTemplate(templateName);

			StringWriter writer = new StringWriter();
			template.process(model, writer);
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		}
	}

}