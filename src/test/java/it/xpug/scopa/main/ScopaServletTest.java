package it.xpug.scopa.main;

import java.util.*;

import it.xpug.scopa.domain.*;

import javax.servlet.http.*;

import org.hamcrest.*;
import org.jmock.*;
import org.jmock.api.*;
import org.jmock.integration.junit4.*;
import org.junit.*;

public class ScopaServletTest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	HttpServletRequest request = context.mock(HttpServletRequest.class);
	HttpServletResponse response = context.mock(HttpServletResponse.class);
	
	CardGameService game = context.mock(CardGameService.class);
	ScopaServlet servlet = new ScopaServlet(game);
	
	Map<String, String> parameters = new HashMap<String, String>();
	
	@Before
	public void setUp() throws Exception {
		context.checking(new Expectations() {{
			allowing(request).getParameter(with(any(String.class)));
			will(returnTheParameter());			
		}});
	}
	

	@Test
	public void sendsPlayToService() throws Exception {
		parameters.put("card", "3-cups");
		context.checking(new Expectations() {{
			oneOf(game).play("3-cups");

			oneOf(response).sendRedirect("/");
		}});
		servlet.doPost(request, response);
	}

	@Test
	public void sendsResetToRepository() throws Exception {
		parameters.put("new-game-command", "anything");

		context.checking(new Expectations() {{
			oneOf(game).startNewGame();			
			
			oneOf(response).sendRedirect("/");
		}});
		servlet.doPost(request, response);
	}

	protected Action returnTheParameter() {
		return new Action() {
			@Override
			public Object invoke(Invocation invocation) throws Throwable {
				return parameters.get(invocation.getParameter(0));
			}
			
			@Override
			public void describeTo(Description arg0) {
			}
		};
	}

}
