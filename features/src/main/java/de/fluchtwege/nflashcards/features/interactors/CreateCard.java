package de.fluchtwege.nflashcards.features.interactors;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class CreateCard {

	public Observable<FlashCard> createCard(final DataSource dataSource) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(final Subscriber<? super FlashCard> subscriber) {
				final FlashCard flashCard = new FlashCard(new ArrayList<CardContent>());
				dataSource.createCard(flashCard).subscribe(new Action1<Void>() {
					@Override
					public void call(Void aVoid) {
						subscriber.onNext(flashCard);
						subscriber.onCompleted();
					}
				});
			}
		});
	}

	public Observable<FlashCard> createCard(final DataSource dataSource, final List<CardContent> cardContentList) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(final Subscriber<? super FlashCard> subscriber) {
				final FlashCard cardToCreate = new FlashCard(cardContentList);
				dataSource.createCard(cardToCreate).subscribe(new Action1<Void>() {
					@Override
					public void call(Void aVoid) {
						subscriber.onNext(cardToCreate);
						subscriber.onCompleted();
					}
				});
			}
		});
	}
}
