package de.fluchtwege.nflashcards.features.boundaries;

import java.util.List;

import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.Group;
import rx.Observable;

public interface DataSource {

	Observable<Void> editCard(final FlashCard card, final int cardId);

	Observable<Void> createCard(final FlashCard card);

	Observable<Void> addContent(final CardContent content,  final int cardId);

	Observable<Void> removeContent(final CardContent content, final int cardId, final int contentId);

	Observable<FlashCard> getCard(final int index);

	Observable<Integer> createCategory(final Category category);

	Observable<List<FlashCard>> getCards();

	Observable<Void> createGroup(Group group);
}
