package de.fluchtwege.nflashcards.features.interactors;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.Category;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class CreateCategory {

	public Observable<Category> createCategory(final DataSource dataSource, final Category category) {
		return Observable.create(new Observable.OnSubscribe<Category>() {
			@Override
			public void call(final Subscriber<? super Category> subscriber) {
				dataSource.createCategory(category).subscribe(new Action1<Integer>() {
					@Override
					public void call(Integer integer) {
							subscriber.onNext(category);
					}
				});
			}
		});
	}
}
