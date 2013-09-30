package it.xpug.scopa.infrastructure;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.jetty.*;
import it.xpug.scopa.main.*;

import java.io.*;
import java.net.*;

import org.junit.*;

public class SmokeTest {

	private static ReusableJettyApp app;

	@Test
	public void servletIsInvokedOnRoot() throws Exception {
		assertThat(get("/"), startsWith("<!DOCTYPE html>"));
	}
	
	@Test
	public void servletIsInvokedOnAnyArbitraryPath() throws Exception {
		assertThat(get("/pippo"), startsWith("<!DOCTYPE html>"));
	}

	@Test
	public void staticResourcesAreReturned() throws Exception {
		assertThat(get("/images/spinner.gif"), startsWith("GIF89"));
	}

	@BeforeClass
	public static void setUp() throws Exception {
		app = new ReusableJettyApp(SimpleWebappServlet.class);		
		app.start(8123, "src/main/webapp");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		app.shutdown();
	}

	private String get(String path) throws IOException {
		InputStream stream = new URL("http://localhost:" + 8123 + path).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.readLine();
	}
}