package de.fluchtwege.nflashcards.features.interactors;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Maraqopa on 10/03/16.
 */
public class EvaluateInput {

	public Observable<Boolean> evaluateInput(final DataSource dataSource, final int cardId, final int categoryId, final String entry) {
		return Observable.create(new Observable.OnSubscribe<Boolean>() {
			@Override
			public void call(final Subscriber<? super Boolean> subscriber) {
				dataSource.getCard(cardId).subscribe(new Action1<FlashCard>() {
					@Override
					public void call(FlashCard flashCard) {
						CardContent content = flashCard.getCardContent(categoryId);
						boolean matches = content.getValue().equals(entry);
						subscriber.onNext(matches);
					}
				});
			}
		});
	}

}
