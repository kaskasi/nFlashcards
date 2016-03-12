package de.fluchtwege.nflashcards.presentation.activity;

import de.fluchtwege.nflashcards.presentation.presenter.CreateCardViewModel;
import de.fluchtwege.nflashcards.presentation.view.CreateCardContainer;

public class CreateCardActivity extends BaseActivity<CreateCardViewModel, CreateCardContainer> {

	@Override
	public CreateCardViewModel createViewModel(CreateCardContainer container) {
		return new CreateCardViewModel(container);
	}

	@Override
	public CreateCardContainer createContainer() {
		return new CreateCardContainer(this);
	}

}
