package de.fluchtwege.nflashcards.presentation.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.models.FlashCard;

public class CardListContainer extends ViewContainer {

	private RecyclerView recyclerView;

	public CardListContainer(Context context) {
		super(context);
	}

	@Override
	public void inflate(final LayoutInflater inflater) {
		final View view = inflater.inflate(R.layout.card_list, this, true);
		recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
		final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
	}

	public void update(final List<FlashCard> cards) {
		final CardListAdapter adapter = new CardListAdapter(cards);
		recyclerView.setAdapter(adapter);
	}
}
