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

	public Repository() {
		setupFakeData();
	}

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


	public void setupFakeData() {
		categories.add(new Category("Kanji"));
		categories.add(new Category("Hiragana"));
		categories.add(new Category("Katagana"));
		categories.add(new Category("Deutsch"));

		FlashCard card0 = create(categories.get(0), "木", categories.get(1), "き", categories.get(2), "モク", categories.get(3), "Baum");
		FlashCard card1 = create(categories.get(0), "本", categories.get(1), "ほん", categories.get(2), "ホン", categories.get(3), "Buch");
		FlashCard card2 = create(categories.get(0), "山", categories.get(1), "やま", categories.get(2), "サン", categories.get(3), "Berg");
		FlashCard card3 = create(categories.get(0), "中", categories.get(1), "なか", categories.get(2), "チュウ", categories.get(3), "Mitte");
		FlashCard card4 = create(categories.get(0), "日", categories.get(1), "にち", categories.get(2), "ヒ", categories.get(3), "Tag");
		FlashCard card5 = create(categories.get(0), "月", categories.get(1), "つき", categories.get(2), "ゲツ", categories.get(3), "Mond, Monat");
		FlashCard card6 = create(categories.get(0), "猫", categories.get(1), "ねこ", categories.get(2), "", categories.get(3), "Katze");
		FlashCard card7 = create(categories.get(0), "川", categories.get(1), "かわ", categories.get(2), "", categories.get(3), "Fluss");

		cards.add(card0);
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
	}

	private FlashCard create( Category c1, String s1,Category c2, String s2, Category c3, String s3, Category c4, String s4) {
		FlashCard card = new FlashCard(new ArrayList<CardContent>());
		card.addContent(new CardContent(c1, s1));
		card.addContent(new CardContent(c2, s2));
		card.addContent(new CardContent(c3, s3));
		card.addContent(new CardContent(c4, s4));
		return card;
	}

}
