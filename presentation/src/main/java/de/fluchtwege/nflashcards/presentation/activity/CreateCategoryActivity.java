package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.presentation.presenter.CreateCategoryViewModel;
import de.fluchtwege.nflashcards.presentation.view.CreateCategoryContainer;

/**
 * Created by Maraqopa on 11/03/16.
 */
public class CreateCategoryActivity extends BaseActivity<CreateCategoryViewModel, CreateCategoryContainer> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createcategory);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	public CreateCategoryViewModel createViewModel() {
		return new CreateCategoryViewModel();
	}

	@Override
	public CreateCategoryContainer createContainer() {
		return new CreateCategoryContainer(this);
	}
}
