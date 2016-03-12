package de.fluchtwege.nflashcards.features.interactors;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Maraqopa on 09/03/16.
 */
public class EditCard {

	public Observable<FlashCard> editCard(final DataSource dataSource, final FlashCard card, final int index) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(final Subscriber<? super FlashCard> subscriber) {
				dataSource.editCard(card, index).subscribe(new Action1<Void>() {
					@Override
					public void call(Void aVoid) {
						subscriber.onNext(card);
					}
				});
			}
		});
	}
}
