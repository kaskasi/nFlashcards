package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.presentation.controller.TestFlowController;
import de.fluchtwege.nflashcards.presentation.presenter.TestCardViewModel;
import de.fluchtwege.nflashcards.presentation.presenter.ViewModel;
import de.fluchtwege.nflashcards.presentation.view.TestCardContainer;
import de.fluchtwege.nflashcards.presentation.view.ViewContainer;

/**
 * Created by Maraqopa on 10/03/16.
 */
public class TestCardActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		new TestFlowController().perform((TestCardViewModel) viewModel);
	}

	@Override
	public ViewModel createViewModel(ViewContainer container) {
		return new TestCardViewModel((TestCardContainer) container);
	}

	@Override
	public ViewContainer createContainer() {
		return new TestCardContainer(this);
	}
}