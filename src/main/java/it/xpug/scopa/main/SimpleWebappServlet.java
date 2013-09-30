package it.xpug.scopa.main;

import it.xpug.scopa.freemarker.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleWebappServlet extends HttpServlet {	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		TemplateView view = new TemplateView("index.ftl");
		writer.write(view.toHtml());
		writer.close();
	}
}
