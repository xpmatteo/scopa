package it.xpug.scopa.main;

import it.xpug.scopa.adapters.html.*;
import it.xpug.scopa.adapters.persistence.*;
import it.xpug.scopa.domain.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ScopaServlet extends HttpServlet {	
	
	private CardGameService service;

	public ScopaServlet(CardGameService service) {
		this.service = service;
	}
	
	public ScopaServlet() {
		this(new ScopaService());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		ScopaView view = new ScopaView(service);
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("new-game-command") != null) {
			service.startNewGame();
		} else {
			String card = req.getParameter("card");
			service.play(card);
		}
		resp.sendRedirect("/");
	}
}
