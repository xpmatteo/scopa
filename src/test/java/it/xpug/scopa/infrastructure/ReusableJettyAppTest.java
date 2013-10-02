package it.xpug.scopa.infrastructure;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import it.xpug.scopa.jetty.*;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.junit.*;

public class ReusableJettyAppTest {

	private static ReusableJettyApp app = new ReusableJettyApp(TestServlet.class);		
	
	public static class TestServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().write("Hello!");
		}
	}

	@Test
	public void servletIsInvokedOnRoot() throws Exception {
		assertThat(get("/"), startsWith("Hello!"));
	}
	
	@Test
	public void servletIsInvokedOnAnyArbitraryPath() throws Exception {
		assertThat(get("/pippo"), startsWith("Hello!"));
	}

	@Test
	public void staticResourcesAreReturned() throws Exception {
		assertThat(get("/images/spinner.gif"), startsWith("GIF89"));
	}

	@BeforeClass
	public static void setUp() throws Exception {
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
