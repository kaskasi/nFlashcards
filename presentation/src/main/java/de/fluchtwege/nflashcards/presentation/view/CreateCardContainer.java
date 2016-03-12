package de.fluchtwege.nflashcards.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.subjects.PublishSubject;

public class CreateCardContainer extends ViewContainer implements View.OnClickListener {

	private final LinearLayout container;
	private int counter;
	private Category[] categories;
	private PublishSubject<FlashCard> subject;

	public CreateCardContainer(Context activity) {
		super(activity);
		final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.addcard, this, true);
		Button addMore = (Button) findViewById(R.id.addmore);
		addMore.setOnClickListener(this);
		Button done = (Button) findViewById(R.id.done);
		done.setOnClickListener(this);
		container = (LinearLayout) findViewById(R.id.container);
		subject  = PublishSubject.create();


	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.addmore) {
			EditText edit = new EditText(getContext());
			edit.setId(counter);
			counter++;
			container.addView(edit);
		} else if (view.getId() == R.id.done) {
			FlashCard card = new FlashCard(new ArrayList<CardContent>(0));
			for (int i = 0; i < counter; i++) {
				EditText edit = (EditText) findViewById(i);
				String text = edit.getText().toString();
				card.addContent(new CardContent(categories[i], text));
			}


		}

	}

	public void setCategories(Category[] categories) {
		this.categories = categories;
	}

	public void done() {
		((Activity) getContext()).finish();
	}

	public PublishSubject<FlashCard> getClickButtonSubject() {
		return subject;
	}
}
