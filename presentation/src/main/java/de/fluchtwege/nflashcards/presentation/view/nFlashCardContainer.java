package de.fluchtwege.nflashcards.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.presentation.activity.CardListActivity;
import de.fluchtwege.nflashcards.presentation.activity.CreateCardActivity;
import de.fluchtwege.nflashcards.presentation.activity.TestCardActivity;

public class nFlashCardContainer extends ViewContainer implements View.OnClickListener {

	public nFlashCardContainer(final Context context) {
		super(context);
	}

	@Override
	public void inflate(LayoutInflater inflater) {
		final View view = inflater.inflate(R.layout.main, this, true);

		final Button addCardButton = (Button) view.findViewById(R.id.addcard);
		addCardButton.setOnClickListener(this);

		final Button cardListButton = (Button) view.findViewById(R.id.cardlist);
		cardListButton.setOnClickListener(this);

		final Button testCardButton = (Button) view.findViewById(R.id.testcard);
		testCardButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.addcard) {
			getContext().startActivity(new Intent(getContext(), CreateCardActivity.class));
		} else if (v.getId() == R.id.cardlist) {
			getContext().startActivity(new Intent(getContext(), CardListActivity.class));
		} else if (v.getId() == R.id.testcard) {
			getContext().startActivity(new Intent(getContext(), TestCardActivity.class));
		}
	}
}
