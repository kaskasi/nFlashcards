package de.fluchtwege.nflashcards.features.boundaries;

import de.fluchtwege.nflashcards.features.models.Content;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;

public interface DataSource {

	Observable<Void> editCard(final FlashCard card, int cardId);

	Observable<Void> createCard(final FlashCard card);

	Observable<Void> addContent(Content content,  int cardId);

	Observable<Void> removeContent(Content content, int cardId, int contentId);

	Observable<FlashCard> getCard(final int index);
}
