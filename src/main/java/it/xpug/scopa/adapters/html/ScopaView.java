package it.xpug.scopa.adapters.html;

import it.xpug.scopa.adapters.freemarker.*;
import it.xpug.scopa.domain.*;


public class ScopaView extends TemplateView {

	private CardGame scopaService;

	public ScopaView(CardGame scopaService) {
		super("index.ftl");
		this.scopaService = scopaService;
	}
	
	@Override
	public String toHtml() {
		super.put("game", scopaService);
		return super.toHtml();
	}

}
