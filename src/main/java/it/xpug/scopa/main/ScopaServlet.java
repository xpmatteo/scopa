package it.xpug.scopa.main;

import it.xpug.scopa.adapters.html.*;
import it.xpug.scopa.domain.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ScopaServlet extends HttpServlet {

	private CardGameService game;

	public ScopaServlet(CardGameService service) {
		this.game = service;
	}

	public ScopaServlet() {
		this(new ScopaGameApplicationService());
	}

	@Override
	public void init() throws ServletException {
		game.onStartNewGame();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		ScopaView view = new ScopaView(game);
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("new-game-command") != null) {
			game.onStartNewGame();
		} else {
			String card = req.getParameter("card");
			game.onCardPlayed(card);
		}
		resp.sendRedirect("/");
	}
}
