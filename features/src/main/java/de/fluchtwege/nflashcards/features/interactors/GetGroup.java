package de.fluchtwege.nflashcards.features.interactors;

import java.util.List;

import de.fluchtwege.nflashcards.features.boundaries.DataSource;
import de.fluchtwege.nflashcards.features.models.Group;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class GetGroup {
	public Observable<Group> getGroup(final DataSource dataSource, final int groupId) {
		return Observable.create(new Observable.OnSubscribe<Group>() {
			@Override
			public void call(final Subscriber<? super Group> subscriber) {
				dataSource.getGroup(groupId).subscribe(new Action1<Group>() {
					@Override
					public void call(Group group) {
						subscriber.onNext(group);
						subscriber.onCompleted();
					}
				});
			}
		});
	}

	public Observable<List<Group>> getGroups(final DataSource dataSource) {
		return Observable.create(new Observable.OnSubscribe<List<Group>>() {
			@Override
			public void call(final Subscriber<? super List<Group>> subscriber) {
				dataSource.getGroups().subscribe(new Action1<List<Group>>() {
					@Override
					public void call(List<Group> groups) {
						subscriber.onNext(groups);
						subscriber.onCompleted();
					}
				});
			}
		});
	}
}
