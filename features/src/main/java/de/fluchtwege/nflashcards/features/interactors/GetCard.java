package de.fluchtwege.nflashcards.features.interactors;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class GetCard {

	public Observable<FlashCard> getCard(final DataSource dataSource, final int cardId) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(final Subscriber<? super FlashCard> subscriber) {
				dataSource.getCard(cardId).subscribe(new Action1<FlashCard>() {
					@Override
					public void call(FlashCard flashCard) {
						subscriber.onNext(flashCard);
					}
				});
			}
		});
	}

}
