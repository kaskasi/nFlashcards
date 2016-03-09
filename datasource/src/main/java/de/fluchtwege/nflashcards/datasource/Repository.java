package de.fluchtwege.nflashcards.datasource;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.Content;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import rx.Observable;
import rx.Subscriber;

public class Repository implements DataSource {

	private List<FlashCard> cards = new ArrayList<>();

	@Override
	public Observable<Void> editCard(final FlashCard card, final int cardId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.set(cardId, card);
				subscriber.onNext(null);
			}
		});
	}

	@Override
	public Observable<Void> createCard(final FlashCard card) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.add(card);
				subscriber.onNext(null);
			}
		});
	}

	@Override
	public Observable<Void> addContent(final Content content, final int cardId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.get(cardId).addContent(content);
				subscriber.onNext(null);
			}
		});
	}

	@Override
	public Observable<Void> removeContent(final Content content, final int cardId, final int contentId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.get(cardId).removeContent(contentId);
				subscriber.onNext(null);
			}
		});
	}

	@Override
	public Observable<FlashCard> getCard(final int cardId) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(Subscriber<? super FlashCard> subscriber) {
				FlashCard flashCard = cards.get(cardId);
				subscriber.onNext(flashCard);
			}
		});
	}


}
