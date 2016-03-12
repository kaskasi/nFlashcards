package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.fluchtwege.nflashcards.presentation.presenter.ViewModel;
import de.fluchtwege.nflashcards.presentation.view.ViewContainer;

/**
 *
 */
public abstract class BaseActivity<T extends ViewModel, R extends ViewContainer> extends AppCompatActivity {

	protected R container;
	protected T viewModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		container = createContainer();
		viewModel = createViewModel();
		viewModel.setViewContainer(container);
		setContentView(container);
	}

	public abstract T createViewModel();

	public abstract R createContainer();

}
