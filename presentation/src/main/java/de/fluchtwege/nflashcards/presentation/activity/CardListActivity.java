package de.fluchtwege.nflashcards.presentation.activity;

import de.fluchtwege.nflashcards.presentation.presenter.CardListViewModel;
import de.fluchtwege.nflashcards.presentation.view.CardListContainer;

public class CardListActivity extends BaseActivity<CardListViewModel, CardListContainer> {

	@Override
	public CardListViewModel createViewModel(CardListContainer container) {
		return new CardListViewModel(container);
	}

	@Override
	public CardListContainer createContainer() {
		return new CardListContainer(this);
	}
}
