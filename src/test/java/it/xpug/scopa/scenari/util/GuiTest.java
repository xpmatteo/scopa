package it.xpug.scopa.scenari.util;

import it.xpug.scopa.adapters.jetty.*;
import it.xpug.scopa.main.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class GuiTest {

	protected static final int APPLICATION_PORT = 8123;
	protected static ReusableJettyApp app = new ReusableJettyApp(ScopaServlet.class);
	protected WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public static void startApplication() throws Exception {
		app.start(APPLICATION_PORT, "src/main/webapp");
	}

	@AfterClass
	public static void shutdownApplication() throws Exception {
		app.shutdown();
	}
}
