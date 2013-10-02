package it.xpug.scopa.main;

import it.xpug.scopa.domain.*;
import it.xpug.scopa.view.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ScopaServlet extends HttpServlet {	
	
	private static class TemporaryScopaRepository implements ScopaRepository {
		static CardSet table = new CardSet(new Card[] { Card.parse("1-clubs"), Card.parse("2-clubs") });
		static CardSet playerHand = new CardSet(new Card[] { Card.parse("1-swords"), Card.parse("3-cups") });
		static ScopaService scopaService = new ScopaService(table, playerHand);

		@Override
		public GameService find() {
			return scopaService;
		}
	}

	private ScopaRepository repository;

	public ScopaServlet(ScopaRepository repository) {
		this.repository = repository;
	}
	
	public ScopaServlet() {
		this.repository = new TemporaryScopaRepository();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		GameService service = repository.find();		
		ScopaView view = new ScopaView(service);
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String card = req.getParameter("card");
		GameService game = repository.find();
		game.play(card);
		resp.sendRedirect("/");
	}
}
