package it.xpug.simplewebapp.main;

import it.xpug.simplewebapp.jetty.*;


public class Main {
	public static void main(String[] args) {		
		ReusableJettyApp app = new ReusableJettyApp(SimpleWebappServlet.class);		
		app.start(8080, "src/main/webapp");
	}
}
