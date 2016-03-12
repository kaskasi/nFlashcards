package de.fluchtwege.nflashcards.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.presenter.ViewModel;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;


/**
 * Created by Maraqopa on 11/03/16.
 */
public class TestCardContainer extends ViewContainer implements TextWatcher {

	private final TextView category;
	private final TextView given;
	private EditText entry;
	private PublishSubject<String> publishSubject;

	public TestCardContainer(Context activity) {
		super(activity);
		final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.testcard, this, true);

		entry = (EditText) findViewById(R.id.entry);
		entry.addTextChangedListener(this);
		given = (TextView) findViewById(R.id.given);
		category = (TextView) findViewById(R.id.category);
	}

	public void update(FlashCard card, int categoryIndex) {
		given.setText((CharSequence) card.getCardContent(0).getValue());
		category.setText(card.getCardContent(categoryIndex).getCategory().getName());
		entry.setText("");
	}

	public void setPublishSubject(PublishSubject<String> subject) {
		publishSubject = subject;
	}

	public void updateTest(boolean testCorrect) {
		if (testCorrect) {
			entry.setTextColor(getContext().getResources().getColor(R.color.green));
		} else {
			entry.setTextColor(getContext().getResources().getColor(R.color.red));
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		publishSubject.onNext(entry.getText().toString());
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	public void testDone() {
		((Activity)getContext()).finish();
	}
}
