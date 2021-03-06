package de.fluchtwege.nflashcards.presentation.presenter;

import java.util.List;

import de.fluchtwege.nflashcards.features.interactors.CreateCard;
import de.fluchtwege.nflashcards.features.interactors.GetCategory;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.presentation.activity.nFlashcardsActivity;
import de.fluchtwege.nflashcards.presentation.view.CreateCardContainer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class CreateCardViewModel extends ViewModel<CreateCardContainer> {

	private PublishSubject<List<CardContent>> subject;

	public CreateCardViewModel(final CreateCardContainer createCardContainer) {
		container = createCardContainer;
		new GetCategory().getCategories(nFlashcardsActivity.repository).subscribe(new Action1<List<Category>>() {
			@Override
			public void call(final List<Category> categories) {
				subscribeToCardCreatedSubject(container);
				container.update(categories);

			}
		});
	}

	private void subscribeToCardCreatedSubject(final CreateCardContainer container) {
		subject = container.getClickButtonSubject();
		subject.subscribe(new Action1<List<CardContent>>() {
			@Override
			public void call(List<CardContent> cardToCreate) {
				createCard(cardToCreate);
			}
		});
	}

	public void createCard(final List<CardContent> cardToCreate) {
		new CreateCard().createCard(nFlashcardsActivity.repository, cardToCreate).subscribe(new Action1<FlashCard>() {
			@Override
			public void call(FlashCard flashCard) {
				container.done();
			}
		});
	}
}
