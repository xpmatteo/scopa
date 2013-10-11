package it.xpug.scopa.main;

import it.xpug.scopa.adapters.html.*;
import it.xpug.scopa.adapters.persistence.*;
import it.xpug.scopa.domain.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ScopaServlet extends HttpServlet {	
	
	private ScopaRepository repository;

	public ScopaServlet(ScopaRepository repository) {
		this.repository = repository;
	}
	
	public ScopaServlet() {
		this(new InMemoryScopaRepository());
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		CardGameService service = repository.find();		
		ScopaView view = new ScopaView(service);
		PrintWriter writer = response.getWriter();
		writer.write(view.toHtml());
		writer.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String card = req.getParameter("card");
		CardGameService game = repository.find();
		game.play(card);
		resp.sendRedirect("/");
	}
}
