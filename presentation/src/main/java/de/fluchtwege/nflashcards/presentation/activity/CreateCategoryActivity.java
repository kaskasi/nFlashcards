package de.fluchtwege.nflashcards.presentation.activity;

import de.fluchtwege.nflashcards.presentation.presenter.CreateCategoryViewModel;
import de.fluchtwege.nflashcards.presentation.view.CreateCategoryContainer;

public class CreateCategoryActivity extends BaseActivity<CreateCategoryViewModel, CreateCategoryContainer> {


	@Override
	public CreateCategoryViewModel createViewModel(CreateCategoryContainer container) {
		return new CreateCategoryViewModel(container);
	}

	@Override
	public CreateCategoryContainer createContainer() {
		return new CreateCategoryContainer(this);
	}
}
