package de.fluchtwege.nflashcards.presentation.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.interactors.EditCard;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.TextContent;
import rx.functions.Action1;


/**
 * Created by Maraqopa on 09/03/16.
 */
public class CreateCardActivity extends AppCompatActivity implements View.OnClickListener {

	private LinearLayout container;
	private int counter = 0;

	private FlashCard flashCard;

	private Category[] categories = new Category[]{
			new Category("Kanji"), new Category("Hiragana"), new Category("Übersetzung")
	};

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_addcard);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Button addMore = (Button) findViewById(R.id.addmore);
		addMore.setOnClickListener(this);
		Button done = (Button) findViewById(R.id.done);
		done.setOnClickListener(this);
		container = (LinearLayout) findViewById(R.id.container);


		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.addmore) {
			EditText edit = new EditText(CreateCardActivity.this);
			edit.setId(counter);
			counter++;
			container.addView(edit);
		} else if (view.getId() == R.id.done) {
			new CreateCard().createCard(nFlashcardsActivity.repository).subscribe(new Action1<FlashCard>() {
				@Override
				public void call(FlashCard flashCard) {
					for (int i = 0; i < counter; i++) {
						EditText edit = (EditText) findViewById(i);
						String text = edit.getText().toString();
						flashCard.addContent(new CardContent(categories[i], text));

					}
					new EditCard().editCard(nFlashcardsActivity.repository, flashCard, flashCard.getId()).subscribe(new Action1<FlashCard>() {
						@Override
						public void call(FlashCard flashCard) {
							finish();
						}
					});
				}
			});


		}
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"AddCard Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://de.fluchtwege.nflashcards.presentation.activity/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"AddCard Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://de.fluchtwege.nflashcards.presentation.activity/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}
}
