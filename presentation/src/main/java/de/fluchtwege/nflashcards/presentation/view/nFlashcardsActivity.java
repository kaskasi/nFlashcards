package de.fluchtwege.nflashcards.presentation.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.datasource.Repository;
import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.interactors.EditCard;
import de.fluchtwege.nflashcards.features.interactors.GetCard;
import de.fluchtwege.nflashcards.features.models.Content;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.TextContent;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class nFlashcardsActivity extends AppCompatActivity {

	private DataSource repository;
	private LinearLayout view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nflashcards);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		view = (LinearLayout) findViewById(R.id.card);


		repository = new Repository();
		new CreateCard().createCard(repository).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				flashCard.addContent(new TextContent("Fluss"));
				flashCard.addContent(new TextContent("川"));
				flashCard.addContent(new TextContent("かわ"));
				new EditCard().editCard(repository, flashCard, 0).
						subscribeOn(Schedulers.io()).
						observeOn(AndroidSchedulers.mainThread()).
						subscribe(new Action1<FlashCard>() {
							@Override
							public void call(FlashCard editedCard) {
								for (Content content : editedCard.get()) {
									final TextView textView = new TextView(nFlashcardsActivity.this);
									final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
									textView.setLayoutParams(params);
									textView.setText("Something: " + ((TextContent) content).get());
									view.addView(textView);
								}
							}
						});

			}
		});

	}
}
