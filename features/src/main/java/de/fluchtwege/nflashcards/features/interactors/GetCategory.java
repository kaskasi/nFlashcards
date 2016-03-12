package de.fluchtwege.nflashcards.features.interactors;

import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.Category;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class GetCategory {

	public Observable<Category> getCategory(final DataSource dataSource, final int categoryId) {
		return Observable.create(new Observable.OnSubscribe<Category>() {
			@Override
			public void call(final Subscriber<? super Category> subscriber) {
				dataSource.getCategory(categoryId).subscribe(new Action1<Category>() {
					@Override
					public void call(Category category) {
						subscriber.onNext(category);
						subscriber.onCompleted();
					}
				});
			}
		});
	}

	public Observable<List<Category>> getCategories(final DataSource dataSource) {
		return Observable.create(new Observable.OnSubscribe<List<Category>>() {
			@Override
			public void call(final Subscriber<? super List<Category>> subscriber) {
				dataSource.getCategories().subscribe(new Action1<List<Category>>() {
					@Override
					public void call(List<Category> categories) {
						subscriber.onNext(categories);
						subscriber.onCompleted();
					}
				});
			}
		});
	}
}
