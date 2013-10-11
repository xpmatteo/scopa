package it.xpug.scopa.adapters.persistence;

import it.xpug.scopa.domain.*;

public class InMemoryScopaRepository implements ScopaRepository {
	CardGameService scopaService = new ScopaService();
	
	public InMemoryScopaRepository() {
		scopaService.startNewGame();
	}

	@Override
	public CardGameService find() {
		return scopaService;
	}
}