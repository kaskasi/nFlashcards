package de.fluchtwege.nflashcards.presentation.activity;

import de.fluchtwege.nflashcards.presentation.presenter.CardListViewModel;
import de.fluchtwege.nflashcards.presentation.view.CardListContainer;

/**
 * Created by Maraqopa on 11/03/16.
 */
public class CardListActivity extends BaseActivity<CardListViewModel, CardListContainer> {

	@Override
	public CardListViewModel createViewModel() {
		return new CardListViewModel();
	}

	@Override
	public CardListContainer createContainer() {
		return new CardListContainer(this);
	}
}
