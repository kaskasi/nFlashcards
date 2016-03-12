package de.fluchtwege.nflashcards.presentation.presenter;

import java.util.List;

import de.fluchtwege.nflashcards.features.interactors.GetCard;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.view.CardListContainer;
import rx.functions.Action1;

import static de.fluchtwege.nflashcards.presentation.activity.nFlashcardsActivity.repository;

public class CardListViewModel extends ViewModel<CardListContainer> {

	public CardListViewModel(final CardListContainer cardListContainer) {
		container = cardListContainer;
		new GetCard().getCards(repository).subscribe(new Action1<List<FlashCard>>() {
			@Override
			public void call(final List<FlashCard> cards) {
				container.update(cards);
			}
		});
	}

}
