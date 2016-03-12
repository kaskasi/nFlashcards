package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.presentation.controller.TestFlowController;
import de.fluchtwege.nflashcards.presentation.presenter.TestCardViewModel;
import de.fluchtwege.nflashcards.presentation.view.TestCardContainer;

/**
 * Created by Maraqopa on 10/03/16.
 */
public class TestCardActivity extends BaseActivity<TestCardViewModel, TestCardContainer> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		new TestFlowController().perform(viewModel);
	}

	@Override
	public TestCardViewModel createViewModel() {
		return new TestCardViewModel();
	}

	@Override
	public TestCardContainer createContainer() {
		return new TestCardContainer(this);
	}
}