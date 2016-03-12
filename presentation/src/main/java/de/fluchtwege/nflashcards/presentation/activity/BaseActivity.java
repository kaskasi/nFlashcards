package de.fluchtwege.nflashcards.presentation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

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
		container.inflate((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		viewModel = createViewModel(container);
		setContentView(container);
		Toolbar toolbar = (Toolbar) findViewById(de.fluchtwege.nflashcards.R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	public abstract T createViewModel(R container);

	public abstract R createContainer();

}
