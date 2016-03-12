package de.fluchtwege.nflashcards.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.fluchtwege.nflashcards.presentation.presenter.ViewModel;
import de.fluchtwege.nflashcards.presentation.view.ViewContainer;

/**
 * Created by Maraqopa on 11/03/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

	protected ViewContainer container;
	protected ViewModel viewModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		container = createContainer();
		viewModel = createViewModel(container);
		setContentView(container);
	}

	public abstract ViewModel createViewModel(ViewContainer container);

	public abstract ViewContainer createContainer();

}
