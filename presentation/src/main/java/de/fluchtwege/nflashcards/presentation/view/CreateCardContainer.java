package de.fluchtwege.nflashcards.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.subjects.PublishSubject;

public class CreateCardContainer extends ViewContainer implements View.OnClickListener {

	private List<Category> categories;
	private PublishSubject<FlashCard> subject;

	private EditText kanjiEntry;
	private EditText hiraganaEntry;
	private EditText katakanaEntry;
	private EditText deutschEntry;

	private TextView kanjiCategory;
	private TextView hiraganaCategory;
	private TextView katakanaCategory;
	private TextView deutschCategory;

	public CreateCardContainer(Context activity) {
		super(activity);
		subject = PublishSubject.create();
	}

	@Override
	public void inflate(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.create_card, this, true);

		kanjiCategory = (TextView) view.findViewById(R.id.category_kanji);
		kanjiEntry = (EditText) view.findViewById(R.id.entry_kanji);

		hiraganaCategory = (TextView) view.findViewById(R.id.category_hiragana);
		hiraganaEntry = (EditText) view.findViewById(R.id.entry_hiragana);

		katakanaCategory = (TextView) view.findViewById(R.id.category_katakana);
		katakanaEntry = (EditText) view.findViewById(R.id.entry_katakana);

		deutschCategory = (TextView) view.findViewById(R.id.category_deutsch);
		deutschEntry = (EditText) view.findViewById(R.id.entry_deutsch);

		Button done = (Button) view.findViewById(R.id.done);
		done.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.done) {
			FlashCard card = new FlashCard(new ArrayList<CardContent>(0));
			card.addContent(new CardContent(categories.get(0), kanjiEntry.getText().toString()));
			card.addContent(new CardContent(categories.get(1), hiraganaEntry.getText().toString()));
			card.addContent(new CardContent(categories.get(2), katakanaEntry.getText().toString()));
			card.addContent(new CardContent(categories.get(3), deutschEntry.getText().toString()));
			subject.onNext(card);
			subject.onCompleted();
		}
	}

	public void update(List<Category> categoryList) {
		categories = categoryList;

		katakanaCategory.setText(categories.get(2).getName());
		hiraganaCategory.setText(categories.get(1).getName());
		kanjiCategory.setText(categories.get(0).getName());
		deutschCategory.setText(categories.get(3).getName());
	}

	public void done() {
		((Activity) getContext()).finish();
	}

	public PublishSubject<FlashCard> getClickButtonSubject() {
		return subject;
	}

}
