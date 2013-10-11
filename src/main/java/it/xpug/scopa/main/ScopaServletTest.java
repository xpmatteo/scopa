package it.xpug.scopa.main;

import it.xpug.scopa.domain.*;

import javax.servlet.http.*;

import org.jmock.*;
import org.jmock.integration.junit4.*;
import org.junit.*;

public class ScopaServletTest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	HttpServletRequest request = context.mock(HttpServletRequest.class);
	HttpServletResponse response = context.mock(HttpServletResponse.class);
	
	ScopaRepository repository = context.mock(ScopaRepository.class);
	GameService game = context.mock(GameService.class);
	ScopaServlet servlet = new ScopaServlet(repository);
	
	@Test
	public void sendsPlayToService() throws Exception {
		context.checking(new Expectations() {{
			allowing(request).getParameter("card");
			will(returnValue("3-cups"));
			oneOf(response).sendRedirect("/");
			allowing(repository).find();
			will(returnValue(game));
			oneOf(game).play("3-cups");
		}});
		servlet.doPost(request, response);
//		fail("This test is too complicated");
	}

}
