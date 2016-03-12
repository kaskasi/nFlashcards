package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.presentation.presenter.CreateCardViewModel;
import de.fluchtwege.nflashcards.presentation.view.CreateCardContainer;


/**
 * Created by Maraqopa on 09/03/16.
 */
public class CreateCardActivity extends BaseActivity<CreateCardViewModel, CreateCardContainer> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcard);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	public CreateCardViewModel createViewModel() {
		return new CreateCardViewModel();
	}

	@Override
	public CreateCardContainer createContainer() {
		return new CreateCardContainer(this);
	}

}
