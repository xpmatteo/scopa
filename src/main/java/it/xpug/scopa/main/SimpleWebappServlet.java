package it.xpug.scopa.main;

import it.xpug.scopa.domain.*;
import it.xpug.scopa.view.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleWebappServlet extends HttpServlet {	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		CardSet table = new CardSet(new Card[] { Card.parse("1-clubs"), Card.parse("2-clubs") });
		CardSet playerHand = new CardSet(new Card[] { Card.parse("1-swords"), Card.parse("3-cups") });
		GameService gameService = new GameService(table, playerHand);
		
		ScopaView view = new ScopaView(gameService);
		writer.write(view.toHtml());
		writer.close();
	}
}
