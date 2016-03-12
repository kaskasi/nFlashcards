package de.fluchtwege.nflashcards.datasource;

import java.util.ArrayList;
import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.CardContent;
import de.fluchtwege.nflashcards.features.models.Category;
import de.fluchtwege.nflashcards.features.models.FlashCard;
import de.fluchtwege.nflashcards.features.models.Group;
import rx.Observable;
import rx.Subscriber;

public class Repository implements DataSource {

	private List<FlashCard> cards = new ArrayList<>();
	private List<Group> groups = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();

	@Override
	public Observable<Void> editCard(final FlashCard card, final int cardId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.set(cardId, card);
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<Void> createCard(final FlashCard card) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				card.setId(cards.size());
				cards.add(card);
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<Void> addContent(final CardContent content, final int cardId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.get(cardId).addContent(content);
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<Void> removeContent(final CardContent content, final int cardId, final int contentId) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				cards.get(cardId).removeContent(contentId);
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<FlashCard> getCard(final int cardId) {
		return Observable.create(new Observable.OnSubscribe<FlashCard>() {
			@Override
			public void call(Subscriber<? super FlashCard> subscriber) {
				if (cards.size() > cardId) {
					FlashCard flashCard = cards.get(cardId);
					subscriber.onNext(flashCard);
					subscriber.onCompleted();
				}
			}
		});
	}

	@Override
	public Observable<Integer> createCategory(final Category category) {
		return Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				category.setId(categories.size());
				categories.add(category);
				subscriber.onNext(categories.size() - 1);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<List<FlashCard>> getCards() {
		return Observable.create(new Observable.OnSubscribe<List<FlashCard>>() {
			@Override
			public void call(Subscriber<? super List<FlashCard>> subscriber) {
				subscriber.onNext(cards);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<Void> createGroup(final Group group) {
		return Observable.create(new Observable.OnSubscribe<Void>() {
			@Override
			public void call(Subscriber<? super Void> subscriber) {
				group.setId(categories.size());
				groups.add(group);
				subscriber.onNext(null);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<List<Category>> getCategories() {
		return Observable.create(new Observable.OnSubscribe<List<Category>>() {
			@Override
			public void call(Subscriber<? super List<Category>> subscriber) {
				subscriber.onNext(categories);
				subscriber.onCompleted();
			}
		});
	}

	@Override
	public Observable<Category> getCategory(final int categoryId) {
		return Observable.create(new Observable.OnSubscribe<Category>() {
			@Override
			public void call(Subscriber<? super Category> subscriber) {
				subscriber.onNext(categories.get(categoryId));
				subscriber.onCompleted();
			}
		});
	}


}
