package it.xpug.scopa.view;

import it.xpug.scopa.domain.*;
import it.xpug.scopa.freemarker.*;


public class ScopaView extends TemplateView {

	private GameService scopaService;

	public ScopaView(GameService scopaService) {
		super("index.ftl");
		this.scopaService = scopaService;
	}
	
	@Override
	public String toHtml() {
		super.put("table", scopaService.table());
		super.put("playerHand", scopaService.playerHand());
		return super.toHtml();
	}

}
