package it.xpug.scopa.view;

import it.xpug.scopa.domain.*;
import it.xpug.scopa.freemarker.*;


public class ScopaView extends TemplateView {

	private GameService gameService;

	public ScopaView(GameService gameService) {
		super("index.ftl");
		this.gameService = gameService;
	}
	
	@Override
	public String toHtml() {
		super.put("table", gameService.table());
		super.put("playerHand", gameService.playerHand());
		return super.toHtml();
	}

}
