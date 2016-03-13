package de.fluchtwege.nflashcards.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.fluchtwege.nflashcards.R;
import de.fluchtwege.nflashcards.features.models.FlashCard;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder>  {

	private final List<FlashCard> cards;

	public CardListAdapter(final List<FlashCard> cardList) {
		cards = cardList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.card_item, parent, false);
		final ViewHolder holder = new ViewHolder(view);
		holder.kanji = (TextView) view.findViewById(R.id.kanji);
		holder.hiragana = (TextView) view.findViewById(R.id.hiragana);
		holder.katakana = (TextView) view.findViewById(R.id.katakana);
		holder.deutsch = (TextView) view.findViewById(R.id.deutsch);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final FlashCard card = cards.get(position);
		holder.kanji.setText((CharSequence) card.getCardContent(0).getValue());
		holder.hiragana.setText((CharSequence) card.getCardContent(1).getValue());
		holder.katakana.setText((CharSequence) card.getCardContent(2).getValue());
		holder.deutsch.setText((CharSequence) card.getCardContent(3).getValue());
	}

	@Override
	public int getItemCount() {
		return cards.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		TextView kanji;
		TextView hiragana;
		TextView katakana;
		TextView deutsch;

		public ViewHolder(final View itemView) {
			super(itemView);
		}
	}
}
