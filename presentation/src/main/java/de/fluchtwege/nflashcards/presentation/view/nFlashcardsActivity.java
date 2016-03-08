package de.fluchtwege.nflashcards.presentation.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.datasource.Repository;
import de.fluchtwege.nflashcards.features.boundaries.IRepository;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;

public class nFlashcardsActivity extends AppCompatActivity implements View.OnClickListener {

	private IRepository repository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nflashcards);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(this);
		repository = new Repository();

	}

	@Override
	public void onClick(View v) {
		new CreateCard(repository).execute();
	}
}
