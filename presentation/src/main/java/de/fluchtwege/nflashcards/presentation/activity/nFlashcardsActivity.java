package de.fluchtwege.nflashcards.presentation.activity;

import de.fluchtwege.nflashcards.datasource.Repository;
import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.presentation.presenter.nFlashCardViewModel;
import de.fluchtwege.nflashcards.presentation.view.nFlashCardContainer;

public class nFlashcardsActivity extends BaseActivity<nFlashCardViewModel, nFlashCardContainer> {

	public static DataSource repository = new Repository();

	@Override
	public nFlashCardViewModel createViewModel(nFlashCardContainer container) {
		return new nFlashCardViewModel(container);
	}

	@Override
	public nFlashCardContainer createContainer() {
		return new nFlashCardContainer(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
